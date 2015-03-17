package com.changhongit.guanhutong3.whitelist.detail;

import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.utils.HttpUtil;
import android.os.Handler;

public class WhiteListDetailModelImpl implements WhiteListDetailModel{
	
	private static final int SUCCESS = 1;
	private static final int FAIL = 0;
	
	onSaveFinishedListener mListener;
	
	public WhiteListDetailModelImpl(onSaveFinishedListener listener){
		mListener = listener;
	}

	@Override
	public void save(final boolean isNew,final String name, final String phone,final String id) {
		// TODO Auto-generated method stub
		new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				boolean success;
				if(isNew)success = HttpUtil.getinstance().addWhiteList(GhtApplication.mCurrentTerminal.getImei(), phone, name);
				else success = HttpUtil.getinstance().updateWhiteList(GhtApplication.mCurrentTerminal.getImei(), id, phone, name);
				if(success){
					handler.sendEmptyMessage(SUCCESS);
				}
				else{
					handler.sendEmptyMessage(FAIL);
				}
			}
		}.start();
	}
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case FAIL: //失败
				mListener.onFinish(false);
				break;
			case SUCCESS: //成功
				mListener.onFinish(true);
				break;
			}
		}
	};

}
