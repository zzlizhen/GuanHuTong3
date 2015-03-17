package com.changhongit.guanhutong3.alert;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.utils.HttpUtil;
import com.changhongit.guanhutong3.utils.XMLPullUtil;
import com.changhongit.guanhutong3.utils.pulldata.AlertList;

public class AlertModelImpl {
	
	onLoadFinishedListener mListener;
	
	final int SUCCESS = 0;
	final int FAILURE = 1;
	
	public AlertModelImpl(onLoadFinishedListener listener){
		mListener = listener;
	}

	public void getData(final int page) {
		// TODO Auto-generated method stub
		new Thread() {
            public void run() {
            	String result = HttpUtil.getinstance().RequestAlertList(GhtApplication.mUserId, page, 10);
            	if(result == null)handler.sendEmptyMessage(FAILURE);
            	else if(!result.equals("empty data")){
                	Message msg = new Message();
                	Bundle bundle = new Bundle();
                	bundle.putString("result", result);
                	msg.setData(bundle);
                	msg.what = SUCCESS;
                	handler.sendMessage(msg);
            	}
            	else{
            		handler.sendEmptyMessage(FAILURE);
            	}
            };
        }.start();
	}
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case SUCCESS:
				String result = msg.getData().getString("result");
				AlertList alertlist = null;
				try {
					alertlist = XMLPullUtil.getinstance().parseAlerts(result);
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.v("", "parse done");
				mListener.onLoaded(alertlist);
				break;
			case FAILURE:
				mListener.onLoaded(null);
				break;
			}
		}
	};

}
