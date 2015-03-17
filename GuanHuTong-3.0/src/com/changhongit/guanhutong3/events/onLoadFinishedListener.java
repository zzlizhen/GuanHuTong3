package com.changhongit.guanhutong3.events;

import java.util.List;

import com.changhongit.guanhutong3.utils.pulldata.Reminder;

public interface onLoadFinishedListener {
	void onLoadFinished(List<Reminder> items);
}
