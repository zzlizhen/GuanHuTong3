package com.changhongit.guanhutong3.health.bloodpressure;

import java.util.List;

import com.changhongit.guanhutong3.utils.pulldata.BloodPressure;

public class BloodPressurePresenterImpl implements BloodPressurePresenter , OnLoadedListener{

	BloodPressureView mView;
	BloodPressureModelImpl mModel;
	
	public BloodPressurePresenterImpl(BloodPressureView view){
		this.mView = view;
		mModel = new BloodPressureModelImpl(this);
	}
	
	@Override
	public void LoadData() {
		// TODO Auto-generated method stub
		mModel.LoadData();
	}

	@Override
	public void onLoaded(List<BloodPressure> list) {
		// TODO Auto-generated method stub
		mView.hideprogress();
		if(list!=null){
			if(list.size()>0)mView.showResult(list.get(0));
			else mView.displayMessage("没有血压数据");
		}
		else{
			mView.displayMessage("获取数据失败，请检查网络连接");
		}
	}

}
