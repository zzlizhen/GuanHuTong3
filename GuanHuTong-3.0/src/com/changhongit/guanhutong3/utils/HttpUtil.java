package com.changhongit.guanhutong3.utils;

import java.util.ArrayList;

import android.util.Log;

import com.changhongit.guanhutong3.map.utils.DebugUtil;
import com.changhongit.guanhutong3.utils.pulldata.Contact;
import com.changhongit.guanhutong3.webservice.WebService;

public class HttpUtil {

	//URL
    public static final String HEADER = "http://new.guanhutong.com.cn/ght/httpApi/";
    public static final String SERVER_HEALTH = "http://new.guanhutong.com.cn:9080/ilove.webapi/";

    //变量名
    public static final String PAGE = "page=";
    public static final String IMEI = "imei=";
    public static final String PAGE_SIZE = "pageSize=";
    public static final String USER_ID = "userid=";
    public static final String USER_ID1 = "userId=";//request alert，TerminaInfo用的是这个
    public static final String START_TIME = "startTime=";
    public static final String END_TIME = "endTime=";
	public static final String ID = "id=";

    //get类型
    public static final String USER_TERMINAL = "userTerminal?";
    public static final String TERMINAL_INFO = "terminalInfo?";
    public static final String LOCATION = "locations?";
    public static final String STATUS = "status?";
    public static final String QUERY_AREAS = "queryAreas?";
    public static final String TERMINAL_WHITELIST = "terminalWhitelist?";
    public static final String HISTORY_QUERY = "historyQuery?";
    public static final String WHITE_LIST = "terminalWhitelist?";
    public static final String GTERMINAL_SETTING = "terminalSettingsQuery?";
    public static final String CONTACT_LIST = "terminalContactsList?";
    public static final String REMINDER_LIST = "queryRemindersList?";
    public static final String REMINDER_INFO = "queryReminders?";
    public static final String USER_INFO = "users?";
    public static final String ALERT = "alerts?";
    public static final String BLOOD_OXYGEN = "bloodoxygens?";
    public static final String BLOOD_PRESSURE = "bloodpressures?";
    public static final String BODY_COMPOSITOIN = "bodycompositions?";
    public static final String BLOOD_SUGAR = "bloodsugars?";
    
    //post类型
    public static final String ADD_WHITELIST = "terminalAddWhitelist";
    public static final String UPDATE_WHITELIST = "terminalUpdateWhitelist";
    public static final String DELETE_WHITELIST = "terminalDelWhitelist?";
    
    public static final String PTERMINAL_SETTING = "terminalSettings";
    public static final String UPDATE_CONTACT = "updateContact";
    
    public static final String ADD_REMINDER = "addReminders";
    public static final String UPDATE_REMINDER = "updateReminders";
    public static final String DELETE_REMINDER = "delReminders?";
    
    public static final String UPDATE_PASSWORD = "password";
    public static final String UPDATE_USERINFO = "updateUsers?";
    
    public static final String ADD_TERMINAL = null;
    public static final String UPDATE_TERMINAL = "terminalUpdateByUser";
	private static final String DELETE_TERMINAL = "terminalDeleteByUser";

    public static HttpUtil instance = null;

    public static HttpUtil getinstance() {
        if (instance == null) {
            instance = new HttpUtil();
        }
        return instance;
    }

    /**
     * 请求终端列表，API1
     */
    public String RequestUserTerminal(String userid, int page, int pagesize) {
        String request = HEADER + USER_TERMINAL + PAGE + String.valueOf(page) + "&" + PAGE_SIZE
                + String.valueOf(pagesize) + "&" + USER_ID + userid;
        String result = WebService.getInstance().getRequest(request);
        return result;
    }

    /**
     * 请求终端当前位置，API2
     */
    public String RequestLocation(String imei) {
        String request = HEADER + LOCATION + IMEI + imei;
        DebugUtil.Debug("RequestLocation === " + request);
        String result = WebService.getInstance().getRequest(request);
        return result;
    }

    /**
     * 请求终端状态，API3
     */
    public String RequestStatus(String imei) {
        String request = HEADER + STATUS + IMEI + imei;
        String result = WebService.getInstance().getRequest(request);
        return result;
    }

