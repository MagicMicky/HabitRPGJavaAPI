package com.magicmicky.habitrpgmobileapp.habits;

import java.util.ArrayList;
import java.util.List;

public class User {
	private List<HabitItem> items;
	private String name;
	private double hp,maxHp,xp, maxXp,gp;
	private int lvl;
	public User() {
		this(new ArrayList<HabitItem>());
	}
	public User(List<HabitItem> items) {
		this.setItems(items);
	}
	/**
	 * @return the items
	 */
	public List<HabitItem> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<HabitItem> items) {
		this.items = items;
	}
	/**
	 * @return the name
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
	 * @return the hp
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
	 * @return the maxHp
	 */
	public double getMaxHp() {
		return maxHp;
	}
	/**
	 * @param maxHp the maxHp to set
	 */
	public void setMaxHp(double maxHp) {
		this.maxHp = maxHp;
	}
	/**
	 * @return the xp
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
	 * @return the maxXp
	 */
	public double getMaxXp() {
		return maxXp;
	}
	/**
	 * @param maxXp the maxXp to set
	 */
	public void setMaxXp(double maxXp) {
		this.maxXp = maxXp;
	}
	/**
	 * @return the lvl
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
	 * @return the gp
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
}
