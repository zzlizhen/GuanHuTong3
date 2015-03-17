package com.changhongit.guanhutong3.application;

import com.amap.api.maps2d.model.LatLng;
import com.changhongit.guanhutong3.utils.SQLiteUtil;
import com.changhongit.guanhutong3.utils.pulldata.UserTerminal;
import com.changhongit.guanhutong3.utils.pulldata.UserTerminalList;

import android.app.Application;

public class GhtApplication extends Application{
	public static String mUserId;   //用户登录id
	public static UserTerminal mCurrentTerminal;
	public static UserTerminalList mTerminals;
	public static SQLiteUtil mSQLiteUtil;
	public static String mLoginUserName;
	 public static LatLng location_marker;   //当前终端位置，防护圈不必在重新获取
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mSQLiteUtil = new SQLiteUtil(this);
	}
}
