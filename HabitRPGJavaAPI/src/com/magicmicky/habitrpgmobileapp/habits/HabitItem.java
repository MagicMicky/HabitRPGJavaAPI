package com.magicmicky.habitrpgmobileapp.habits;


public abstract class HabitItem {
	private String id;
	private String notes;
	private String priority;
	private String text;
	private double value;
	
	public HabitItem(String id, String notes, String priority, String text, double value) {
		this.setId(id);
		this.setNotes(notes);
		this.setPriority(priority);
		this.setText(text);
		this.setValue(value);
		
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		if(priority != null && priority.matches("!+")) {
			this.priority = priority;
		} else {
			this.priority="!";
		}
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}


}
