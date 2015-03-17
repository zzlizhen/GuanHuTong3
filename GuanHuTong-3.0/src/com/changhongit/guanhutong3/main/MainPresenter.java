package com.changhongit.guanhutong3.main;

import android.view.View;

public interface MainPresenter {
    public void initPopItems(View view);

    public void onResume();

    public void onPause();

    public void loadTerminalInfos(String imei);

    public boolean trackingSwitch();

	void locate(String imei);

}
