package com.magicmicky.habitrpglibrary.onlineapi;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;


public class PutTimeZone extends WebServiceInteraction {
	private final static String CMD = "user/";
	private int mTimeZone;
	public PutTimeZone(OnHabitsAPIResult callback,
			HostConfig config, int timeZone) {
		super(CMD, callback, config);
		this.mTimeZone=timeZone;
	}

	@Override
	protected HttpRequestBase getRequest() {
		HttpPut method =  new HttpPut();
		try {
			StringEntity ent = new StringEntity("{\"preferences.timezoneOffset\":" + mTimeZone + "}","utf-8");
			ent.setContentType("application/json; charset=UTF-8");
			method.setEntity(ent);
			System.out.println(method.getMethod());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return method;
	}

	@Override
	protected Answer findAnswer(JSONObject answer) {
		return new PutTimeZoneData(answer,getCallback());
	}
	private class PutTimeZoneData extends Answer {

		public PutTimeZoneData(JSONObject obj, OnHabitsAPIResult callback) {
			super(obj, callback);
		}
		public void parse() {
			//HabitItem it;
			/*try {
				//it = ParserHelper.parseHabitItem(getObject());
				if(this.callback != null)
					this.callback.onEditTaskAnswer(it);

			} catch (ParseErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(this.callback != null)
					this.callback.onError(e);

			}*/
		}
		
	}
}
