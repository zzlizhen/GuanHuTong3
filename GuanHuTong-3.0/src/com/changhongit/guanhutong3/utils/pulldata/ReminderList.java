package com.changhongit.guanhutong3.utils.pulldata;

import java.util.ArrayList;

public class ReminderList {
	
	private ArrayList<Reminder> list = new ArrayList<Reminder>();
	private ArrayList<String> idlist = new ArrayList<String>();
	
	public void addReminder(Reminder reminder){
		list.add(reminder);
	}
	
	public void addId(String id){
		idlist.add(id);
	}
	
	public Reminder getReminder(int i){
		return list.get(i);
	}
	
	public String getId(int i){
		return idlist.get(i);
	}
	
	public ArrayList<Reminder> getList(){
		return list;
	}
	
	public ArrayList<String> getIdList(){
		return idlist;
	}

}