package com.magicmicky.habitrpglibrary.onlineapi;



import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONException;
import org.json.JSONObject;

import com.magicmicky.habitrpglibrary.habits.*;
import com.magicmicky.habitrpglibrary.onlineapi.helper.ParseErrorException;
import com.magicmicky.habitrpglibrary.onlineapi.helper.ParserHelper;


/**
 * Retrieve all the user's information from the web
 * @author MagicMicky
 * @see OnHabitsAPIResult#onUserReceived(User)
 */
public class GetUser extends WebServiceInteraction {
	private final static String command = "user/";
	/**
	 * Create a new GetUser Request, based on a callback (that will be called at the end), and a HostConfig
	 * @param callback the callback to call once we have retrieved the user
	 * @param config the Config of the server to call
	 */
	public GetUser(OnHabitsAPIResult callback, HostConfig config) {
		super(command, callback,config);
	}

	@Override
	protected Answer findAnswer(JSONObject answer) {
		Answer omg=null;
		try {
			omg = new UserData(answer, this.getCallback());

		} catch(JSONException e) {
			System.out.println("error GetUser: new UserData has thrown a JSONException (no tag habitRPGData");
		}
		return omg;
	}

	@Override
	protected HttpRequestBase getRequest() {
		return new HttpGet();
	}
	

	
	/**
	 * Parse the information about the User
	 * @author MagicMicky
	 *
	 */
	private class UserData extends Answer {
		private static final String TAG_habitData = "habitRPGData";
		
		/**
		 * Create a new UserData based on a JSONObject to parse, and a callback to call
		 * @param obj the json that contains the user's values
		 * @param callback the callback to call when the information is parsed
		 * @throws JSONException 
		 */
		public UserData(JSONObject obj, OnHabitsAPIResult callback) throws JSONException {
			super(obj.has(TAG_habitData) ?obj.getJSONObject(TAG_habitData):obj, callback);
		}


		@Override
		public void parse() {
			try {
				User us=ParserHelper.parseUser(this.getObject());
				callback.onUserReceived(us);
			} catch (ParseErrorException e) {
				e.printStackTrace();
				callback.onError(e);
			}

		}
	}
		
}
