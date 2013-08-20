package com.magicmicky.habitrpgmobileapp.onlineapi;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import com.magicmicky.habitrpgmobileapp.habits.Daily;
import com.magicmicky.habitrpgmobileapp.habits.Habit;
import com.magicmicky.habitrpgmobileapp.habits.HabitItem;
import com.magicmicky.habitrpgmobileapp.habits.HabitType;
import com.magicmicky.habitrpgmobileapp.habits.Reward;
import com.magicmicky.habitrpgmobileapp.habits.ToDo;
/**
 * Allows creation of a new task using POST /api/v1/user/task
 * @author Mickael
 * @see OnHabitsAPIResult#onPostTaskAnswer(HabitItem)
 */
public class PostTask extends WebServiceInteraction {
	private static final String CMD = "user/task/";
	protected HabitItem habit;
	public PostTask(OnHabitsAPIResult callback, HostConfig config, HabitItem habit) {
		super(CMD, callback, config);
		this.habit=habit;
	}
	protected PostTask(String CMD, OnHabitsAPIResult callback, HostConfig config, HabitItem habit) {
		super(CMD, callback, config);
		this.habit=habit;
	}

	@Override
	protected HttpRequestBase getRequest() {
		HttpPost method =  new HttpPost();
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
		return new PostTaskData(answer, this.getCallback());
	}
	

	/**
	 * The result of the POST task request, which has to be parsed
	 * @author Mickael
	 *
	 */
	protected class PostTaskData extends Answer {
		protected static final String TAG_ERR ="err";
		private static final String TAG_TASK_TYPE = "type";
		private static final String TAG_TASK_ID = "id";
		private static final String TAG_TASK_NOTES = "notes";
		private static final String TAG_TASK_TEXT = "text";
		private static final String TAG_TASK_VALUE = "value";
		private static final String TAG_TASK_COMPLETED = "completed";
		private static final String TAG_TASK_UP = "up";
		private static final String TAG_TASK_DOWN = "down";
		private static final String TAG_TASK_TAGS = "tags";
		public PostTaskData(JSONObject obj, OnHabitsAPIResult callback) {
			super(obj, callback);
			this.setObject(obj);
		}

		@Override
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
				this.callback.onPostTaskAnswer(item);
		}


		/**
		 * Parse a task
		 * @return
		 */
		protected HabitItem parseTask() {
			JSONObject obj = this.getObject();
			String type;
			HabitItem item = null;
			try {
				type = obj.getString(TAG_TASK_TYPE);

				if(type.equals(HabitType.daily.toString())) {
					boolean[] days = {false,false,false,false,false,false,false};
					item = new Daily(obj.getString(TAG_TASK_ID), obj.has(TAG_TASK_NOTES) ? obj.getString(TAG_TASK_NOTES) : "", "",  obj.getString(TAG_TASK_TEXT), obj.has(TAG_TASK_VALUE) ? obj.getDouble(TAG_TASK_VALUE) : 0, obj.has(TAG_TASK_COMPLETED) ? obj.getBoolean(TAG_TASK_COMPLETED) : false,days);
				} else if(type.equals(HabitType.todo.toString())) {
					item = new ToDo(obj.getString(TAG_TASK_ID), obj.has(TAG_TASK_NOTES) ? obj.getString(TAG_TASK_NOTES) : "", "", obj.getString(TAG_TASK_TEXT), obj.has(TAG_TASK_VALUE) ? obj.getDouble(TAG_TASK_VALUE) : 0, obj.has(TAG_TASK_COMPLETED) ?  obj.getBoolean(TAG_TASK_COMPLETED) : false, null);
				} else if(type.equals(HabitType.reward.toString())) {
					item = new Reward(obj.getString(TAG_TASK_ID), obj.has(TAG_TASK_NOTES) ? obj.getString(TAG_TASK_NOTES) : "", "", obj.getString(TAG_TASK_TEXT), obj.has(TAG_TASK_VALUE) ? obj.getDouble(TAG_TASK_VALUE) : 0);
				} else {
					item = new Habit(obj.getString(TAG_TASK_ID), obj.has(TAG_TASK_NOTES) ? obj.getString(TAG_TASK_NOTES) : "", "", obj.getString(TAG_TASK_TEXT), obj.has(TAG_TASK_VALUE) ? obj.getDouble(TAG_TASK_VALUE) : 0,obj.has(TAG_TASK_UP)? obj.getBoolean(TAG_TASK_UP) : false, obj.has(TAG_TASK_DOWN) ? obj.getBoolean(TAG_TASK_DOWN) : false);
				}
			} catch (JSONException e) {
				WebServiceException ex = new WebServiceException(WebServiceException.JSON_TASKS_UNPARSABLE);
				this.callback.onError(ex);
				e.printStackTrace();
			}
			return item;	
		}
		
		/**
		 * Parse the tags for an HabitItem
		 * @param item the habitItem where we need to put the tags
		 */
		protected void parseTags(HabitItem item) {
			List<String> tags= new ArrayList<String>();
			JSONObject tagsObj;
			if(this.getObject().has(TAG_TASK_TAGS)) {
				try {
					tagsObj = this.getObject().getJSONObject(TAG_TASK_TAGS);
					@SuppressWarnings("unchecked")
					Iterator<String> it = tagsObj.keys();
					while(it.hasNext()) {
						String tagId = it.next();
						if(tagsObj.getBoolean(tagId)) {
							tags.add(tagId);
						}
					}
					item.setTagsId(tags);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
			
		
	}

}
