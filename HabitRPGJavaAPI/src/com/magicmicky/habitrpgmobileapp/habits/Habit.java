package com.magicmicky.habitrpgmobileapp.habits;

public class Habit extends HabitItem{

	private boolean up;
	private boolean down;
	public Habit(String id, String notes, String priority, String text, double value, boolean up, boolean down) {
		super(id, notes, priority, text, value);
		this.setUp(up);
		this.setDown(down);
	}
	/**
	 * @return the up
	 */
	public boolean isUp() {
		return up;
	}
	/**
	 * @param up the up to set
	 */
	public void setUp(boolean up) {
		this.up = up;
	}
	/**
	 * @return the down
	 */
	public boolean isDown() {
		return down;
	}
	/**
	 * @param down the down to set
	 */
	public void setDown(boolean down) {
		this.down = down;
	}
}
