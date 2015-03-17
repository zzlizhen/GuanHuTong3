package com.changhongit.guanhutong3.whitelist;

import java.util.List;

import com.changhongit.guanhutong3.utils.pulldata.WhiteListInfo;

public class WhiteListPresenterImpl implements WhiteListPresenter,onLoadFinishedListener{
	WhiteListView mView;
	FindWhiteListItemsInteractor mModel;
	
	public final int LOAD_ERROR = 0;     //网络出错时报错
	public final int DELETE_SUCCESS = 1; //删除成功提示
	public final int DELETE_ERROR = 2;   //删除失败报错
	
	
	public WhiteListPresenterImpl(WhiteListView view) {
		super();
		// TODO Auto-generated constructor stub
		mView = view;
		mModel = new FindWhiteListItemsInteractorImpl(this);
	}

	@Override
	public void onResume() {             //读取列表，在多处调用
		// TODO Auto-generated method stub
		mView.showProgress();
		mModel.findItems();
	}

	@Override
	public void onItemClicked(int position) {
		// TODO Auto-generated method stub
		mView.showMessage("item %d onclick"+position);
		mView.turnToDetail(position);
	}

	@Override
	public void onLoadFinished(List<WhiteListInfo> items) {
		// TODO Auto-generated method stub
		mView.hideProgress();
		mView.setItems(items);
	}
	
	@Override
	public void onReturnStatus(int i) {
		// TODO Auto-generated method stub
		mView.hideProgress();
		switch(i){
		case LOAD_ERROR:
			mView.showMessage("读取失败，请检查网络");
			break;
		case DELETE_SUCCESS:
			mView.showMessage("删除成功");
			onResume();
			break;
		case DELETE_ERROR:
			mView.showMessage("删除失败，请检查网络");
			break;
		}
	}


	@Override
	public void delete(int position) {
		// TODO Auto-generated method stub
		mView.showProgress();
		mModel.delete(position);
	}


}
