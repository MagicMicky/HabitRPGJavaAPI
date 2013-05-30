package com.magicmicky.habitrpgmobileapp.habits;

/**
 * A daily item. It contains the item called "Daily" on the website
 * @author MagicMicky
 */
public class Daily extends HabitItem{
	private final static HabitType type=HabitType.daily;
	private boolean completed;
	private boolean[] repeat;
	private long lastCompleted;
	/**
	 * Construct a daily based on all the information needed
	 * @param id the id of the daily
	 * @param notes the notes associated to a daily
	 * @param priority the priority of the daily
	 * @param text the text of the daily
	 * @param value the value (points) of the daily
	 * @param completed whether or not the daily is completed
	 * @param repeat when does it repeat?
	 */
	public Daily(String id, String notes, String priority, String text,
			double value, boolean completed, boolean[] repeat) {
		super(id, notes, priority, text, value);
		this.setCompleted(completed);
		this.setRepeat(repeat);
	}
	public Daily(String id, String notes, String priority, String text,
			double value, boolean completed, boolean[] repeat, long lastCompleted) {
		this(id, notes, priority, text, value,completed,repeat);
		this.setLastCompleted(lastCompleted);
	}
	/**
	 * @return if the daily is completed
	 */
	public boolean isCompleted() {
		return completed;
	}
	/**
	 *  Set whether or not the daily is completed
	 * @param completed
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	/**
	 * @return the repeat array.<br/>
	 * This array contains 7 values, one for each days, starting from monday.
	 */
	public boolean[] getRepeat() {
		return repeat;
	}
	/**
	 * @param repeat the repeat array to set
	 */
	public void setRepeat(boolean[] repeat) {
		this.repeat = repeat;
	}
	@Override
	protected String getType() {
		// TODO Auto-generated method stub
		return type.toString();
	}
	@Override
	public String getJSONString() {
		StringBuilder json = new StringBuilder();
		json.append("{\"type\":\"" + this.getType() + "\"," );
		json.append("\"text\":\"" + this.getText() + "\"," );
		json.append("\"value\":0," );
		if(this.getNotes()!=null && !this.getNotes().contentEquals(""))
			json.append("\"notes\":\"" + this.getNotes() + "\"," );
		json.append("\"completed\":" + (this.isCompleted() ? "true":"false") + "}" );
		return json.toString();
	}
	/**
	 * @return the lastCompleted
	 */
	public long getLastCompleted() {
		return lastCompleted;
	}
	/**
	 * @param lastCompleted the lastCompleted to set
	 */
	public void setLastCompleted(long lastCompleted) {
		this.lastCompleted = lastCompleted;
	}


}
