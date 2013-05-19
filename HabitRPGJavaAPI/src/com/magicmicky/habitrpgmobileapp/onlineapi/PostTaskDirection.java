package com.magicmicky.habitrpgmobileapp.onlineapi;


import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * POST a task in a direction to the API
 * @author MagicMicky
 * @see OnHabitsAPIResult#onPostResult(double, double, double, double, double)
 */
public class PostTaskDirection extends WebServiceInteraction {
	private static final String CMD = "user/tasks/";
	/**
	 * Create a new PostTaskDirection, based on a callback to call, a taskId, a direction and the server's config
	 * @param callback the callback to call after the POST command is done
	 * @param taskId the task who want to update
	 * @param direction the direction ("up" or "down")
	 * @param config the HostConfig to set up
	 */
	public PostTaskDirection(OnHabitsAPIResult callback, String taskId, String direction, HostConfig config) {
		super(CMD + taskId + "/" + direction, callback, config);
	}

	@Override
	protected Answer findAnswer(JSONObject answer) {
		return new PostTaskDirectionData(answer, this.getCallback());
	}
	/**
	 * The result returned by the command
	 * @author MagicMicky
	 *
	 */
	private class PostTaskDirectionData extends Answer {
		private static final String TAG_NOWXP = "exp";
		private static final String TAG_NOWGP = "gp";
		private static final String TAG_NOWHP = "hp";
		private static final String TAG_LVL = "lvl";
		private static final String TAG_DELTA = "delta";

		/**
		 * Creates a new result
		 * @param obj the JSONObject to parse
		 * @param callback the callback to call once everything is parsed
		 */
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
