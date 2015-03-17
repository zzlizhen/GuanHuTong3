package com.changhongit.guanhutong3.events;


import com.changhongit.guanhutong3.utils.pulldata.Reminder;

public interface EventsModel {
	 public void findItems();
	 public Reminder getItem(int position);
}
