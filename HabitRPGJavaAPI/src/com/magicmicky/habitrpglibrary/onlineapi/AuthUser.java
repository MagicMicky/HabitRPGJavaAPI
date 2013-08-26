package com.magicmicky.habitrpglibrary.onlineapi;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import com.magicmicky.habitrpglibrary.onlineapi.WebServiceInteraction.Answer;
import com.magicmicky.habitrpglibrary.onlineapi.helper.ParseErrorException;
import com.magicmicky.habitrpglibrary.onlineapi.helper.ParserHelper;

public class AuthUser extends WebServiceInteraction {
	private final static String CMD="user/auth/local";
	private final static String CMD_FB="user/auth/facebook";
	private static final String TAG_USER_NAME = "username";
	private static final String TAG_USER_PASSWORD = "password";
	private static final String TAG_FACEBOOK_ID="facebook_id";
	private String mUserName;
	private String mUserPassword;
	private String mUserFacebookId;
	public AuthUser(OnHabitsAPIResult callback,HostConfig config, String name, String password) {
		super(CMD, callback, config);
		this.mUserName= name;
		this.mUserPassword=password;
	}
	public AuthUser(OnHabitsAPIResult callback,HostConfig config, String facebook_id) {
		super(CMD_FB, callback, config);
		this.mUserFacebookId= facebook_id;
	}
	protected HttpRequestBase getRequest() {
		HttpPost method =  new HttpPost();
		try {
			StringBuilder sb = new StringBuilder()
			.append("{");
			if(mUserFacebookId==null) {
				sb.append("\"").append(TAG_USER_NAME).append("\":").append(JSONObject.quote(mUserName)).append(",")
				.append("\"").append(TAG_USER_PASSWORD).append("\":").append(JSONObject.quote(mUserPassword));
			} else {
				sb.append("\"").append(TAG_FACEBOOK_ID).append("\":").append(JSONObject.quote(mUserFacebookId));
			}
			sb.append("}");
			StringEntity ent = new StringEntity(sb.toString());
			ent.setContentType("application/json");
			method.setEntity(ent);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return method;
	}

	@Override
	protected Answer findAnswer(JSONObject answer) {
		return new AuthUserData(answer, getCallback());
	}

	public class AuthUserData extends Answer {
		
		public AuthUserData(JSONObject obj, OnHabitsAPIResult callback) {
			super(obj,callback);
		}
		
		public void parse() {
			JSONObject obj = this.getObject();
			try {
			
				String[] tokens = ParserHelper.parseAuthenticationAnswer(obj);
				callback.onUserConnected(tokens[0], tokens[1]);
			} catch (ParseErrorException e) {
						callback.onError(e);
			}
		}
	}

}
