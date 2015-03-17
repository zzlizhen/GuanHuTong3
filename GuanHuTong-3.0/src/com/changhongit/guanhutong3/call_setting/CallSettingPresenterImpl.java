package com.changhongit.guanhutong3.call_setting;

import com.changhongit.guanhutong3.utils.pulldata.TerminalSetting;

public class CallSettingPresenterImpl implements CallSettingPresenter{
	CallSettingModel mModel;
	
	public CallSettingPresenterImpl(CallSettingView view) {
		// TODO Auto-generated constructor stub
		mModel = new CallSettingModelImpl(view);
	}

	@Override
	public void loadData() {
		// TODO Auto-generated method stub
		mModel.loadData();
	}

	@Override
	public void savaData(TerminalSetting info) {
		// TODO Auto-generated method stub
		mModel.saveData(info);
	}
}
