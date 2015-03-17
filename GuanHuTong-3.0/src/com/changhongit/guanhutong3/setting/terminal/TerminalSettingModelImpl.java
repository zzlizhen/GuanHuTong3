package com.changhongit.guanhutong3.setting.terminal;

import java.io.IOException;
import java.text.ParseException;

import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.personal_setting.onLoadFinishedListener;
import com.changhongit.guanhutong3.utils.HttpUtil;
import com.changhongit.guanhutong3.utils.XMLPullUtil;
import com.changhongit.guanhutong3.utils.pulldata.LocationHistoryList;
import com.changhongit.guanhutong3.utils.pulldata.Terminal;
import com.changhongit.guanhutong3.utils.pulldata.UserTerminalList;

public class TerminalSettingModelImpl implements TerminalSetingModel{
	
	OnLoadedListener listener;
	final static int LOAD_SUCCESS = -20;
	final static int SAVE_SUCCESS = -19;
	final static int LOAD_ERROR = -18;
	final static int SAVE_ERROR = -17;
	private String id;
	private String code;
	private String sn;
	public 	UserTerminalList mTerminals;
	
	public TerminalSettingModelImpl(OnLoadedListener listener){
		this.listener = listener;
	}

	@Override
	public void loaddata() {
		// TODO Auto-generated method stub
		new Thread() {
			public void run() {
				final String result = HttpUtil.getinstance().RequestTerminalInfo(GhtApplication.mCurrentTerminal.getImei(), GhtApplication.mUserId);
				if(result==null){//请求数据失败时报错
					handler.sendEmptyMessage(LOAD_ERROR);
				}
				else{
					Bundle bundle = new Bundle();
					bundle.putString("result", result);
					Message message = new Message();
					message.setData(bundle);
					message.what = LOAD_SUCCESS;
					handler.sendMessage(message);
				}
            }
        }.start();
	}
	

	@Override
	public void savedata(final Terminal data) {
		// TODO Auto-generated method stub
		new Thread() {
			public void run() {
				boolean result = HttpUtil.getinstance().updateTerminal(id, GhtApplication.mUserId, GhtApplication.mCurrentTerminal.getImei(),
						sn ,code, data.getName(), data.getNickname(), data.getIsMale(),
						data.getBPHigh(), data.getBPLow(), data.getBlood(), data.getHeight(), 
						data.getWeight(), data.getMedicalHistory(), data.getAllergy(), 
						data.getBirthday(), data.getRemark());
				if(result){//请求数据失败时报错
					handler.sendEmptyMessage(SAVE_SUCCESS);
				}
				else{
					handler.sendEmptyMessage(SAVE_ERROR);
				}
            }
        }.start();
	}
	
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
        	switch(msg.what){
        	case LOAD_SUCCESS:
        		Terminal terminal = null;
        		String result = msg.getData().getString("result");
        		try {
					terminal = XMLPullUtil.getinstance().parseTerminal(result);
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		if(result!=null){
        			listener.onLoad(terminal);
        			id = terminal.getId();
        			code = terminal.getCode();
        			sn = terminal.getSn();
        			}
        		else listener.showMessage("获取数据失败");
        		break;
        	case LOAD_ERROR:
        		listener.showMessage("读取数据失败，请检查网络");
        		break;
        	case SAVE_SUCCESS:
        		listener.onSave();
        		break;
        	case SAVE_ERROR:
        		listener.showMessage("保存数据失败");
        	case 0:
        		try {
					mTerminals =  XMLPullUtil.getinstance().getUserTerminal(msg.getData().getString("result"), "utf-8");
					listener.onLoadListFinish(mTerminals);
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
        		//删除成功
        		listener.onDeleteFinish(true);
        		break;
        	case 3:
        		//删除失败
        		listener.onDeleteFinish(false);
        		break;
        	}
        }
    };

	@Override
	public void loadList() {
		// TODO Auto-generated method stub
		new Thread(){
			public void run() {
				String result = HttpUtil.getinstance().RequestUserTerminal(GhtApplication.mUserId, 1,20);
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
	public void delete(final int index) {
		// TODO Auto-generated method stub
		new Thread(){
			public void run() {
				boolean result = HttpUtil.getinstance().deleteTerminal(mTerminals.getlist().get(index).getImei(), GhtApplication.mUserId);
				if(result){
					handler.sendEmptyMessage(2);
				}
				else{
					handler.sendEmptyMessage(3);
				}
			};
		}.start();
	}



}
