package com.changhongit.guanhutong3.personal_setting;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.map.utils.DebugUtil;
import com.changhongit.guanhutong3.utils.HttpUtil;
import com.changhongit.guanhutong3.utils.SpUtils;
import com.changhongit.guanhutong3.utils.XMLPullUtil;
import com.changhongit.guanhutong3.utils.pulldata.UserInfo;

public class PersonalSettingModelImpl implements PersonalSettingModel{
	onLoadFinishedListener mListener;
	private UserInfo mInfo;
	public PersonalSettingModelImpl(onLoadFinishedListener listener) {
		// TODO Auto-generated constructor stub
		mListener = listener;
	}
	@Override
	public void loadUserInfo() {
		// TODO Auto-generated method stub
		new Thread(){
			public void run() {
				String result = HttpUtil.getinstance().RequestUserInfoInfo(GhtApplication.mUserId);
				DebugUtil.Debug("setting result ==== " + result);
				if (result!=null){
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
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case 0:
				try {
					mInfo = XMLPullUtil.getinstance().parseUserInfo(msg.getData().getString("result"));
					mListener.onLoadFinished(mInfo);
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 1:
				break;
			case 2:
				mListener.onSetFinished(msg.getData().getBoolean("isSuccess"));
				break;
			}
		};
		
	};
	@Override
	public void setUserInfo(final UserInfo info) {
		// TODO Auto-generated method stub
		new Thread(){
			public void run() {
				boolean isSuccess = HttpUtil.getinstance().updateUserInfo(GhtApplication.mUserId, GhtApplication.mLoginUserName, info.getUserName(), info.isMale(), info.getEMail(), info.getAddress(), info.getBirthday(), info.getPhone(),"");
				Message msg = Message.obtain();
				msg.what = 2;
				Bundle bundle = new Bundle();
				bundle.putBoolean("isSuccess", isSuccess);
				msg.setData(bundle);
				handler.sendMessage(msg);
			};
		}.start();
	}

}
