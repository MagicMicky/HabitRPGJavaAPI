package com.magicmicky.habitrpglibrary.habits;


/**
 * A daily item. It contains the item called "Daily" on the website
 * @author MagicMicky
 */
public class Daily extends HabitItem{
	private final static HabitType type=HabitType.daily;
	private final static String[] days = {"m","t","w","th","f","s","su"};
	private boolean completed;
	private boolean[] repeat;
	private long lastCompleted;
	private int streak;
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
	public Daily(String id, String notes, String priority, String text,
			double value, boolean completed, boolean[] repeat, int streak, long lastCompleted) {
		this(id, notes, priority, text, value,completed,repeat,lastCompleted);
		this.setStreak(streak);
	}
	public Daily() {
		super();
		this.setCompleted(false);
		boolean[] r = new boolean[7];
		this.setRepeat(r);
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
		return type.toString();
	}
	@Override
	public String getJSONString() {
		StringBuilder json = new StringBuilder()
		.append("{")
			.append(super.getJSONBaseString());
			if(this.getRepeat() != null) {
				json.append("\"repeat\":{");
				for(int i=0;i<7;i++) {
					json.append("\"").append(Daily.days[i]).append("\": ").append(this.getRepeat()[i]).append(",");
				}
				json =json.deleteCharAt(json.length()-1);
				json.append("},");
			}
			json.append("\"streak\":").append(this.getStreak()).append(",");
			json.append("\"completed\":" + (this.isCompleted() ? "true":"false"))
		.append("}");
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
	/**
	 * @return the streak
	 */
	public int getStreak() {
		return streak;
	}
	/**
	 * @param streak the streak to set
	 */
	public void setStreak(int streak) {
		this.streak = streak;
	}


}
