package com.changhongit.guanhutong3.setting.terminal;

import com.changhongit.guanhutong3.utils.pulldata.Terminal;
import com.changhongit.guanhutong3.utils.pulldata.UserTerminalList;

public interface TerminalSettingView {
	
	void showprogress();
	void hideprogress();
	void showMessage(String msg);
	void setData(Terminal result);
	void setListData(UserTerminalList list);
	void onDeleteFinished(boolean isSuccess);
	
}
