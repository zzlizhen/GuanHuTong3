package com.changhongit.guanhutong3.contacts;

import java.util.ArrayList;

import com.changhongit.guanhutong3.utils.pulldata.Contact;

public class ContactsPresenterImpl implements ContactsPresenter{
	ContactsView mView;
	ContactsModel mModel;
	
	public ContactsPresenterImpl(ContactsView view,onLoadFinishedListener listener) {
		// TODO Auto-generated constructor stub
		mView = view;
		mModel = new ContactModelImpl(listener);
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		mModel.loadContacts();
	}
	@Override
	public void setContactForIndex(int index, String name, String phone) {
		// TODO Auto-generated method stub
		mModel.setContacts(index, name, phone);
	}

}
;