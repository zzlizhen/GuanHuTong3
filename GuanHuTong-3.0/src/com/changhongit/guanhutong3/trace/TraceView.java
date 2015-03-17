package com.changhongit.guanhutong3.trace;

import com.changhongit.guanhutong3.utils.pulldata.Location;

public interface TraceView {
    public void setTrace();
	public void setProgress(int progress, Location loc);
	public void iniView();
	public void nextTrace();
}
