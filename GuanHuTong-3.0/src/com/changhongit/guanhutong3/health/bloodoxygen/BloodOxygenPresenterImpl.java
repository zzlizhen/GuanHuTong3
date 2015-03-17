package com.changhongit.guanhutong3.health.bloodoxygen;

import java.util.List;

import com.changhongit.guanhutong3.utils.pulldata.BloodOxygen;

public class BloodOxygenPresenterImpl implements BloodOxygenPresenter , OnLoadedListener{

	BloodOxygenView mView;
	BloodOxygenModelImpl mModel;
	
	public BloodOxygenPresenterImpl(BloodOxygenView view){
		this.mView = view;
		mModel = new BloodOxygenModelImpl(this);
	}
	
	@Override
	public void LoadData() {
		// TODO Auto-generated method stub
		mModel.LoadData();
	}

	@Override
	public void onLoaded(List<BloodOxygen> list) {
		// TODO Auto-generated method stub
		mView.hideprogress();
		if(list!=null){
			if(list.size()>0)mView.showResult(list.get(0));
			else mView.displayMessage("没有血氧数据");
		}
		else{
			mView.displayMessage("获取数据失败，请检查网络连接");
		}
	}

}
