package com.changhongit.guanhutong3.health.bodycompositions;

import java.io.IOException;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.utils.HttpUtil;
import com.changhongit.guanhutong3.utils.XMLPullUtil;
import com.changhongit.guanhutong3.utils.pulldata.BodyCompositions;

public class BodyCompositionsModelImpl implements BodyCompositionsModel{
	
	private final int SUCCESS = 0;
	private final int FAILURE = 1;
	OnLoadedListener listener;
	
	public BodyCompositionsModelImpl(OnLoadedListener listener){
		this.listener = listener;
	}

	@Override
	public void LoadData() {
		// TODO Auto-generated method stub
		new Thread() {
            public void run() {
            	String result = HttpUtil.getinstance().RequestBodyCompositions(GhtApplication.mCurrentTerminal.getImei(), null, null, 1, 1);
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
				List<BodyCompositions> list = null;
				Log.v("", result);
				try {
					list = XMLPullUtil.getinstance().parseBodyComposition(result);
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.v("", "parse done");
				listener.onLoaded(list);
				break;
			case FAILURE:
				listener.onLoaded(null);
				break;
			}
		}
	};

}
