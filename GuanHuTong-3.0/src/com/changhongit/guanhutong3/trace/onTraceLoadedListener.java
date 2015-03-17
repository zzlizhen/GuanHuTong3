package com.changhongit.guanhutong3.trace;

import com.changhongit.guanhutong3.utils.pulldata.Location;
import com.changhongit.guanhutong3.utils.pulldata.LocationHistoryList;

public interface onTraceLoadedListener {
	
	public void onFinish(LocationHistoryList list);
	
	public void onNext(int progress,Location loc);
	
	public void onNetWorkError();

	public void onEmptyList();

}
