package com.changhongit.guanhutong3.contacts;

import java.util.ArrayList;

import com.changhongit.guanhutong3.utils.pulldata.Contact;

public interface ContactsModel {
	public void loadContacts();
	public void setContacts(int index,String name,String phone);
	
}
