package com.changhongit.guanhutong3.personal_setting;

import com.changhongit.guanhutong3.utils.pulldata.UserInfo;

public class PersonalSettingPresenterImpl implements PersonalSettingPresenter{
	private PersonalSettingModel mModel;
	
	public PersonalSettingPresenterImpl(onLoadFinishedListener listener) {
		// TODO Auto-generated constructor stub
		mModel = new PersonalSettingModelImpl(listener);
	}
	@Override
	public void loadUserInfo() {
		// TODO Auto-generated method stub
		mModel.loadUserInfo();
	}

	@Override
	public void setUserInfo(UserInfo info) {
		// TODO Auto-generated method stub
		mModel.setUserInfo(info);
	}

}
