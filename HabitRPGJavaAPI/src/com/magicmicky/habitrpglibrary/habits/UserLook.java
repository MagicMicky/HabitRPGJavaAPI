package com.magicmicky.habitrpglibrary.habits;

/**
 * Class storing a user look.
 * @author MagicMicky
 *
 */
public class UserLook {
	private String skin;
	private String shirtColor;
	private UserHair hair;
	private String size;
	private UserItems items;
	public UserLook(UserHair hair, String skin, String armorSet, String size, String shirtColor
			, String armor, String head, String shield, String weapon) {
		this.hair=hair;
		this.skin=skin;
		this.size = size;
		this.shirtColor = shirtColor;
		setItems(new UserItems(armor,head,shield,weapon));
	}
	public UserLook () {
		setItems(new UserItems());
	}
	/**
	 * @return the hair of the user.
	 */
	public UserHair getHair() {
		return hair;
	}
	/**
	 * @param hair the hair to set
	 */
	public void setHair(UserHair hair) {
		this.hair = hair;
	}
	/**
	 * @return the skin of the user
	 */
	public String getSkin() {
		return skin;
	}
	/**
	 * @param skin the skin to set
	 */
	public void setSkin(String skin) {
		this.skin = skin;
	}

	@Override
	/**
	 * Return a string to show the UserLook easily.
	 */
	public String toString() {
		/*return  + ": armor" + this.getItems().getArmor() + " head" + this.getItems().getHead()
				+ " hair" + this.hair + " skin" + this.getSkin()
				+ " weapon" + this.getItems().getWeapon() + " shield" + this.getItems().getShield();*/
		return "";
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
	/**
	 * @return the shirtColor
	 */
	public String getShirtColor() {
		return shirtColor;
	}
	/**
	 * @param shirtColor the shirtColor to set
	 */
	public void setShirtColor(String shirtColor) {
		this.shirtColor = shirtColor;
	}
	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	public static class UserItems {
		/*
		 * items
		 */
		private String armor;
		private String head;
		private String shield;
		private String weapon;

		public UserItems() {
			
		}
		public UserItems(String armor,String head, String shield, String weapon) {
			this.setArmor(armor);
			this.setHead(head);
			this.setShield(shield);
			this.setWeapon(weapon);
		}
		/**
		 * @return the armor
		 */
		public String getArmor() {
			return armor;
		}
		/**
		 * the armor of the user ( a 0-5 value)
		 * @param armor the armor to set
		 */
		public void setArmor(String armor) {
			this.armor = armor;
		}
		/**
		 * @return the head
		 */
		public String getHead() {
			return head;
		}
		/**
		 * The helmet of the user (0-5)
		 * @param head the head to set
		 */
		public void setHead(String head) {
			this.head = head;
		}
		/**
		 * @return the shield
		 */
		public String getShield() {
			return shield;
		}
		/**
		 * The shield of the user (0-5 value), 0 being no shield
		 * @param shield the shield to set
		 */
		public void setShield(String shield) {
			this.shield = shield;
		}
		/**
		 * @return the weapon
		 */
		public String getWeapon() {
			return weapon;
		}
		/**
		 * The weapon of the user (0-6 value)
		 * @param weapon the weapon to set
		 */
		public void setWeapon(String weapon) {
			this.weapon = weapon;
		}
		/*public List<Reward> toRewards() {
			List<Reward> specRewards = new ArrayList<Reward>();
			specRewards.add(new SpecialReward(this.getWeapon(), "weapon"));
			specRewards.add(new SpecialReward(this.getArmor(), "armor"));
			specRewards.add(new SpecialReward(this.getHead(), "head"));
			specRewards.add(new SpecialReward(this.getShield(), "shield"));
			return specRewards;
		}
		public List<Reward> toRewardsUpgrade() {
			List<Reward> specRewards = new ArrayList<Reward>();
			if(this.getWeapon()<6)
				specRewards.add(new SpecialReward(this.getWeapon() +1, "weapon"));
			if(this.getArmor() < 5)
				specRewards.add(new SpecialReward(this.getArmor() +1, "armor"));
			if(this.getHead()< 5)
				specRewards.add(new SpecialReward(this.getHead()+1, "head"));
			if(this.getShield()<5) 
				specRewards.add(new SpecialReward(this.getShield()+1, "shield"));
			return specRewards;
		}*/
	}
}
