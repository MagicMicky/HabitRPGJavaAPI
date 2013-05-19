package com.magicmicky.habitrpgmobileapp.onlineapi;

import com.magicmicky.habitrpgmobileapp.habits.User;

public interface OnHabitsAPIResult {
	public void onUserReceived(User user);
	void onPostResult(double xp, double hp, double gold, double lvl,
			double delta);
	void onPreResult();
	void onError(String message);

}
