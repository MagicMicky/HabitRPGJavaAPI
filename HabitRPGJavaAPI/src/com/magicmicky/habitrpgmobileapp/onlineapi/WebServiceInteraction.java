package com.magicmicky.habitrpgmobileapp.onlineapi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * The main class that do the interaction with the webservice.
 * @author Mickael
 *
 */
public abstract class WebServiceInteraction {
	final private static String SUFFIX = "api/v1/";
	final private String CMD;
	final private OnHabitsAPIResult callback;
	private HostConfig config;
	
	
	/**
	 * Create a new WebServiceInteraction based on a command, and a callback to call once finished
	 * @param command the command to GET
	 * @param callback the callback to call with the different information about the user
	 * @param config the host configuration
	 */
	public WebServiceInteraction(String command, OnHabitsAPIResult callback, HostConfig config) {
		this.CMD = command;
		this.callback=callback;
		this.config=config;
	}
	
	/**
	 * Get the data from the WebService
	 * @return the {@link Answer}
	 */
	public final Answer getData() {
		Answer result=null;
		HttpClient client;
		HttpRequestBase request = null;

	        // Making HTTP request
	        try {
	            String address = config.getAddress();
	            address = address.concat(address.charAt(address.length()-1) == '/' ? SUFFIX + this.CMD : "/api/v1/" + this.CMD);
	            client = new DefaultHttpClient();
	            request = this.getRequest();

	            request.setURI(new URI(address));
	            request.addHeader("x-api-key", config.getApi());
	            request.addHeader("x-api-user", config.getUser());
	            request.addHeader("Accept-Encoding", "gzip");
	            
	            HttpResponse response = client.execute(request);
	            if(response.getStatusLine().getStatusCode() == 204) {//Tasks deleted
            		String jsonstr = "{\"task_deleted\":true}";
		            JSONObject jsonObj = new JSONObject(jsonstr);
    	            result = findAnswer(jsonObj); 
            	}
	            else {
	            	if(response.getStatusLine().getStatusCode() == 404) {
	            		throw new WebServiceException(WebServiceException.HABITRPG_SERVER_API_CALL_NOT_FOUND);
	            	}
	            	if(response.getStatusLine().getStatusCode() == 504) {
	            		throw new WebServiceException(WebServiceException.SERV_EXPERIENCING_ISSUES);
	            		//throw new WebServiceException("The server is experiencing issues. Please either wait or change server.");
	            	} else if(response.getStatusLine().getStatusCode() == 401) {
	            		//TODO:Should continue reading stuff and check if body errors = No user found.
	            		throw new WebServiceException(WebServiceException.AUTH_PB);
	            	} else {
	            		System.out.println(response.getStatusLine().getStatusCode() + "-" + response.getStatusLine().getReasonPhrase());
	            	}//throw new Exception("The server didn't answer 200");
		            
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
		            System.out.println(jsonstr);
		            JSONObject jsonObj = new JSONObject(jsonstr);
		            result = findAnswer(jsonObj);
	            }
            } catch (UnsupportedEncodingException e) {
				WebServiceException ex = new WebServiceException(WebServiceException.INTERNAL_WRONG_URL);
				this.callback.onError(ex);
	            e.printStackTrace();
	        } catch (ClientProtocolException e) {
				this.callback.onError(new WebServiceException(WebServiceException.INTERNAL_OTHER,e.getMessage()));
	            e.printStackTrace();
	        } catch(UnknownHostException e) {
	            this.callback.onError(new WebServiceException(WebServiceException.INTERNAL_NO_CONNECTION));
	        	e.printStackTrace();
			}/*  catch (IOException e) {
	            this.callback.onError("An error happened... Are you still connected to the internet?");
	            e.printStackTrace();

	        }*/ catch(WebServiceException e) {//TODO:Custom exception?!?!
				this.callback.onError(new WebServiceException(WebServiceException.INTERNAL_OTHER,e.getMessage()));
				e.printStackTrace(); 
			} catch (JSONException e) {
				if(request!=null) {
					String uri = request.getURI().toString();
					if(!uri.contains("habitrpg.com") || !uri.contains("https://") ) {
						WebServiceException ex = new WebServiceException(WebServiceException.INTERNAL_WRONG_URL);
						this.callback.onError(ex);
					} else {
						WebServiceException ex = new WebServiceException(WebServiceException.SERV_EXPERIENCING_ISSUES);
						this.callback.onError(ex);
			            e.printStackTrace();
					}

				}
	        } catch (URISyntaxException e) {
				WebServiceException ex = new WebServiceException(WebServiceException.INTERNAL_WRONG_URL);
				this.callback.onError(ex);
	        	e.printStackTrace();
			}
	        catch (Exception e) {
				WebServiceException ex = new WebServiceException(WebServiceException.INTERNAL_OTHER);
				this.callback.onError(ex);

	        	e.printStackTrace();
			}
	 
	        return result;
	 
	    }
	/**
	 * Return the request pre filled with misc data (POST, GET, PUT, DELETE...)
	 * @return the HttpRequest which is going to be used
	 */
	protected abstract HttpRequestBase getRequest();

