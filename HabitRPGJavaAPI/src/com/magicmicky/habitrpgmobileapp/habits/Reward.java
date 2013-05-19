package com.magicmicky.habitrpgmobileapp.habits;
/**
 * A reward. Contain a reward that you can see on the website
 * @author MagicMicky
 *
 */
public class Reward extends HabitItem{
	/**
	 * Create a new Reward
	 * @param id the id of the habit
	 * @param notes the notes associated to a habit
	 * @param priority the priority of the habit
	 * @param text the text of the habit
	 * @param value the value (points) of the habit
	 */
	public Reward(String id, String notes, String priority, String text,
			double value) {
		super(id, notes, priority, text, value);
	}

}
