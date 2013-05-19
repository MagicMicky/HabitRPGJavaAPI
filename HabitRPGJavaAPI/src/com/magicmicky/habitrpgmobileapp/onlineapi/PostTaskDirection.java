package com.magicmicky.habitrpgmobileapp.onlineapi;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class PostTaskDirection extends WebServiceInteraction {
	private static final String CMD = "user/tasks/";
	
	public PostTaskDirection(OnHabitsAPIResult callback, String taskId, String direction, HostConfig config) {
		super(CMD + taskId + "/" + direction, callback, config);
	}

	@Override
	protected Answer findAnswer(JSONObject answer) {
		return new PostTaskDirectionData(answer, this.getCallback());
	}

	private class PostTaskDirectionData extends Answer {
		private static final String TAG_NOWXP = "exp";
		private static final String TAG_NOWGP = "gp";
		private static final String TAG_NOWHP = "hp";
		private static final String TAG_LVL = "lvl";
		private static final String TAG_DELTA = "delta";

		
		public PostTaskDirectionData(JSONObject obj, OnHabitsAPIResult callback) {
			super(obj, callback);
		}

		@Override
		public void parse() {
			double xp = 0,hp = 0,gold = 0,lvl = 0,delta = 0;
			try {
				 xp = this.getObject().getDouble(TAG_NOWXP);
				 hp = this.getObject().getDouble(TAG_NOWHP);
				 gold = this.getObject().getDouble(TAG_NOWGP);
				 lvl = this.getObject().getDouble(TAG_LVL);
				 delta = this.getObject().getDouble(TAG_DELTA);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			callback.onPostResult(xp, hp, gold, lvl, delta);
		}
		
	}

	@Override
	protected HttpRequestBase getRequest() {
		return new HttpPost();
	}
}
