package com.changhongit.guanhutong3.setting.terminal;

import com.changhongit.guanhutong3.utils.pulldata.Terminal;
import com.changhongit.guanhutong3.utils.pulldata.UserTerminalList;

public interface OnLoadedListener {
	
	void onLoad(Terminal result);
	void onSave();
	void onLoadListFinish(UserTerminalList list);
	void showMessage(String msg);
	void onDeleteFinish(boolean isSuccess);
	
}
