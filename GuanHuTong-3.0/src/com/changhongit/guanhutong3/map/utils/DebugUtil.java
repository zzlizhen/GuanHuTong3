/**
 * 
 */
package com.changhongit.guanhutong3.map.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/*******
 * @project AMapV2Demos
 * @time 2013-3-20下午4:35:09
 *******/
public class DebugUtil {
	private static String TAG = "changhongit-test";
	public static void show(Context context, String info) {
		Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
	}

	public static void show(Context context, int info) {
		Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
	}
	public static void Debug(String str){
		Log.d(TAG,str);
	}
	
}
