package com.changhongit.guanhutong3.whitelist.detail;

public class WhiteListDetailPresenterImpl implements WhiteListDetailPresenter,onSaveFinishedListener{

	WhiteListDetailView mView;
	WhiteListDetailModelImpl mModel;
	
	public WhiteListDetailPresenterImpl(WhiteListDetailView view){
		mView = view;
		mModel = new WhiteListDetailModelImpl(this);
	}
	
	@Override
	public void onFinish(boolean success) {
		// TODO Auto-generated method stub
		mView.hideprogress(success);
	}

	@Override
	public void save(boolean isNew,String name, String phone,String id) {
		// TODO Auto-generated method stub
		mView.showprogress();
		mModel.save(isNew ,name , phone ,id);
	}

}
