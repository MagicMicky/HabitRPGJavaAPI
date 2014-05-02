package com.magicmicky.habitrpglibrary.habits;

import java.util.List;

import org.json.JSONObject;

import com.magicmicky.habitrpglibrary.habits.Checklist.ChecklistItem;


/**
 * A ToDo task that you can see of the website
 * You can set a complete date to a ToDo, and you can complete them using a boolean
 * @author MagicMicky
 *
 */
public class ToDo extends HabitItem{
	private final static HabitType type=HabitType.todo;
	private boolean completed;
	private String date;
	private Checklist checklist;
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
	public ToDo(String id, String notes, Integer priority, String text,
			double value, boolean completed, String date, Checklist checklist) {
		super(id, notes, priority, text, value);
		this.setCompleted(completed);
		this.setDate(date);
		this.setChecklist(checklist);
	}

	public ToDo() {
		super();
		this.setCompleted(false);
		this.setDate(null);
		this.setChecklist(new Checklist());
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
	/**
	 * Retrieve the checklist
	 * @return the checklist
	 */
	public Checklist getChecklist() {
		return this.checklist;
	}
	/**
	 * Sets the checklist
	 * @param checklist the new checklist
	 */
	public void setChecklist(Checklist checklist) {
		this.checklist = checklist;
	}
	
	@Override
	protected String getType() {
		return type.toString();
	}

	@Override
	public String getJSONString() {
		StringBuilder json = new StringBuilder()
		.append("{")
		.append(super.getJSONBaseString());
		if(this.getDate()!=null && this.getDate()!="")
			json.append("\"date\":\"").append(this.getDate()).append("\",");
		json.append("\"completed\":").append((this.isCompleted() ? "true":"false"));
		json.append(",")
		.append("\"checklist\":[");

		if(this.getChecklist() != null && !this.getChecklist().getItems().isEmpty()) {
			for(ChecklistItem item : this.getChecklist().getItems()) {
				json.append("{")
					.append("\"text\":").append(JSONObject.quote(item.getText())).append(",")
					.append("\"id\":").append(JSONObject.quote(item.getId())).append(",")
					.append("\"completed\":").append(item.isCompleted() ? "true" : "false")
					.append("},");
			}
			json.deleteCharAt(json.length()-1);
		}
		json.append("]")
		.append("}" );
		return json.toString();
	}



}
