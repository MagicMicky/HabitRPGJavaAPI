package com.magicmicky.habitrpgmobileapp.onlineapi;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import com.magicmicky.habitrpgmobileapp.habits.HabitItem;
import com.magicmicky.habitrpgmobileapp.onlineapi.WebServiceInteraction.Answer;
import com.magicmicky.habitrpgmobileapp.onlineapi.WebServiceInteraction.WebServiceException;
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
		return super.findAnswer(answer);
	}

	private class PutTaskData extends PostTaskData {

		public PutTaskData(JSONObject obj, OnHabitsAPIResult callback) {
			super(obj, callback);
		}
		public void parse() {
			if(this.getObject().has(TAG_ERR)) {
				
				try {
					WebServiceException ex = new WebServiceException(WebServiceException.HABITRPG_INTERNAL_ERROR,this.getObject().getString(TAG_ERR));
					this.callback.onError(ex);

				} catch (JSONException e) {
					e.printStackTrace();
					//Should never happen since we check that it has an error;
				}
				return;
			}
			HabitItem item  = this.parseTask();
			if(item == null) {
				WebServiceException ex = new WebServiceException(WebServiceException.JSON_TASKS_UNPARSABLE);
				this.callback.onError(ex);
			}
			this.parseTags(item);
			if(this.callback != null)
				this.callback.onEditTaskAnswer(item);
		}

		
	}
	
}
