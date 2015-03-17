package com.changhongit.guanhutong3.main;

public interface MainModel {

    public void loadUserTerminalData();

    public void loadCurrentTerminalInfo(String imei);

    public boolean TrackingSwitch();

    public boolean isTracking();
}
