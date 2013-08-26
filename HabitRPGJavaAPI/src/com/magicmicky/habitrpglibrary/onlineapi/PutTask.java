package com.magicmicky.habitrpglibrary.onlineapi;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import com.magicmicky.habitrpglibrary.habits.HabitItem;
import com.magicmicky.habitrpglibrary.onlineapi.WebServiceInteraction.Answer;
import com.magicmicky.habitrpglibrary.onlineapi.helper.ParseErrorException;
import com.magicmicky.habitrpglibrary.onlineapi.helper.ParserHelper;
/**
 * Edit a new task.
 * @see PostTask#findAnswer(JSONObject)
 * @author Mickael
 *
 */
public class PutTask extends PostTask {
	private static final String CMD = "user/task/";

	public PutTask(OnHabitsAPIResult callback, HostConfig config, HabitItem habit) {
		super(CMD + habit.getId(), callback, config, habit);
	}

	@Override
	protected HttpRequestBase getRequest() {
		HttpPut method =  new HttpPut();
		try {
			StringEntity ent = new StringEntity(habit.getJSONString());
			ent.setContentType("application/json");
			method.setEntity(ent);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return method;
	}

	@Override
	protected Answer findAnswer(JSONObject answer) {
		return new PutTaskData(answer, this.getCallback());
	}

	private class PutTaskData extends Answer {

		public PutTaskData(JSONObject obj, OnHabitsAPIResult callback) {
			super(obj, callback);
		}
		public void parse() {
			HabitItem it;
			try {
				it = ParserHelper.parseHabitItem(getObject());
				if(this.callback != null)
					this.callback.onEditTaskAnswer(it);

			} catch (ParseErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(this.callback != null)
					this.callback.onError(e);

			}
		}
		
	}
	
}