    /**
     * 请求防护圈数据，API6
     */
    public String RequestQueryAreas(String imei, String userid, int page, int pagesize) {
        String request = HEADER + QUERY_AREAS + PAGE + String.valueOf(page) + "&" + PAGE_SIZE
                + String.valueOf(pagesize) + "&" + "userId=" + userid + "&" + IMEI + imei;
        String result = WebService.getInstance().getRequest(request);
        DebugUtil.Debug("request string === " + request);
        return result;
    }

    /**
     * 请求历史轨迹数据，API8
     */
    public String RequestLocationHistory(String imei,String starttime,String endtime,int page,int pagesize){
    	String request = HEADER + HISTORY_QUERY +IMEI + imei+"&"+ START_TIME +starttime +"&"+END_TIME +endtime +"&" + PAGE +String.valueOf(page)+'&'+PAGE_SIZE +String.valueOf(pagesize);
    	String result = WebService.getInstance().getRequest(request);
    	return result;
    }

    /**
     * 请求白名单，API9
     */
    public String RequestWhiteList(String imei){
    	String request = HEADER + WHITE_LIST + IMEI + imei;
    	String result = WebService.getInstance().getRequest(request);
    	return result;	
    }

    /**
     * 请求联系人列表，API12
     */
    public String RequestContactList(String imei){
    	String request = HEADER + CONTACT_LIST + IMEI + imei;
    	String result = WebService.getInstance().getRequest(request);
    	DebugUtil.Debug("RequestContactList result ====" + result);
    	return result;
    }

    /**
     * 请求接听设置，API11
     */
    public String RequestTerminalSettings(String imei){
    	String request = HEADER + GTERMINAL_SETTING + IMEI +imei;
    	String result = WebService.getInstance().getRequest(request);
    	return result;
    }

    /**
     * 请求事件提醒，API14
     */
    public String RequestReminderList(String imei,int page,int pagesize){
    	String request = HEADER + REMINDER_LIST + IMEI +imei + '&' + PAGE + page +'&' + PAGE_SIZE +pagesize;
    	String result = WebService.getInstance().getRequest(request);
    	return result;
    }

    /**
     * 请求事件提醒细节，API15
     */
    public String RequestReminderInfo(String id){
    	String request = HEADER + REMINDER_INFO + ID + id;
    	String result = WebService.getInstance().getRequest(request);
    	return result;
    }

    /**
     * 请求终端详细信息，API19
     */
    public String RequestTerminalInfo(String imei,String userid){
    	String request = HEADER + TERMINAL_INFO + IMEI + imei + '&' + USER_ID1 + userid;
    	String result = WebService.getInstance().getRequest(request);
    	return result;
    }

    /**
     * 请求用户个人信息，API22
     */
    public String RequestUserInfoInfo(String userid){
    	String request = HEADER + USER_INFO + USER_ID + userid;
    	String result = WebService.getInstance().getRequest(request);
    	return result;
    }

    /**
     * 请求报警提醒信息，API25
     */
    public String RequestAlertList(String userid,int page,int pagesize){
    	String request = HEADER + ALERT + USER_ID1 + userid + '&' + PAGE + page + '&' + PAGE_SIZE + pagesize;
    	String result = WebService.getInstance().getRequest(request);
    	return result;
    }

    /**
     * 请求血氧，健康测量接口1
     */
    public String RequestBloodOxygen(String imei,String startTime,String endTime,int page,int pagesize){
    	String request = SERVER_HEALTH + BLOOD_OXYGEN;
    	if(imei!=null)request = request + IMEI + imei +'&';
    	if(startTime!=null)request = request + START_TIME + startTime +'&';
    	if(endTime!=null)request = request + END_TIME + endTime + '&';
    	if(page!=0)request = request + PAGE + page + '&';
    	if(pagesize!=0)request = request + PAGE_SIZE + pagesize;
    	Log.v("", request);
    	String result = WebService.getInstance().getAuthenticatedRequest(request);
    	return result;
    }

