package com.changhongit.guanhutong3.trace;

import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

import org.xmlpull.v1.XmlPullParserException;

import com.changhongit.guanhutong3.utils.HttpUtil;
import com.changhongit.guanhutong3.utils.XMLPullUtil;
import com.changhongit.guanhutong3.utils.pulldata.Location;
import com.changhongit.guanhutong3.utils.pulldata.LocationHistoryList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class TraceModelImpl implements TraceModel {

	
	public LocationHistoryList list = null;
	int leftcount;//剩余总数，判断是否继续请求数据
	int totalcount;
	int progress;//当前播放到的点
	int currentpage = 1; //当前页数
	int period = 1000;//播放周期b
	private boolean signal = true;//互斥信号量，保证Activity中NextTrace()和setTrace()不同时执行
	static String mImei;
	static String mStartTime;
    static String mEndTime;
	boolean loading = false;   //信号量，判断是否是第一次调用loadHistory
    private Timer timer = null;
	final static int MAX_SIZE = 10000;
	final static int SUCCESS = -20;//成功取得一次数据
	final static int FINISH = -19;//数据取完了
	final static int NEXT = -10;//显示下一个点
	final static int NETWORK_ERROR = -18;
	final static int EMPTY_DATA = -17;
    onTraceLoadedListener listener = null;
    

    public TraceModelImpl(onTraceLoadedListener listener) {
        this.listener = listener;
    }

    /**
     * 从服务器获取数据(可能多次调用)
     */
    @Override
    public void loadHistory(String startTime, String endTime, String imei) {
        // TODO Auto-generated method stub
    	if(!loading){
  		  mStartTime = startTime;
  		  mEndTime = endTime;
  		  mImei = imei;
  		  }
    	 new Thread() {
             public void run() {
            	 final String result = HttpUtil.getinstance().RequestLocationHistory(mImei, mStartTime,mEndTime,  currentpage, MAX_SIZE);
            	 if(result==null){//请求数据失败时报错
            		 handler.sendEmptyMessage(NETWORK_ERROR);
            	 }
            	 else if(result.equals("empty data")){//请求数据为空时报错
            		 handler.sendEmptyMessage(EMPTY_DATA);
            	 }
            	 else{//请求数据正常
            	 LocationHistoryList tmp = null;
            	 try {
					 tmp = XMLPullUtil.getinstance().getLocationHistoryList(result,"utf-8");
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	 assembleData(tmp);
            	 totalcount = leftcount = tmp.gettotalcount();
            	 handler.sendEmptyMessage(SUCCESS);	}
             };
         }.start();
    }    

	@Override
	public void assembleData(LocationHistoryList loclist) {
		// TODO Auto-generated method stub
		if(list == null)list = new LocationHistoryList();
		list = list.mergeList(loclist);
	}
    
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
        	switch(msg.what){
        	case SUCCESS://数据获取一节(10000个)后执行
        		leftcount -=MAX_SIZE;
        		currentpage++;
        		if(leftcount>0){
        			loading = true;
        			loadHistory(mStartTime,mEndTime,mImei);
        		}
        		else{
        			handler.sendEmptyMessage(FINISH);
        		}
        		break;
        	case FINISH://数据读取完成后执行
    			resetstatus();
        		listener.onFinish(list);
        		break;
        	case NEXT://接受时钟信号时执行
        		progress++;
        		if(progress>=totalcount)stopPlaying();
        		else listener.onNext(progress, list.getitem(progress));
        		break;
        	case NETWORK_ERROR:
        		listener.onNetWorkError();
        		break;
        	case EMPTY_DATA:
        		listener.onEmptyList();
        		break;
        	}
        }
    };
    
    private void resetstatus(){
    	leftcount = 0;
		currentpage = 1;
		mImei = null;
		mStartTime = null;
		mEndTime = null;
		loading = false;
    }
    
	@Override
    public LocationHistoryList getList(){
    	return list;
    }

	@Override
	public Location jumpto(int progress) {
		// TODO Auto-generated method stub
		this.progress = progress;
		return this.list.getlist().get(progress);
	}

	@Override
	public boolean playSwitch() {
		// TODO Auto-generated method stub
        boolean mode = isPlaying();
        if (mode)
            stopPlaying();
        else
            startPlaying();
       
        return !mode;
	}
	
	public void startPlaying(){
        timer = new Timer();
        timer.schedule(new TimerTask() { 
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                    	if(signal)handler.sendEmptyMessage(NEXT);
                    }
                }, 50, period);
	}
	

    protected void stopPlaying() {
        timer.cancel();
        timer = null;
	}
    
    public boolean isPlaying() {
        // TODO Auto-generated method stub
        if (timer != null) // 通过timer是否为null判断
            return true;
        else
            return false;
    }

	@Override
	public void setPeriod(int period) {
		// TODO Auto-generated method stub
		this.period = period;
		if(isPlaying()){
			stopPlaying();
			startPlaying();
		}
	}

	@Override
	public void setSignal(boolean signal) {
		// TODO Auto-generated method stub
		this.signal = signal;
	}


}
