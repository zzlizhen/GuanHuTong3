package com.changhongit.guanhutong3.contacts;

import java.util.ArrayList;

import com.changhongit.guanhutong3.utils.pulldata.Contact;

public interface ContactsPresenter {
	public void onResume();
	public void setContactForIndex(int index,String name,String phone);
}
