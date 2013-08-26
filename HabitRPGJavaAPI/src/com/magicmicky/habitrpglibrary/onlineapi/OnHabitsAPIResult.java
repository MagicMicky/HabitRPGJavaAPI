package com.magicmicky.habitrpglibrary.onlineapi;

import com.magicmicky.habitrpglibrary.habits.HabitItem;
import com.magicmicky.habitrpglibrary.habits.User;
import com.magicmicky.habitrpglibrary.habits.UserLook;
import com.magicmicky.habitrpglibrary.habits.UserLook.UserItems;
import com.magicmicky.habitrpglibrary.onlineapi.helper.HabitRPGException;
import com.magicmicky.habitrpglibrary.onlineapi.helper.ParseErrorException;
/**
 * This is the callback interface. The functions here are called by the different POST, PUT, GET, and DELETE
 * methods, and notifies the application of changes
 * @author MagicMicky
 *
 */
public interface OnHabitsAPIResult {
	
	/**
	 * This is the function called when a new user is created
	 * @param user the new user
	 * @param ApiToken the user's API token
	 * @param ApiUser the user's API User token
	 */
	public void onNewUser(String ApiToken, String ApiUser);
	
	/**
	 * This is the function called once a user have been updated
	 * @param user
	 */
	public void onUserReceived(User user);
	
	public void onUserItemsReceived(UserItems userLook);
	/**
	 * This is the callback called when we have a result from an update Tasks
	 * @param xp
	 * @param hp
	 * @param gold
	 * @param lvl
	 * @param delta
	 */
	void onPostResult(double xp, double hp, double gold, double lvl,
			double delta);
	/**
	 * This is called before starting every actions
	 */
	void onPreResult();
	/**
	 * This is called when there has been an error from the API
	 * @param message an optional error message
	 */
	//void onError(WebServiceInteraction.WebServiceException error);
	void onError(HabitRPGException error);

	/**
	 * This is called when a new Task is added/updated
	 * @param task
	 */
	void onPostTaskAnswer(HabitItem task);

	/**
	 * This is called when a task have been deleted
	 * @param deletedTask the deleted task
	 */
	void onDeletedTask(HabitItem deletedTask);
	
	/**
	 * Answer gotten agter a task have been edited
	 * @param item
	 */
	public void onEditTaskAnswer(HabitItem item);

	/**
	 * Used when an user connects to HabitRPG
	 * @param api_t the API token
	 * @param user_t the user token
	 */
	public void onUserConnected(String api_t, String user_t);
}
