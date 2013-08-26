package com.magicmicky.habitrpglibrary.onlineapi.helper;

public class HabitRPGException extends Exception {
	/**
	 * Happens when the server api call wasn't found.
	 */
	public final static int HABITRPG_SERVER_API_CALL_NOT_FOUND=-1;
	
	/**
	 * Happens when the server is experiencing issues
	 */
	public final static int SERV_EXPERIENCING_ISSUES = 2;
	/**
	 * Happens when something couldn't be parsed
	 * /
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
	public static final int HABITRPG_REGISTRATION_ERROR = 7;


	private int currentError;

	public HabitRPGException(int id) {
		this(id,errorToString(id));
		this.currentError=id;
	}
	public HabitRPGException(int error, String details) {
		super(details);
		this.currentError=error;
	}
	public int getErrorNumber() {
		return currentError;
	}
	private static String errorToString(int id) {
		String errorMessage="";
		switch(id) {
			case HABITRPG_SERVER_API_CALL_NOT_FOUND:
			case INTERNAL_WRONG_URL:
				errorMessage="The server wasn't found. Please check your hosts settings.";
				break;
			case SERV_EXPERIENCING_ISSUES:
				errorMessage="HabitRPG's server is having some trouble. Feel free to switch to the beta server";
				break;
			case INTERNAL_NO_CONNECTION:
				errorMessage="Error. Please check your connection";
				break;
			case INTERNAL_OTHER:
				errorMessage="An internal unknown  error happend!";
				break;
			case HABITRPG_REGISTRATION_ERROR:
				errorMessage="There has been an error during the registration process! Please try again.";
			default:
				errorMessage="An unknown error happened!";
				break;
			
		}
		return errorMessage;
	}
}
