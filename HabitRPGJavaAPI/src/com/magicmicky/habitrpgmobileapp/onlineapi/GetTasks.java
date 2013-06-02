package com.magicmicky.habitrpgmobileapp.onlineapi;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONObject;
/**
 * Retrieves the list of tasks from the API
 * @deprecated Not implemented yet.
 * @author MagicMicky
 */
public class GetTasks extends WebServiceInteraction{

	public GetTasks(String command,JSONObject obj, OnHabitsAPIResult callback, HostConfig config) {
		super(command, callback, config);
	}

	@Override
	protected HttpRequestBase getRequest() {
		return new HttpGet();
	}
	@Override
	protected Answer findAnswer(JSONObject answer) {
		// TODO Auto-generated method stub
		return null;
	}

	class TasksData extends Answer {

		public TasksData(JSONObject obj, OnHabitsAPIResult callback) {
			super(obj, callback);
		}

		@Override
		public void parse() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
