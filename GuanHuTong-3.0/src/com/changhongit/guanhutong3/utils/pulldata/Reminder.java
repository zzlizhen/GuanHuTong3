package com.changhongit.guanhutong3.utils.pulldata;


public class Reminder {
	
	private String id;
	private String indexNum;
	private String imei;
	private int ReminderType;
	public static final int ONE = 0;
	public static final int DAY = 1;
	public static final int WEEK = 2;
	private boolean[] week = {false,false,false,false,false,false,false};
	private String reminderTime;
	private boolean swichFlag;
	
	public boolean isSwichFlag() {
		return swichFlag;
	}
	public void setSwichFlag(boolean swichFlag) {
		this.swichFlag = swichFlag;
	}
	private String content;
	
	public String getId() {
		return id;
	}
	public Reminder setId(String id) {
		this.id = id;
		return this;
	}
	public String getImei() {
		return imei;
	}
	public Reminder setImei(String imei) {
		this.imei = imei;
		return this;
	}
	public int getReminderType() {
		return ReminderType;
	}
	public Reminder setReminderType(int reminderType) {
		ReminderType = reminderType;
		return this;
	}
	public boolean[] getWeek() {
		return week;
	}
	public Reminder setWeek(boolean[] week) {
		this.week = week;
		return this;
	}
	public String getReminderTime() {
		return reminderTime;
	}
	public Reminder setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
		return this;
	}
	public String getContent() {
		return content;
	}
	public Reminder setContent(String content) {
		this.content = content;
		return this;
	}
	public String getIndexNum() {
		return indexNum;
	}
	public void setIndexNum(String indexNum) {
		this.indexNum = indexNum;
	}
	
	
	
	

}