	/**
	 * Retrieve the Answer based on a JSONObject
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
	 * The Answer Type.<br/>
	 * Is used to parse the JSON and call {@code callback} with the information
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
		protected JSONObject getObject() {
			return object;
		}

		/**
		 * @param object the object to set
		 */
		protected void setObject(JSONObject object) {
			this.object = object;
		}
	}
	/**
	 * The class that is used to throw exceptions from the webservice
	 * @author Mickael
	 */
	@SuppressWarnings("serial")
	public class WebServiceException extends Exception {
		/**
		 * Happens when the server api call wasn't found.
		 */
		public final static int HABITRPG_SERVER_API_CALL_NOT_FOUND=-1;
		
		/**
		 * Happens when HabitRPG sends a "err" json tag
		 */
		public final static int HABITRPG_INTERNAL_ERROR=0;
		/**
		 * Happens when there is a problem with the authentication
		 */
		public final static int AUTH_PB = 1;
		/**
		 * Happens when the server is experiencing issues
		 */
		public final static int SERV_EXPERIENCING_ISSUES = 2;
		/**
		 * Happens when something couldn't be parsed
		 */
		public final static int PARSING_ERROR = 3;
		/**
		 * Happens when the user seems to have no connection
		 */
		public final static int INTERNAL_NO_CONNECTION=4;
		
		/**
		 * Could happend when a user cut the connection while requesting some info.
		 */
		public final static int INTERNAL_OTHER = 5;
		public static final int INTERNAL_WRONG_URL = 6;

		/*
		 * JSON errors:
		 */
		/**
		 * Happen when the user's tasks couldn't be parsed.
		 */
		public final static int JSON_USER_TASKS = 100;
		/**
		 * Happen when a user avatar couldn't be parsed
		 */
		public final static int JSON_USER_AVATAR_ERR = 101;
		/**
		 * Happen when a user's personnal info couldn't be parsed
		 */
		public final static int JSON_USER_PERS_INFO = 102;
		/**
		 * Happen when a user's tags couldn't be parsed;
		 */
		public final static int JSON_USER_TAGS = 103;
		
		/**
		 * Happen when a single task couldn't be parsed;
		 */
		public final static int JSON_TASKS_UNPARSABLE = 200;
		
		/**
		 * Happen when a task couldn't be set to "up" or "down"
		 */
		public final static int JSON_TASK_DIRECTION = 210;
		
		/**
		 * Happen when a task couldn't be deleted;
		 */
		public static final int TASK_DELETE_FAIL = 220;

		private int currentError;

		private String errorDetails;
		public WebServiceException(int error) {
			this.currentError=error;
		}
		public WebServiceException(int error, String details) {
			this.currentError=error;
			this.errorDetails = details;
		}
		public int getError() {
			return currentError;
		}
		public String getDetails() {
			return errorDetails;
		}
	}
}
