package com.changhongit.guanhutong3.main;

import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.map.utils.DebugUtil;
import com.changhongit.guanhutong3.utils.HttpUtil;
import com.changhongit.guanhutong3.utils.XMLPullUtil;
import com.changhongit.guanhutong3.utils.pulldata.Location;
import com.changhongit.guanhutong3.utils.pulldata.QueryAreas;
import com.changhongit.guanhutong3.utils.pulldata.Status;
import com.changhongit.guanhutong3.utils.pulldata.UserTerminalList;

public class MainModelImpl implements MainModel {
    UserTerminalList terminals;
    onLoadFinishedListener listener = null;;
    Location currentLocation;
    Status currentStatus;
    private Timer timer = null;
    private String imei = null;
    QueryAreas areas;

    public MainModelImpl(onLoadFinishedListener lis) {
        this.listener = lis;
    }

    public void loadUserTerminalData() {
        // TODO Auto-generated method stub
        new Thread() {
            public void run() {
                String result = HttpUtil.getinstance().RequestUserTerminal(GhtApplication.mUserId, 1, 10);
                DebugUtil.Debug("terminal result  === " + result);
                if (result == null) {
                    handler.sendEmptyMessage(1);
                }
                else if (result.equals("empty data")){
                	handler.sendEmptyMessage(3);
                }
                else {
                    Message msg = Message.obtain();
                    Bundle bundle = new Bundle();
                    msg.what = 0;
                    bundle.putString("result", result);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            };
        }.start();
    }

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
            case 0: // 用户绑定的终端列表
                try {
                    terminals = XMLPullUtil.getinstance().getUserTerminal(msg.getData().getString("result"), "utf-8");
                    DebugUtil.Debug("terminal === " + terminals.getlist().size());
                } catch (XmlPullParserException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                listener.onLoadFinished(terminals);
                break;
            case 1:
            	listener.onNetWorkError();
                break;
            case 2: // 当前终端经纬度
                try {
                    currentLocation = XMLPullUtil.getinstance().getLocation(msg.getData().getString("locresult"),
                            "utf-8");
                    currentStatus = XMLPullUtil.getinstance().getStatus(msg.getData().getString("statusresult"),
                            "utf-8");
//                    areas = XMLPullUtil.getinstance().getQueryAreas(msg.getData().getString("areasresult"), "utf-8");
                } catch (XmlPullParserException e) {
                    // TODO Auto-generated catch block
                    DebugUtil.Debug("e error === " + e.toString());
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                listener.onLocationFinished(currentLocation, currentStatus, areas);
                break;
            case 3:
            	listener.onEmptyData();
                break;
            case 4:
                break;
            case 31:
                if (imei != null)
                    loadCurrentTerminalInfo(imei);
            }

        };

    };

    @Override
    public void loadCurrentTerminalInfo(final String imei) {
        // TODO Auto-generated method stub
        if (this.imei != null)
            this.imei = imei;
        new Thread() {
            public void run() {
            	Log.v("", imei);
                String locresult = HttpUtil.getinstance().RequestLocation(imei);
                Log.v("", locresult);
                String statusresult = HttpUtil.getinstance().RequestStatus(imei);
                String areasresult = HttpUtil.getinstance().RequestQueryAreas(imei, GhtApplication.mUserId, 1, 10);

                DebugUtil.Debug("areasresult === " + areasresult);
                if (locresult != null && statusresult != null) {
                    Message msg = Message.obtain();
                    msg.what = 2;
                    Bundle bundle = new Bundle();
                    bundle.putString("locresult", locresult);
                    bundle.putString("statusresult", statusresult);
                    bundle.putString("areasresult", areasresult);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                } else {
                    handler.sendEmptyMessage(3);
                }
            };
        }.start();
    }

    /**
     * 开启追踪模式
     */
    public void startTracking() {
        // TODO Auto-generated method stub
        timer = new Timer();
        timer.schedule(new TimerTask() { // 1秒之后，每隔5秒发消息查询地址
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                    	handler.sendEmptyMessage(31);
                    }
                }, 1000, 5000);
    }

    /**
     * 关闭追踪模式
     */
    public void stopTracking() {
        // TODO Auto-generated method stub
        timer.cancel();
        timer = null;
    }

    /**
     * 读取追踪状态
     */
    @Override
    public boolean isTracking() {
        // TODO Auto-generated method stub
        if (timer != null) // 通过timer是否为null判断
            return true;
        else
            return false;
    }

    /**
     * 切换并返回追踪状态
     */
    @Override
    public boolean TrackingSwitch() {
        // TODO Auto-generated method stub
        boolean mode = isTracking();
        if (mode)
            stopTracking();
        else
            startTracking();
        return !mode;
    }

}
