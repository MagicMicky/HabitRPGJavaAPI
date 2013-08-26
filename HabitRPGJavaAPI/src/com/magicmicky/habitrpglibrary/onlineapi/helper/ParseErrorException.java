package com.magicmicky.habitrpglibrary.onlineapi.helper;


public class ParseErrorException extends HabitRPGException {
	public static final int HABITRPG_PARSING_ERROR_REGISTRATION=100;
	public static final int HABITRPG_PARSING_ERROR_AUTH = 101;
	public static final int JSON_DAILY_UNPARSABLE = 102;
	public static final int JSON_TODO_UNPARSABLE = 103;
	public static final int JSON_HABIT_UNPARSABLE = 104;
	public static final int JSON_REWARD_UNPARSABLE = 105;
	public static final int JSON_USER_INFO_NOT_FOUND = 106;
	public static final int JSON_USER_LOOK_NOT_FOUND = 107;
	public static final int JSON_USER_TAGS_NOT_FOUND = 108;
	public static final int HABITRPG_DIRECTION_ANSWER_UNPARSABLE = 109;

	public static final int JSON_USER_HAS_NO_TASKS_TAGS = 120;
	public static final int JSON_USER_HAS_NO_DAILIES_TAGS = 121;
	public static final int JSON_USER_HAS_NO_TODOS_TAGS = 122;
	public static final int JSON_USER_HAS_NO_HABITS_TAGS = 123;
	public static final int JSON_USER_HAS_NO_REWARDS_TAGS = 124;
	public static final int TASK_NOT_DELETED = 200;
	
	public ParseErrorException(int id, String message) {
		super(id, message);
	}
	public ParseErrorException(int id) {
		this(id, errorToString(id));
	}
	
	private static String errorToString(int id) {
		String details=null;
		switch(id) {
		case HABITRPG_PARSING_ERROR_REGISTRATION:
			details="Error while registering!";
			break;
		case HABITRPG_PARSING_ERROR_AUTH:
			details="Error while authentication";
			break;
		case JSON_DAILY_UNPARSABLE:
			details="Error: your dailies couldn't be parsed";
			break;
		case JSON_TODO_UNPARSABLE:
			details="Error: your Todos couldn't be parsed";
			break;
		case JSON_HABIT_UNPARSABLE:
			details="Error: your Habits couldn't be parsed";
			break;
		case JSON_REWARD_UNPARSABLE:
			details="Error: your Rewards couldn't be parsed";
			break;
		case JSON_USER_INFO_NOT_FOUND:
			details="Error: your User's information couldn't be parsed";
			break;
		case JSON_USER_LOOK_NOT_FOUND:
			details="Error: your user's look couldn't be parsed";
			break;
		case JSON_USER_TAGS_NOT_FOUND:
			details="Error: your tags couldn't be parsed";
			break;
		case HABITRPG_DIRECTION_ANSWER_UNPARSABLE:
			details="Error: HabitRPG's answer couldn't be parsed";
			break;
		case JSON_USER_HAS_NO_TASKS_TAGS:
			details="Error: you have no tasks tag";
			break;
		case JSON_USER_HAS_NO_DAILIES_TAGS:
			details="Error: you have no dailies tag";
			break;
		case JSON_USER_HAS_NO_TODOS_TAGS:
			details="Error: you have no todos tag";
			break;
		case JSON_USER_HAS_NO_HABITS_TAGS:
			details="Error: you have no habits tag";
			break;
		case JSON_USER_HAS_NO_REWARDS_TAGS:
			details="Error: you have no dailies tag";
		case TASK_NOT_DELETED:
			details="Unable to delete the task";
			break;
			
		}
		return details;
	}
}
