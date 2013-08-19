package com.magicmicky.habitrpgmobileapp.onlineapi;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import com.magicmicky.habitrpgmobileapp.habits.HabitItem;
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

}