    /**
     * 请求血压，健康测量接口2
     */
    public String RequestBloodPressure(String imei,String startTime,String endTime,int page,int pagesize){
    	String request = SERVER_HEALTH + BLOOD_PRESSURE;
    	if(imei!=null)request = request + IMEI + imei +'&';
    	if(startTime!=null)request = request + START_TIME + startTime +'&';
    	if(endTime!=null)request = request + END_TIME + endTime + '&';
    	if(page!=0)request = request + PAGE + page + '&';
    	if(pagesize!=0)request = request + PAGE_SIZE + pagesize;
    	String result = WebService.getInstance().getAuthenticatedRequest(request);
    	return result;
    }

    /**
     * 请求血脂，健康测量接口3
     */
    public String RequestBodyCompositions(String imei,String startTime,String endTime,int page,int pagesize){
    	String request = SERVER_HEALTH + BODY_COMPOSITOIN;
    	if(imei!=null)request = request + IMEI + imei +'&';
    	if(startTime!=null)request = request + START_TIME + startTime +'&';
    	if(endTime!=null)request = request + END_TIME + endTime + '&';
    	if(page!=0)request = request + PAGE + page + '&';
    	if(pagesize!=0)request = request + PAGE_SIZE + pagesize;
    	String result = WebService.getInstance().getAuthenticatedRequest(request);
    	return result;
    }

    /**
     * 请求血糖，健康测量接口4
     */
    public String RequestBloodSugar(String imei,String startTime,String endTime,int page,int pagesize){
    	String request = SERVER_HEALTH + BLOOD_SUGAR;
    	if(imei!=null)request = request + IMEI + imei +'&';
    	if(startTime!=null)request = request + START_TIME + startTime +'&';
    	if(endTime!=null)request = request + END_TIME + endTime + '&';
    	if(page!=0)request = request + PAGE + page + '&';
    	if(pagesize!=0)request = request + PAGE_SIZE + pagesize;
    	String result = WebService.getInstance().getAuthenticatedRequest(request);
    	return result;
    }
    
    //上面是get,下面是post

    /**
     * 添加白名单，API9
     */
    public boolean addWhiteList(String imei,String phone,String name){
    	String url = HEADER + ADD_WHITELIST;
    	String body = XMLMakerUtil.getInstance().addWhiteList(imei, phone, name);
    	return WebService.getInstance().PostRequestForBoolean(url, body);
    }

    /**
     * 更新单条白名单信息，API9
     */
    public boolean updateWhiteList(String imei,String id,String phone,String name){
    	String url = HEADER + UPDATE_WHITELIST;
    	String body = XMLMakerUtil.getInstance().updateWhiteList(imei, id, phone, name);
    	return WebService.getInstance().PostRequestForBoolean(url, body);
    }

    /**
     * 删除白名单，API9
     */
    public boolean deleteWhiteList(String imei,String id){
    	String url = HEADER + DELETE_WHITELIST + IMEI +imei +'&' + ID +id;
    	String body = "";
    	return WebService.getInstance().PostRequestForBoolean(url, body);
    }

    /**
     * 更改接听设置,API11
     */
    public boolean updateTerminalSettings(String userid,String imei,boolean auto,boolean receiveAll){
    	String url = HEADER + PTERMINAL_SETTING;
    	String body = XMLMakerUtil.getInstance().terminalSettings(imei, userid, auto, receiveAll);
    	Log.d("Landylitest","body ===== " + body);
    	return WebService.getInstance().PostRequestForBoolean(url, body);
    }

    /**
     * 更改联系人，API13
     */
    public boolean updateContact(String imei,int index,String name,String phone){
    	String url = HEADER + UPDATE_CONTACT;
    	String body = XMLMakerUtil.getInstance().updateContact(imei, index, phone, name);
    	return WebService.getInstance().PostRequestForBoolean(url, body);
    }
    
    /**
     * 更改全部联系人，API13
     */
    public boolean updateAllContact(String imei,ArrayList<Contact> list){
    	String url = HEADER + UPDATE_CONTACT;
    	for(int i = 0 ; i<list.size();i++){
    		String body = XMLMakerUtil.getInstance().updateContact(imei, i,list.get(i).getName(),list.get(i).getPhone());
    		WebService.getInstance().PostRequestForBoolean(url, body);
    	}
    	return true;
    }

