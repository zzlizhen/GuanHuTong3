package com.changhongit.guanhutong3.alert;

import com.changhongit.guanhutong3.utils.pulldata.AlertList;

public interface AlertView {
	
	void showprogress();
	void hideprogress();
	void setData(AlertList list);
	void showFailure();

}
