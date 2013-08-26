package com.magicmicky.habitrpglibrary.onlineapi;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONObject;

import com.magicmicky.habitrpglibrary.habits.HabitItem;
import com.magicmicky.habitrpglibrary.habits.User;
import com.magicmicky.habitrpglibrary.onlineapi.WebServiceInteraction.Answer;
import com.magicmicky.habitrpglibrary.onlineapi.helper.ParseErrorException;
import com.magicmicky.habitrpglibrary.onlineapi.helper.ParserHelper;

public class ReviveUser extends WebServiceInteraction {
	private static final String CMD = "user/revive";
	public ReviveUser(OnHabitsAPIResult callback,
			HostConfig config) {
		super(CMD, callback, config);
	}

	@Override
	protected HttpRequestBase getRequest() {
		return new HttpPost();
	}

	@Override
	protected Answer findAnswer(JSONObject answer) {
		return new ReviveData(answer,this.getCallback());
	}
	private class ReviveData extends Answer {

		public ReviveData(JSONObject obj, OnHabitsAPIResult callback) {
			super(obj, callback);
		}
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
