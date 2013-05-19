package com.magicmicky.habitrpgmobileapp.habits;

public class ToDo extends HabitItem{
	private boolean completed;
	private String date;
	public ToDo(String id, String notes, String priority, String text,
			double value, boolean completed, String date) {
		super(id, notes, priority, text, value);
		this.setCompleted(completed);
		this.setDate(date);
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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

}
