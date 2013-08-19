package com.magicmicky.habitrpgmobileapp.habits;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

/**
 * Custom Item that regroup all the others.
 * @author MagicMicky
 *
 */
public abstract class HabitItem {
	private String id;
	private String notes;
	private String priority;
	private String text;
	private double value;
	private List<String> tagsId;
	/**
	 * Create a new HabitItem from what is necessary
	 * @param id the id of the habit
	 * @param notes the notes associated to a habit
	 * @param priority the priority of the habit
	 * @param text the text of the habit
	 * @param value the value (points) of the habit
	 */
	public HabitItem(String id, String notes, String priority, String text, double value) {
		this.setId(id);
		this.setNotes(notes);
		this.setPriority(priority);
		this.setText(text);
		this.setValue(value);
		this.tagsId=new ArrayList<String>();

	}
	public HabitItem() {
		this("","","!","",0);
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
	
	/**
	 * @return the tagsId
	 */
	public List<String> getTagsId() {
		return tagsId;
	}
	/**
	 * @param tagsId the tagsId to set
	 */
	public void setTagsId(List<String> tagsId) {
		this.tagsId = tagsId;
	}
	
	public boolean isTagged(List<String> tags) {
		if(this.getTagsId()==null) {
			System.out.println("getTags is null!!!");
		}
		if(this.getTagsId() != null && this.getTagsId().containsAll(tags))
			return true;
		
		return false;
	}
	/**
	 * Returns a string of the type of the HabitItem
	 * @return the string of the Item type
	 */
	protected abstract String getType();
	/**
	 * Returns a JSONString of the current HabitItem
	 * @return the Item in json (ready to send as POST)
	 */
	public abstract String getJSONString();

	/**
	 * Creates a JSON String for this HabitItem using the basic information.<br>
	 * Doesn't have the necessary open and close brackets to create an item.
	 * @return
	 */
	protected String getJSONBaseString() {
		StringBuilder json = new StringBuilder();
		if(this.getId()!=null)
			json.append("\"id\":").append(JSONObject.quote(this.getId())).append(",");
		json
			.append("\"type\":\"").append(this.getType()).append("\"," )
			.append("\"text\":").append(JSONObject.quote(this.getText())).append("," );
			if(this.getNotes()!=null && !this.getNotes().contentEquals(""))
				json.append("\"notes\":").append(JSONObject.quote(this.getNotes())).append("," );
			json.append("\"value\":").append(this.getValue()).append(",");
			if(this.getTagsId()!=null && this.getTagsId().size()!=0) {
				json.append("\"tags\":{");
				for(String tagId : this.getTagsId()) {
					json.append("").append(JSONObject.quote(tagId)).append(":").append("true").append(",");
				}
				json.deleteCharAt(json.length()-1);
				json.append("},");
			}
		return 	json.toString();
	}
}
