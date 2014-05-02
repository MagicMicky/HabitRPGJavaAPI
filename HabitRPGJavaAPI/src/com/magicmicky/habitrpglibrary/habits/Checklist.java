package com.magicmicky.habitrpglibrary.habits;

import java.util.ArrayList;
import java.util.List;

public class Checklist {
	private List<ChecklistItem> checklist;
	
	public Checklist() {
		this.checklist = new ArrayList<ChecklistItem>();
	}
	
	public void addItem(ChecklistItem item) {
		this.checklist.add(item);
	}


	public void toggleItem(String id) {
		boolean flag = false;
		for(int i=0;i<this.checklist.size() && !flag; i++) {
			ChecklistItem it = this.checklist.get(i);
			if(it.getId().equals(id)) {
				it.setCompleted(!it.isCompleted());
				flag=true;
			}
		}
	}
	public List<ChecklistItem> getItems() {
		return checklist;
	}
	
	public int getSize() {
		return checklist.size();
	}
	

public static class ChecklistItem {
	private String text;
	private String id;
	private boolean completed;
	public ChecklistItem() {
		this(null,null);
	}
	public ChecklistItem(String id, String text) {
		this(text,id,false);
	}
	public ChecklistItem(String id,String text, boolean completed) {
		this.setText(text);
		this.setId(id);
		this.setCompleted(completed);
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}


}