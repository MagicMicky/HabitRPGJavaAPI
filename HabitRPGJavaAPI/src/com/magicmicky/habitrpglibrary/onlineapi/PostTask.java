package com.magicmicky.habitrpglibrary.onlineapi;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.magicmicky.habitrpglibrary.habits.Daily;
import com.magicmicky.habitrpglibrary.habits.Habit;
import com.magicmicky.habitrpglibrary.habits.HabitItem;
import com.magicmicky.habitrpglibrary.habits.HabitType;
import com.magicmicky.habitrpglibrary.habits.Reward;
import com.magicmicky.habitrpglibrary.habits.ToDo;
import com.magicmicky.habitrpglibrary.onlineapi.helper.ParseErrorException;
import com.magicmicky.habitrpglibrary.onlineapi.helper.ParserHelper;
/**
 * Allows creation of a new task using POST /api/v2/user/tasks
 * @author Mickael
 * @see OnHabitsAPIResult#onPostTaskAnswer(HabitItem)
 */
public class PostTask extends WebServiceInteraction {
	private static final String CMD = "user/tasks/";
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
			StringEntity ent = new StringEntity(habit.getJSONString(), "utf-8");
			ent.setContentType("application/json; charset=UTF-8");
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

		public PostTaskData(JSONObject obj, OnHabitsAPIResult callback) {
			super(obj, callback);
			this.setObject(obj);
		}

		@Override
		public void parse() {
			HabitItem it;
			try {
				it = ParserHelper.parseHabitItem(getObject());
				if(this.callback != null)
					this.callback.onPostTaskAnswer(it);

			} catch (ParseErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(this.callback != null)
					this.callback.onError(e);

			}
		}
	}

}
