package com.magicmicky.habitrpgmobileapp.onlineapi;


import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONObject;

import com.magicmicky.habitrpgmobileapp.habits.HabitItem;

public class DeleteTask extends WebServiceInteraction {
	private final static String CMD = "user/task/";
	public DeleteTask(OnHabitsAPIResult callback, HostConfig config, HabitItem habit) {
		super(CMD + habit.getId(), callback, config);
		System.out.println("DELETING " +CMD + habit.getId());
	}
	@Override
	protected HttpRequestBase getRequest() {
		
		HttpRequestBase request = new HttpDelete();
       // request.addHeader("Content-Type", "application/json");
		return request;
	}
	@Override
	protected Answer findAnswer(JSONObject answer) {
		return new DeleteData(answer,this.getCallback());
	}
	
	
	public class DeleteData extends Answer {
		private static final String TAG_TASK_DELETED = "task_deleted";
		/**
		 * Create a new Answer based on the object to parse, and the callback to call after.
		 * @param obj the object to parse
		 * @param callback the callback to call after parsing
		 */
		public DeleteData(JSONObject obj, OnHabitsAPIResult callback) {
			super(obj,callback);
		}
		/**
		 * Parse the {@code object}
		 */
		public void parse() {
			if(this.getObject().has(TAG_TASK_DELETED)) {
	 			callback.onDeletedTask(true);
			} else {
	 			callback.onError(new WebServiceException(WebServiceException.TASK_DELETE_FAIL));

			}

		}
	}

}
