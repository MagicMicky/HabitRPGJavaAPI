package com.magicmicky.habitrpglibrary.habits;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Contains the User that uses the application.
 * @author MagicMicky 
 *
 */
public class User {
	private List<HabitItem> items;
	private String name;
	private double hp,maxHp=0,xp, maxXp=0,gp;
	private int lvl;
	private int dayStart;
	private UserLook look;
	private Map<String, String> tags;
	private int timeZoneOffset;
	/**
	 * Create a new User
	 */
	public User() {
		this(new ArrayList<HabitItem>());
	}
	/**
	 * Create a new User based on a list of HabitItem
	 * @param items the user's list of items
	 */
	public User(List<HabitItem> items) {
		this.setItems(items);
	}

	/**
	 * Get the tasks of an user, with or without the custom rewards
	 * @param withCustomRewardsUpgrade whether or not you want the customrewards integrated to the HabitItem list
	 * @return the list of all the tasks of this user
	 */
	public List<HabitItem> getItems(boolean withCustomRewardsUpgrade) {
		if(withCustomRewardsUpgrade) {
			List<HabitItem> allItems = new ArrayList<HabitItem>(items);
		/*	if(getLook() != null && getLook().getItems() != null && getLook().getItems().toRewardsUpgrade()!=null)
				allItems.addAll(getLook().getItems().toRewardsUpgrade());*/
			return allItems;
		}
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<HabitItem> items) {
		this.items = items;
	}
	/**
	 * @return the user's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the user's hp
	 */
	public double getHp() {
		return hp;
	}
	/**
	 * @param hp the hp to set
	 */
	public void setHp(double hp) {
		this.hp = hp;
	}
	/**
	 * @return the user's maxHp
	 */
	public double getMaxHp() {
		if(maxHp!=0)
			return maxHp;
		return 50;
	}
	/**
	 * @param maxHp the maxHp to set
	 */
	public void setMaxHp(double maxHp) {
		this.maxHp = maxHp;
	}
	/**
	 * @return the user's xp
	 */
	public double getXp() {
		return xp;
	}
	/**
	 * @param d the xp to set
	 */
	public void setXp(double d) {
		this.xp = d;
	}
	/**
	 * @return the user's maxXp
	 */
	public double getMaxXp() {
		if(maxXp != 0) {
			return maxXp;
		}
		return Math.round(((Math.pow(this.getLvl(), 2) * 0.25) + (10 * this.getLvl()) + 139.75) / 10)*10;
	}
	/**
	 * @param maxXp the maxXp to set
	 */
	public void setMaxXp(double maxXp) {
		this.maxXp = maxXp;
	}
	/**
	 * @return the user's lvl
	 */
	public int getLvl() {
		return lvl;
	}
	/**
	 * @param lvl the lvl to set
	 */
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	/**
	 * @return the user's gp
	 */
	public double getGp() {
		return gp;
	}
	/**
	 * @param gp the gp to set
	 */
	public void setGp(double gp) {
		this.gp = gp;
	}
	/**
	 * @return the look
	 */
	public UserLook getLook() {
		return look;
	}
	/**
	 * @param look the look to set
	 */
	public void setLook(UserLook look) {
		this.look = look;
	}
	/**
	 * @param task the task to add to this user
	 */
	public void addTask(HabitItem task) {
		this.items.add(task);
	}
	
	
	public void editTask(HabitItem task) {
		boolean flag=false;
		int i=0;
		while(i<this.items.size() && flag!=true) {
			if(this.items.get(i).getId().equals(task.getId())) {
				flag=true;
			} else {
				i++;
			}
		}
		if(flag) {
			this.items.set(i, task);
		}
	}
	/**
	 * 
	 */
	public void deleteTask(HabitItem task) {
		if(this.items.contains(task)) {
			this.items.remove(task);
			System.out.println("NO need for search");
		} else {
			boolean flag=false;
			int i=0;
			while(i<this.items.size() && flag!=true) {
				if(this.items.get(i).getId().equals(task.getId())) {
					flag=true;
				} else {
					i++;
				}
			}
			if(flag) {
				this.items.remove(i);
			}
			
		}
	}
	/**
	 * @return the dayStart
	 */
	public int getDayStart() {
		return dayStart;
	}
	/**
	 * @param dayStart the dayStart to set
	 */
	public void setDayStart(int dayStart) {
		this.dayStart = dayStart;
	}
	/**
	 * @return the tags
	 */
	public Map<String,String> getTags() {
		return tags;
	}
	/**
	 * @param tags2 the tags to set
	 */
	public void setTags(Map<String, String> tags2) {
		this.tags = tags2;
	}
	/**
	 * @return the timeZoneOffset
	 */
	public int getTimeZoneOffset() {
		return timeZoneOffset;
	}
	/**
	 * @param timeZoneOffset the timeZoneOffset to set
	 */
	public void setTimeZoneOffset(int timeZoneOffset) {
		this.timeZoneOffset = timeZoneOffset;
	}
	
	/**
	 * @return true if the user is dead, false otherwise
	 */
	public boolean isUserDead() {
		return this.getHp()<=0;
	}
}
