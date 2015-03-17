package com.changhongit.guanhutong3.main;

import com.changhongit.guanhutong3.utils.pulldata.Location;
import com.changhongit.guanhutong3.utils.pulldata.QueryAreas;
import com.changhongit.guanhutong3.utils.pulldata.Status;
import com.changhongit.guanhutong3.utils.pulldata.UserTerminalList;

public interface MainView {
    public void onPopItemClick(int position);

    public void setTerminals(UserTerminalList terminals);

    public void setLocation(Location loc);
}
