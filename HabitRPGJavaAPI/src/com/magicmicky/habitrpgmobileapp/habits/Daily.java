package com.magicmicky.habitrpgmobileapp.habits;

public class Daily extends HabitItem{
	private boolean completed;
	private boolean[] repeat;
	public Daily(String id, String notes, String priority, String text,
			double value, boolean completed, boolean[] repeat) {
		super(id, notes, priority, text, value);
		this.setCompleted(completed);
		this.setRepeat(repeat);
	}
	/**
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}
	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	/**
	 * @return the repeat
	 */
	public boolean[] getRepeat() {
		return repeat;
	}
	/**
	 * @param repeat the repeat to set
	 */
	public void setRepeat(boolean[] repeat) {
		this.repeat = repeat;
	}


}
