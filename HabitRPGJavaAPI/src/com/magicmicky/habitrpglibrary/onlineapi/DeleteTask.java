package com.magicmicky.habitrpglibrary.onlineapi;


import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONObject;

import com.magicmicky.habitrpglibrary.habits.HabitItem;
import com.magicmicky.habitrpglibrary.onlineapi.helper.ParseErrorException;
import com.magicmicky.habitrpglibrary.onlineapi.helper.ParserHelper;

public class DeleteTask extends WebServiceInteraction {
	private final static String CMD = "user/tasks/";
	private final HabitItem habit;
	
	public DeleteTask(OnHabitsAPIResult callback, HostConfig config, HabitItem habit) {
		super(CMD + habit.getId(), callback, config);
		System.out.println("DELETING " +CMD + habit.getId());
		this.habit=habit;
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
			boolean deleted=false;
			try {
				deleted = ParserHelper.parseDeletedTask(getObject());
				if(deleted)
		 			callback.onDeletedTask(habit);
			} catch (ParseErrorException e) {
				callback.onError(e);
				e.printStackTrace();
			}
		}
	}

}
