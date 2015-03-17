package com.changhongit.guanhutong3.contacts;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.utils.HttpUtil;
import com.changhongit.guanhutong3.utils.XMLPullUtil;
import com.changhongit.guanhutong3.utils.pulldata.Contact;
import com.changhongit.guanhutong3.view.PullToRefreshView;

public class ContactModelImpl implements ContactsModel{
	private ArrayList<Contact> mList;
	private onLoadFinishedListener mListener;
	public ContactModelImpl(onLoadFinishedListener listener) {
		// TODO Auto-generated constructor stub
		mListener = listener;
		mList = new ArrayList<Contact>();
	}
	@Override
	public void loadContacts() {
		// TODO Auto-generated method stub
		new Thread(){
			public void run() {
				String result = HttpUtil.getinstance().RequestContactList(GhtApplication.mCurrentTerminal.getImei());
				if(result!=null){
					try {
						mList.addAll( XMLPullUtil.getinstance().parserContactList(result));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					handler.sendEmptyMessage(0);
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
				mListener.onLoadFinished(mList);
				break;
			case 1:
				break;
			case 2:   //设置联络人成功
				mListener.onSetFinished(msg.getData().getInt("index"), true);
				break;
			case 3:  //设置联络人失败
				mListener.onSetFinished(msg.getData().getInt("index"), false);
				break;
			}
		};
	};

	/**
	 * 设置单个联络人
	 */
	@Override
	public void setContacts(final int index,final String name,final String phone) {
		// TODO Auto-generated method stub
		new Thread(){
			public void run() {
				boolean result = HttpUtil.getinstance().updateContact(GhtApplication.mCurrentTerminal.getImei(),index,name,phone );
				if(result){
					Message msg = Message.obtain();
					msg.what = 2;
					Bundle bundle = new Bundle();
					bundle.putInt("index", index);
					msg.setData(bundle);
					handler.sendMessage(msg);
				}
				else{
					Message msg = Message.obtain();
					msg.what = 3;
					Bundle bundle = new Bundle();
					bundle.putInt("index", index);
					msg.setData(bundle);
					handler.sendMessage(msg);
				}
			};
		}.start();
	}

}
