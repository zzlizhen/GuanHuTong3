package com.changhongit.guanhutong3.call_setting;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.utils.HttpUtil;
import com.changhongit.guanhutong3.utils.XMLPullUtil;
import com.changhongit.guanhutong3.utils.pulldata.TerminalSetting;

public class CallSettingModelImpl implements CallSettingModel{
	CallSettingView mView;
	TerminalSetting mInfo;
	public CallSettingModelImpl(CallSettingView listener) {
		// TODO Auto-generated constructor stub
		mView = listener;
	}
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case 0:
				try {
					mInfo = XMLPullUtil.getinstance().parserTerminalSetting(msg.getData().getString("result"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mView.setData(mInfo);
				break;
			case 1:
				break;
			case 3:
				break;
			case 4:
				break;
			}
		};
	};
	@Override
	public void loadData() {
		// TODO Auto-generated method stub
		new Thread(){
			public void run() {
				String result = HttpUtil.getinstance().RequestTerminalSettings(GhtApplication.mCurrentTerminal.getImei());
				if(result!=null){
					Message msg = Message.obtain();
					msg.what = 0;
					Bundle bundle = new Bundle();
					bundle.putString("result", result);
					msg.setData(bundle);
					handler.sendMessage(msg);
				}
				else{
					handler.sendEmptyMessage(1);
				}
			};
		}.start();
	}
	@Override
	public void saveData(final TerminalSetting info) {
		// TODO Auto-generated method stub
		new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				boolean isSuccess = HttpUtil.getinstance().updateTerminalSettings(GhtApplication.mUserId, info.getImei(), info.isAuto(), info.isReceiveAll());
				if(isSuccess){
					handler.sendEmptyMessage(3);
				}
				else{
					handler.sendEmptyMessage(4);
				}
			}
			
		}.start();
	}

}
