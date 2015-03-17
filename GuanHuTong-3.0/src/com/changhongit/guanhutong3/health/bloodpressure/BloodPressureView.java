package com.changhongit.guanhutong3.health.bloodpressure;

import com.changhongit.guanhutong3.utils.pulldata.BloodPressure;

public interface BloodPressureView {
	
	void showprogress();
	void hideprogress();
	void showResult(BloodPressure result);
	void displayMessage(String message);
}
