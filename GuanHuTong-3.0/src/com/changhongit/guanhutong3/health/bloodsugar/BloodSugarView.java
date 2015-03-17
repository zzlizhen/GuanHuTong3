package com.changhongit.guanhutong3.health.bloodsugar;

import com.changhongit.guanhutong3.utils.pulldata.BloodSugar;

public interface BloodSugarView {
	
	void showprogress();
	void hideprogress();
	void showResult(BloodSugar result);
	void displayMessage(String message);
}
