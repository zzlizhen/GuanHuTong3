package com.changhongit.guanhutong3.whitelist;

import java.util.List;

import com.changhongit.guanhutong3.utils.pulldata.WhiteListInfo;

public interface onLoadFinishedListener {
	void onLoadFinished(List<WhiteListInfo> items);
	void onReturnStatus(int i);
}
