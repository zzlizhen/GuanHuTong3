package com.changhongit.guanhutong3.contacts;

import java.util.ArrayList;
import java.util.List;

import com.changhongit.guanhutong3.utils.pulldata.Contact;
import com.changhongit.guanhutong3.utils.pulldata.WhiteListInfo;

public interface onLoadFinishedListener {
	void onLoadFinished(ArrayList<Contact> items);
	void onSetFinished(int index,boolean isSuccessed);
}
