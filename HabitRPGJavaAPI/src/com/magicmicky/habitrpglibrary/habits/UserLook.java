package com.magicmicky.habitrpglibrary.habits;
/**
 * Class storing a user look.
 * @author MagicMicky
 *
 */
public class UserLook {
	private String gender;
	private String hair;
	private String skin;
	private String armorSet;
	private boolean showHelm;
	private UserItems items;
	public UserLook(String gender, String hair, String skin, String armorSet, boolean showHelm
			, int armor, int head, int shield, int weapon) {
		this.gender=gender;
		this.hair=hair;
		this.skin=skin;
		this.armorSet=armorSet;
		this.showHelm=showHelm;
		setItems(new UserItems(armor,head,shield,weapon));
	}
	public UserLook () {
		setItems(new UserItems());
	}
	/**
	 * @return the gender of the user
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * Gender should be either "m" or "f"
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the hair of the user.
	 */
	public String getHair() {
		return hair;
	}
	/**
	 * Hair can be either "blond", "brown", "black" or "white" 
	 * @param hair the hair to set
	 */
	public void setHair(String hair) {
		this.hair = hair;
	}
	/**
	 * @return the skin of the user
	 */
	public String getSkin() {
		return skin;
	}
	/**
	 * Skin should be either "orc", "asian", "black", "white", "dead"
	 * @param skin the skin to set
	 */
	public void setSkin(String skin) {
		this.skin = skin;
	}
	/**
	 * @return the armorSet
	 */
	public String getArmorSet() {
		return armorSet;
	}
	/**
	 * The version of the armorSet "v1", or "v2". Should be used to determine the head and the armor for females.
	 * @param armorSet the armorSet to set
	 */
	public void setArmorSet(String armorSet) {
		this.armorSet = armorSet;
	}
	/**
	 * @return the showHelm
	 */
	public boolean isShowHelm() {
		return showHelm;
	}
	/**
	 * Whether or not the helm should be shown
	 * @param showHelm the showHelm to set
	 */
	public void setShowHelm(boolean showHelm) {
		this.showHelm = showHelm;
	}

	@Override
	/**
	 * Return a string to show the UserLook easily.
	 */
	public String toString() {
		return this.gender + ": armor" + this.getItems().getArmor() + " head" + this.getItems().getHead()
				+ " hair" + this.hair + " skin" + this.getSkin()
				+ " weapon" + this.getItems().getWeapon() + " shield" + this.getItems().getShield();
		
	}
	/**
	 * @return the items
	 */
	public UserItems getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(UserItems items) {
		this.items = items;
	}
	public static class UserItems {
		/*
		 * items
		 */
		private int armor;
		private int head;
		private int shield;
		private int weapon;

		public UserItems() {
			
		}
		public UserItems(int armor,int head, int shield, int weapon) {
			this.setArmor(armor);
			this.setHead(head);
			this.setShield(shield);
			this.setWeapon(weapon);
		}
	/**
	 * @return the armor
	 */
	public int getArmor() {
		return armor;
	}
	/**
	 * the armor of the user ( a 0-5 value)
	 * @param armor the armor to set
	 */
	public void setArmor(int armor) {
		this.armor = armor;
	}
	/**
	 * @return the head
	 */
	public int getHead() {
		return head;
	}
	/**
	 * The helmet of the user (0-5)
	 * @param head the head to set
	 */
	public void setHead(int head) {
		this.head = head;
	}
	/**
	 * @return the shield
	 */
	public int getShield() {
		return shield;
	}
	/**
	 * The shield of the user (0-5 value), 0 being no shield
	 * @param shield the shield to set
	 */
	public void setShield(int shield) {
		this.shield = shield;
	}
	/**
	 * @return the weapon
	 */
	public int getWeapon() {
		return weapon;
	}
	/**
	 * The weapon of the user (0-6 value)
	 * @param weapon the weapon to set
	 */
	public void setWeapon(int weapon) {
		this.weapon = weapon;
	}
	
}
}