    /**
     * 更改密码，API24
     */
    public boolean updatePassword(String username,String oldpassword,String newpassword){
    	String url = HEADER + UPDATE_PASSWORD;
    	String body = XMLMakerUtil.getInstance().updatePassWord(username, oldpassword, newpassword);
    	return WebService.getInstance().PostRequestForBoolean(url, body);
    }

    /**
     * 添加事件提醒，API17
     */
    public boolean addReminder(String imei,int reminderType,boolean[] chooseWeek,boolean switchFlag,String reminderTime,String content){
    	String url = HEADER + ADD_REMINDER;
    	String body = XMLMakerUtil.getInstance().addReminder(imei, reminderType, chooseWeek, switchFlag, reminderTime, content);
    	Log.d("Landylitest","body ==== " + body);
    	return WebService.getInstance().PostRequestForBoolean(url, body);
    }

    /**
     * 更改事件提醒，API16
     */
    public boolean updateReminder(String id,String imei,int reminderType,boolean[] chooseWeek,boolean switchFlag,String reminderTime,String content){
    	String url = HEADER + UPDATE_REMINDER;
    	String body = XMLMakerUtil.getInstance().updateRemider(id, imei, reminderType, chooseWeek, reminderTime, switchFlag, content);
    	return WebService.getInstance().PostRequestForBoolean(url, body);
    }

    /**
     * 删除事件提醒，API27
     */
    public boolean deleteReminder(String id){
    	String url = HEADER + DELETE_REMINDER + ID + id;
    	String body = "";
    	return WebService.getInstance().PostRequestForBoolean(url, body);
    }
    
    //缺少Url
    /**
     * 添加终端，API21
     */
   public boolean addTerminal(String userId,String imei,String Code,String username,String nickname,boolean isMale,String bpHigh,String bpLow,int bloodType,String height,String weight,String medicalHistory,String Allergy,String birthday,String remark){
   	String url = HEADER + ADD_TERMINAL;
   	String body = XMLMakerUtil.getInstance().addTermine(userId, imei, Code, username, nickname, isMale, bpHigh, bpLow, bloodType, height, weight, medicalHistory, Allergy, birthday, remark);
   	return WebService.getInstance().PostRequestForBoolean(url, body);
   }

   /**
    * 更改终端信息，API20
    */
   public boolean updateTerminal(String id,String userId,String imei,String sn,String code,String username,String nickname,boolean isMale,String bpHigh,String bpLow,int bloodType,String height,String weight,String medicalHistory,String allergy,String birthday,String remark){
	   String url = HEADER + UPDATE_TERMINAL;
	   String body = XMLMakerUtil.getInstance().updateTerminal(id, userId, imei, sn, code, username, nickname, isMale, bpHigh, bpLow, bloodType, height, weight, medicalHistory, allergy, birthday, remark);
	   Log.v("", url);
	   Log.v("",body);
	   return WebService.getInstance().PostRequestForBoolean(url, body);
	   }

   /**
    * 删除终端，API29
    */
   public boolean deleteTerminal(String imei,String userid){
	   String url = HEADER + DELETE_TERMINAL;
	   String body = XMLMakerUtil.getInstance().deleteTermine(imei, userid);
	   return WebService.getInstance().PostRequestForBoolean(url, body);
   }

   /**
    * 更改用户个人信息,API23
    */
    public boolean updateUserInfo(String userid , String usercode,String username,boolean isMale,String email,String address,String birthday,String phone,String startdate){
    	String url = HEADER + UPDATE_USERINFO + USER_ID1 + userid;
    	DebugUtil.Debug("url ==== " + url);
    	String body = XMLMakerUtil.getInstance().updateUserInfo(usercode,username,isMale,email,address,birthday,phone);
    	return WebService.getInstance().PostRequestForBoolean(url, body);
    }//api中还有个startdate条目，意义不明，暂时不管
    
}