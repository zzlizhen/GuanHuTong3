package com.changhongit.guanhutong3.alert;

import com.changhongit.guanhutong3.utils.pulldata.AlertList;

public class AlertPresenterImpl implements AlertPresenter,onLoadFinishedListener{
	
	AlertModelImpl mModel;
	AlertView mView;
	
	public AlertPresenterImpl(AlertView view){
		mView = view;
		mModel = new AlertModelImpl(this);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stubs
		mModel.getData(1);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void getData(int page){
		mModel.getData(page);
	}
	
	@Override
	public void onLoaded(AlertList list){
		if(list!=null){
			mView.setData(list);
		}
		else{
			mView.showFailure();
		}
	}

}
