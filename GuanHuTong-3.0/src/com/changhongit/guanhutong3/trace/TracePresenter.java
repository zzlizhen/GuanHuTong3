package com.changhongit.guanhutong3.trace;

import com.changhongit.guanhutong3.utils.pulldata.LocationHistoryList;

public interface TracePresenter {

    public void onResume();

	public void OnPause();

	public void jumpto(int progress);

	public boolean playSwitch();

	public void loadHistory(String startTime, String endTime,
			String imei);
	
	public void setPeriod(int period);
	
	public LocationHistoryList getlist();
	
	public void setSignal(boolean signal);
}
