package com.magicmicky.habitrpglibrary.onlineapi;


import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONException;
import org.json.JSONObject;

import com.magicmicky.habitrpgllibrary.onlineapi.helper.ParseErrorException;
import com.magicmicky.habitrpgllibrary.onlineapi.helper.ParserHelper;


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
	

	@Override
	protected HttpRequestBase getRequest() {
		return new HttpPost();
	}
	
	/**
	 * The result returned by the command
	 * @author MagicMicky
	 *
	 */
	private class PostTaskDirectionData extends Answer {

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
				try {
					double[] res = ParserHelper.parseDirectionAnswer(getObject());
					 callback.onPostResult(res[0], res[1], res[2], res[3], res[4]);
				} catch (ParseErrorException e) {
					e.printStackTrace();
					callback.onError(e);
				}
		}
		
	}

}
