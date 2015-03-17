package com.changhongit.guanhutong3.setting.terminal;

import java.util.ArrayList;

import com.changhongit.guanhutong3.utils.pulldata.Terminal;
import com.changhongit.guanhutong3.utils.pulldata.UserTerminalList;

public class TerminalSettingPresenterImpl implements TerminalSettingPresenter,OnLoadedListener {
	
	TerminalSettingView view;
	TerminalSettingModelImpl model;
	
	TerminalSettingPresenterImpl(TerminalSettingView view){
		this.view = view;
		model = new TerminalSettingModelImpl(this);
	}

	@Override
	public void onLoad(Terminal result) {
		// TODO Auto-generated method stub
		view.setData(result);
		view.hideprogress();
	}

	@Override
	public void onSave() {
		// TODO Auto-generated method stub
		view.showMessage("保存数据成功");
		view.hideprogress();
	}

	@Override
	public void loaddata() {
		// TODO Auto-generated method stub
		model.loaddata();
	}
	
	@Override
	public void savedata(Terminal data) {
		// TODO Auto-generated method stub
		model.savedata(data);
	}

	@Override
	public void showMessage(String msg) {
		// TODO Auto-generated method stub
		view.showMessage(msg);
		view.hideprogress();
	}

	@Override
	public void loadList() {
		// TODO Auto-generated method stub
		model.loadList();
	}

	@Override
	public void deleteTerminal(int index) {
		// TODO Auto-generated method stub
		model.delete(index);
	}


	@Override
	public void onLoadListFinish(UserTerminalList list) {
		// TODO Auto-generated method stub
		view.setListData(list);
	}

	@Override
	public void onDeleteFinish(boolean isSuccess) {
		// TODO Auto-generated method stub
		view.onDeleteFinished(isSuccess);
	}

	
}
