package com.changhongit.guanhutong3.health.bloodoxygen;

import com.changhongit.guanhutong3.utils.pulldata.BloodOxygen;

public interface BloodOxygenView {
	
	void showprogress();
	void hideprogress();
	void showResult(BloodOxygen result);
	void displayMessage(String message);
}
