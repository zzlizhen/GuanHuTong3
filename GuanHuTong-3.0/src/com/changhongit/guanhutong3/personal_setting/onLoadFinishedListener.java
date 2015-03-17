package com.changhongit.guanhutong3.personal_setting;

import com.changhongit.guanhutong3.utils.pulldata.UserInfo;

public interface onLoadFinishedListener {
	public void onLoadFinished(UserInfo info);
	public void onSetFinished(boolean isSuccessed);
}
