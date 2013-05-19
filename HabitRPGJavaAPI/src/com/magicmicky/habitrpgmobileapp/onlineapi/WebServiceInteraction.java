package com.magicmicky.habitrpgmobileapp.onlineapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;


public abstract class WebServiceInteraction {
//	final private static String WEBSERVICE_URL = "https://beta.habitrpg.com/api/v1/";
	final private static String SUFFIX = "api/v1/";
//	final private static String API_KEY ="b89ef880-7e07-4d13-8a5f-b6be25437fd8";
//	final private static String USER_KEY = "710f41f1-4113-4d8a-9714-79a84edd6175";
	final private String CMD;
	final private OnHabitsAPIResult callback;
	private HostConfig config;
	
	
	/**
	 * Create a new WebServiceInteraction based on a command, and a callback to call once finished
	 * @param command the command to GET
	 * @param callback the callback to call with the different information about the user
	 */
	public WebServiceInteraction(String command, OnHabitsAPIResult callback, HostConfig config) {
		this.CMD = command;
		this.callback=callback;
		this.config=config;
	}
	
	/**
	 * Get the data from the WEBSERVICE
	 * @return the {@link Answer}
	 */
	public final Answer getData() {
		Answer result=null;
	        // Making HTTP request
	        try {
	            // defaultHttpClient
	            HttpClient client = new DefaultHttpClient();
	            HttpRequestBase request = this.getRequest();
	            String address = config.getAddress();
	            address = address.concat(address.charAt(address.length()-1) == '/' ? "api/v1/" + this.CMD : "/api/v1/" + this.CMD);

	            request.setURI(new URI(address));
	            request.addHeader("x-api-key", config.getApi());
	            request.addHeader("x-api-user", config.getUser());
	            request.addHeader("Accept-Encoding", "gzip");
	            
	            HttpResponse response = client.execute(request);
	            if(response.getStatusLine().getStatusCode() !=200) {
	            	throw new Exception("The server didn't answer 200");
	            }
	            HttpEntity httpEntity = response.getEntity();
	            InputStream is = httpEntity.getContent();          
	            Header contentEncoding = response.getFirstHeader("Content-Encoding");
	            if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
	                is = new GZIPInputStream(is);
	            }

	            
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    is));
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");

	            }
	            is.close();
	            String jsonstr = sb.toString();
	            JSONObject jsonObj = new JSONObject(jsonstr);
	            result = findAnswer(jsonObj);
            } catch (UnsupportedEncodingException e) {
	            this.callback.onError(null);
	        } catch (ClientProtocolException e) {
	            this.callback.onError(null);
	        } catch (IOException e) {
	            this.callback.onError(null);

	        } catch (JSONException e) {
	            this.callback.onError(null);
	        } catch (Exception e) {
	            this.callback.onError(null);
	            e.printStackTrace();
	        }
	 
	        return result;
	 
	    }
	/**
	 * Return the request pre filled with misc data (POST, GET, PUT, DELETE...)
	 * @return
	 */
	protected abstract HttpRequestBase getRequest();

	/**
	 * Find the Answer based on a JSONObject
	 * @param answer the JSONObject to parse
	 * @return the answer object, fulfilled with the information
	 */
	protected abstract Answer findAnswer(JSONObject answer);
	
	/**
	 * @return the callback
	 */
	public OnHabitsAPIResult getCallback() {
		return callback;
	}
	/**
	 * The Answer Type. Contains a Answer, and is used to parse the JSON and call {@code callback} with the information
	 * @author MagicMicky
	 */
	public abstract class Answer {
		private JSONObject object;
		protected OnHabitsAPIResult callback;
		/**
		 * Create a new Answer based on the object to parse, and the callback to call after.
		 * @param obj the object to parse
		 * @param callback the callback to call after parsing
		 */
		public Answer(JSONObject obj, OnHabitsAPIResult callback) {
			this.setObject(obj);
			this.callback =callback;
		}
		/**
		 * Parse the {@code object}
		 */
		public abstract void parse();

		/**
		 * @return the object
		 */
		public JSONObject getObject() {
			return object;
		}

		/**
		 * @param object the object to set
		 */
		public void setObject(JSONObject object) {
			this.object = object;
		}
	}
}
