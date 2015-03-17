package com.changhongit.guanhutong3.health.bloodsugar;

import java.util.List;

import com.changhongit.guanhutong3.utils.pulldata.BloodSugar;

public class BloodSugarPresenterImpl implements BloodSugarPresenter , OnLoadedListener{

	BloodSugarView mView;
	BloodSugarModelImpl mModel;
	
	public BloodSugarPresenterImpl(BloodSugarView view){
		this.mView = view;
		mModel = new BloodSugarModelImpl(this);
	}
	
	@Override
	public void LoadData() {
		// TODO Auto-generated method stub
		mModel.LoadData();
	}

	@Override
	public void onLoaded(List<BloodSugar> list) {
		// TODO Auto-generated method stub
		mView.hideprogress();
		if(list!=null){
			if(list.size()>0)mView.showResult(list.get(0));
			else mView.displayMessage("没有血糖数据");
		}
		else{
			mView.displayMessage("获取数据失败，请检查网络连接");
		}
	}

}
