package com.magicmicky.habitrpgmobileapp.habits;
/**
 * A ToDo task that you can see of the website
 * You can set a complete date to a ToDo, and you can complete them using a boolean
 * @author MagicMicky
 *
 */
public class ToDo extends HabitItem{
	private boolean completed;
	private String date;
	/**
	 * Construct a daily based on all the information needed
	 * @param id the id of the daily
	 * @param notes the notes associated to a daily
	 * @param priority the priority of the daily
	 * @param text the text of the daily
	 * @param value the value (points) of the daily
	 * @param completed whether or not the daily is completed
	 * @param date the due date
	 */
	public ToDo(String id, String notes, String priority, String text,
			double value, boolean completed, String date) {
		super(id, notes, priority, text, value);
		this.setCompleted(completed);
		this.setDate(date);
	}

	/**
	 * @return if the todo is completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 *  Set whether or not the todo is completed
	 * @param completed
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	/**
	 * @return the due date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Set the due date
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

}
