package com.magicmicky.habitrpgmobileapp.habits;

public enum HabitType {
	habit("habit"),
	reward("reward"),
	todo("todo"),
	daily("daily");
	
	private final String name;
	private HabitType(String str) {
		this.name=str;
	}
	/**
	 * Returns a string of the HabitType
	 * @return the habittype
	 */
	public String toString() {
		return this.name;
	}

}
