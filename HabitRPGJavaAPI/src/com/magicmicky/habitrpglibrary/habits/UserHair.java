package com.magicmicky.habitrpglibrary.habits;

public class UserHair {
	private int mustache, beard, bangs, base;
	private String color;
	
	public UserHair(int mustache, int beard, int bangs, int base, String color) {
		this.mustache=mustache;
		this.beard = beard;
		this.bangs = bangs;
		this.base =base;
		this.color = color;
	}
	/**
	 * @return the mustache
	 */
	public int getMustache() {
		return mustache;
	}
	/**
	 * @param mustache the mustache to set
	 */
	public void setMustache(int mustache) {
		this.mustache = mustache;
	}
	/**
	 * @return the beard
	 */
	public int getBeard() {
		return beard;
	}
	/**
	 * @param beard the beard to set
	 */
	public void setBeard(int beard) {
		this.beard = beard;
	}
	/**
	 * @return the bangs
	 */
	public int getBangs() {
		return bangs;
	}
	/**
	 * @param bangs the bangs to set
	 */
	public void setBangs(int bangs) {
		this.bangs = bangs;
	}
	/**
	 * @return the base
	 */
	public int getBase() {
		return base;
	}
	/**
	 * @param base the base to set
	 */
	public void setBase(int base) {
		this.base = base;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
}
