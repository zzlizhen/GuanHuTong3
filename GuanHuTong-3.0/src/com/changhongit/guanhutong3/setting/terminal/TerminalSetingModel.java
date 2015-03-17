package com.changhongit.guanhutong3.setting.terminal;

import com.changhongit.guanhutong3.utils.pulldata.Terminal;

public interface TerminalSetingModel {
	
	void loaddata();
	void savedata(Terminal data);
	void loadList();
	void delete(int index);
}
