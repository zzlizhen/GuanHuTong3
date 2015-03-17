package com.changhongit.guanhutong3.main;

import com.changhongit.guanhutong3.utils.pulldata.Location;
import com.changhongit.guanhutong3.utils.pulldata.QueryAreas;
import com.changhongit.guanhutong3.utils.pulldata.Status;
import com.changhongit.guanhutong3.utils.pulldata.UserTerminalList;

public interface onLoadFinishedListener {
	void onLoadFinished(UserTerminalList teminals);
	void onLocationFinished(Location loc,Status status,QueryAreas areas);
	void onNetWorkError();
	void onEmptyData();
}
