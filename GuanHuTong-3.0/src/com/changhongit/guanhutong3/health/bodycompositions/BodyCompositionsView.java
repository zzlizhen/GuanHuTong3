package com.changhongit.guanhutong3.health.bodycompositions;

import com.changhongit.guanhutong3.utils.pulldata.BodyCompositions;

public interface BodyCompositionsView {
	
	void showprogress();
	void hideprogress();
	void showResult(BodyCompositions result);
	void displayMessage(String message);
}
