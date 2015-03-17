package com.changhongit.guanhutong3.area;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.utils.HttpUtil;
import com.changhongit.guanhutong3.utils.XMLPullUtil;
import com.changhongit.guanhutong3.utils.pulldata.QueryAreas;

public class AreaModelImpl implements AreaModel {
	private ArrayList<QueryAreas> mList;
	private AreaView  mView;
	
	public AreaModelImpl(AreaView view) {
		// TODO Auto-generated constructor stub
		mView = view;
	}
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case 0:
				try {
					mList= XMLPullUtil.getinstance().getListQueryAreas(msg.getData().getString("result"), "utf-8");
					Log.d("Landylitest","mList.size === " + mList.size());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mView.setQuery(mList.get(0));
				break;
			case 1:
				break;
			}
		};
	};

	@Override
	public void loadData() {
		// TODO Auto-generated method stub
		new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				String result = HttpUtil.getinstance().RequestQueryAreas(GhtApplication.mCurrentTerminal.getImei(), GhtApplication.mUserId, 1, 10);
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
			}
		}.start();
	}

}
