package com.changhongit.guanhutong3.trace;

import com.changhongit.guanhutong3.utils.pulldata.Location;
import com.changhongit.guanhutong3.utils.pulldata.LocationHistoryList;

public interface TraceModel {

    /**
     * 从服务器获取数据
     */
    public void loadHistory(String startTime, String endTime, String imei);
    
    public void assembleData(LocationHistoryList list);

	public LocationHistoryList getList();

	public Location jumpto(int progress);

	public boolean playSwitch();

	public void setPeriod(int period);
	
	public void setSignal(boolean signal);
}
