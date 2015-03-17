package com.changhongit.guanhutong3.events;

import java.io.IOException;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.utils.HttpUtil;
import com.changhongit.guanhutong3.utils.XMLPullUtil;
import com.changhongit.guanhutong3.utils.pulldata.Reminder;


public class EventsModelImpl implements EventsModel{

	private onLoadFinishedListener mListener;
	private List<Reminder> mList;
	
	public EventsModelImpl(onLoadFinishedListener listener) {
		super();
		// TODO Auto-generated constructor stub
		this.mListener = listener;
	}

	public Reminder getItem(int position){
		return mList.get(position);
	}
	
	@Override
	public void findItems() {
		// TODO Auto-generated method stub
		getEventsListThread();
	}
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case 0:
				try {
					mList = XMLPullUtil.getinstance().parseReminderList(msg.getData().getString("result"));
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mListener.onLoadFinished(mList);
				break;
			case 1:
				break;
			}
		};
	};
	
	public void getEventsListThread(){
		new Thread(){
			public void run() {
				String result = HttpUtil.getinstance().RequestReminderList(GhtApplication.mCurrentTerminal.getImei(), 1, 10);
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
}
