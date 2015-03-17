package com.changhongit.guanhutong3.area;

public class AreaPresenterImpl implements AreaPresenter{
	private AreaModel mModle;
	public AreaPresenterImpl(AreaView view) {
		// TODO Auto-generated constructor stub
		mModle = new AreaModelImpl(view);
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		mModle.loadData();
	}

}
