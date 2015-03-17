/**
 * 
 */
package com.changhongit.guanhutong3.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

public class GHTUtil {



	public static void Debug(String str) {
		Log.d("Landylitest", str);
	}
	
	/**
	 * 一周前的时间
	 * @return
	 */
	public static String lastWeek(){
		   Date date = new Date();
		   int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		   int month=Integer.parseInt(new SimpleDateFormat("MM").format(date));
		   int day=Integer.parseInt(new SimpleDateFormat("dd").format(date))-6;
		  
		   if(day<1){
		    month-=1;
		    if(month==0){
		     year-=1;month=12;
		    }
		    if(month==4||month==6||month==9||month==11){
		     day=30+day;
		    }else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
		    {
		     day=31+day;
		    }else if(month==2){
		     if(year%400==0||(year %4==0&&year%100!=0))day=29+day;
		     else day=28+day;
		    }     
		   }
		   String y = year+"";String m ="";String d ="";
		   if(month<10) m = "0"+month;
		   else m=month+"";
		   if(day<10) d = "0"+day;
		   else d = day+"";
		  
		   return y+"-"+m+"-"+d+"T"+"00:00:00.000Z";
		}
	/**
	 * allMonth个月前的时间
	 * @param allMonth
	 * @return
	 */
	public static String lastMonth(int allMonth) {
        Date date = new Date();
           int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
           int month=Integer.parseInt(new SimpleDateFormat("MM").format(date))-allMonth;
           int day=Integer.parseInt(new SimpleDateFormat("dd").format(date));
           if(month <= 0){
               int yearFlag = (month*(-1))/12 + 1;
               int monthFlag = (month *(-1))%12;
               year -= yearFlag;
               month=monthFlag*(-1) +12;
           }
           else if(day>28){
               if(month==2){
                   if(year%400==0||(year %4==0&&year%100!=0)){
                       day=29;
                   }else day=28;
               }else if((month==4||month==6||month==9||month==11)&&day==31){
                   day=30;
               }
           }
           String y = year+"";String m ="";String d ="";
           if(month<10) m = "0"+month;
           else m=month+"";
           if(day<10) d = "0"+day;
           else d = day+"";
          
           return y+"-"+m+"-"+d+"T"+"00:00:00.000Z";
    }
	
	public static String getCurrentTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String date=sdf.format(new java.util.Date());  
		date = date.replace(" ", "T")+".000Z";
		return date;
	}

	public static String strToMillion(String str) {
		String result = null;
		try {
			/**
			 * 将字符串数据转化为毫秒数
			 */
			String dateTime = str;

			Calendar c = Calendar.getInstance();

			c.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(dateTime));
			result = c.getTimeInMillis() + "";
			Debug("time ==== " + result);
			// System.out.println("时间转化后的毫秒数为：" + c.getTimeInMillis());
			// /**
			// * 将毫秒数转化为时间
			// */
			// String sstime = "1339033320000";
			// Date date = new Date(sstime);
			//
			// SimpleDateFormat sdf = new
			// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//
			// System.out.println("毫秒数转化后的时间为：" + sdf.format(date));
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	/**
	 *  把dip单位转成px单位         
	 *   @param context context对象          
	 *   @param dip dip数值          
	 *   @return          
	 */
	public static int formatDipToPx(Context context, int dip) {
			DisplayMetrics dm = new DisplayMetrics();
			((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
			return (int)Math.ceil( dip * dm.density);
	}
	
    /** 
     * 将px值转换为dip或dp值，保证尺寸大小不变 
     *  
     * @param pxValue 
     * @param scale 
     *            （DisplayMetrics类中属性density） 
     * @return 
     */ 
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
    
    /** 
     * 将dip或dp值转换为px值，保证尺寸大小不变 
     *  
     * @param dipValue 
     * @param scale 
     *            （DisplayMetrics类中属性density） 
     * @return 
     */ 
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
    
    /** 
     * 将px值转换为sp值，保证文字大小不变 
     *  
     * @param pxValue 
     * @param fontScale 
     *            （DisplayMetrics类中属性scaledDensity） 
     * @return 
     */  
    public static int px2sp(Context context, float pxValue) {  
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
        return (int) (pxValue / fontScale + 0.5f);  
    }  
  
    /** 
     * 将sp值转换为px值，保证文字大小不变 
     *  
     * @param spValue 
     * @param fontScale 
     *            （DisplayMetrics类中属性scaledDensity） 
     * @return 
     */  
    public static int sp2px(Context context, float spValue) {  
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
        return (int) (spValue * fontScale + 0.5f);  
    }  
}
