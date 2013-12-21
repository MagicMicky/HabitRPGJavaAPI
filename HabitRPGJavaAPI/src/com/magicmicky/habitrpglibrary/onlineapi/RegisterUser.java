package com.magicmicky.habitrpglibrary.onlineapi;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import com.magicmicky.habitrpglibrary.onlineapi.helper.ParseErrorException;
import com.magicmicky.habitrpglibrary.onlineapi.helper.ParserHelper;

public class RegisterUser extends WebServiceInteraction {
	private final static String CMD = "register";
	
	private final static String TAG_USER_NAME="username";
	private final static String TAG_USER_MAIL="email";
	private final static String TAG_USER_PASSWORD="password";
	private final static String TAG_USER_CONFIRM_PASSWORD="confirmPassword";
	
	private String mUserName;
	private String mUserMail;
	private String mUserPassword;
	private String mUserConfirmPassword;

	public RegisterUser(OnHabitsAPIResult callback, HostConfig config
			, String name, String mail, String password, String confirmPassword) {
		super(CMD, callback, config);
		this.mUserName= name;
		this.mUserMail = mail;
		this.mUserPassword=password;
		this.mUserConfirmPassword=confirmPassword;
	}

	@Override
	protected HttpRequestBase getRequest() {
		HttpPost method =  new HttpPost();
		try {
			StringBuilder sb = new StringBuilder()
			.append("{")
				.append("\"").append(TAG_USER_NAME).append("\":").append(JSONObject.quote(mUserName)).append(",")
				.append("\"").append(TAG_USER_MAIL).append("\":").append(JSONObject.quote(mUserMail)).append(",")
				.append("\"").append(TAG_USER_PASSWORD).append("\":").append(JSONObject.quote(mUserPassword)).append(",")
				.append("\"").append(TAG_USER_CONFIRM_PASSWORD).append("\":").append(JSONObject.quote(mUserConfirmPassword))
			.append("}");
			StringEntity ent = new StringEntity(sb.toString(), "utf-8");
			ent.setContentType("application/json; charset=UTF-8");
			method.setEntity(ent);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return method;
	}

	@Override
	protected Answer findAnswer(JSONObject answer) {
		
		return new RegisterData(answer, this.getCallback());
	}
	
	public class RegisterData extends Answer {
		private final static String TAG_API_TOKEN="apiToken";
		private final static String TAG_API_USER_TOKEN="id";
		private final static String TAG_ERR="err";
		public RegisterData(JSONObject obj, OnHabitsAPIResult callback) {
			super(obj,callback);
		}
		
		public void parse() {
			String api_t;
			String user_t;
			String[] res;
			try {
				res = ParserHelper.parseRegistrationAnswer(this.getObject());
				api_t = res[0];
				user_t = res[1];
			callback.onNewUser(api_t, user_t);
			} catch (ParseErrorException e) {
				e.printStackTrace();
				callback.onError(e);
			}
		}
	}
}
