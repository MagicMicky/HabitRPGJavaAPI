package com.magicmicky.habitrpglibrary.onlineapi;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.json.JSONObject;

import com.magicmicky.habitrpglibrary.habits.Reward.SpecialReward;
import com.magicmicky.habitrpglibrary.habits.User;
import com.magicmicky.habitrpglibrary.habits.UserLook;
import com.magicmicky.habitrpglibrary.onlineapi.WebServiceInteraction.Answer;
import com.magicmicky.habitrpglibrary.onlineapi.helper.ParseErrorException;
import com.magicmicky.habitrpglibrary.onlineapi.helper.ParserHelper;

public class BuyItem extends WebServiceInteraction {
	private final static String CMD = "user/buy/";
	private SpecialReward itemBought;
	public BuyItem(OnHabitsAPIResult callback, HostConfig config, SpecialReward itemBought) {
		super(CMD + itemBought.getType(), callback, config);
		this.itemBought = itemBought;
	}

	@Override
	protected HttpRequestBase getRequest() {
		return new HttpPost();
	}

	@Override
	protected Answer findAnswer(JSONObject answer) {
		// TODO Auto-generated method stub
		return new ItemData(answer,this.getCallback());
	}
	private class ItemData extends Answer {

		public ItemData(JSONObject obj, OnHabitsAPIResult callback) {
			super(obj, callback);
		}
		public void parse() {
			UserLook.UserItems userItems;
			try {
				userItems = ParserHelper.parseUserItems(this.getObject());
				callback.onUserItemsReceived(userItems, itemBought);
			} catch (ParseErrorException e) {
				callback.onError(e);
			}
		}
		
	}

}
