package com.changhongit.guanhutong3.utils;

import java.io.StringWriter;
import java.util.ArrayList;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import com.changhongit.guanhutong3.map.utils.DebugUtil;
import com.changhongit.guanhutong3.utils.pulldata.Contact;
import com.changhongit.guanhutong3.utils.pulldata.Reminder;


public class XMLMakerUtil {

	public static XMLMakerUtil instance = null;

	public static XMLMakerUtil getInstance(){  //单例模式
		if(instance == null){
			instance = new XMLMakerUtil();
		}
		return instance;
	}
	/**
	 * 登陆
	 */
	public String login(String username,String password,String validateCode) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);

			serializer.startTag(null, "resource");
			
			serializer.startTag(null, "username");
			serializer.text(username);
			serializer.endTag(null, "username");
			
			serializer.startTag(null, "password");
			serializer.text(password);
			serializer.endTag(null, "password");
			
			serializer.startTag(null, "validateCode");
			serializer.text(validateCode);
			serializer.endTag(null, "validateCode");
			
			serializer.endTag(null, "resource");
			serializer.endDocument();
			serializer.flush();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return writer.toString();
	}
	/**
	 * 设置联络人
	 */
	public String SetLiaisons(String imei,String index,String phone,String name) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);

			serializer.startTag(null, "resource");
			
			serializer.startTag(null, "imei");
			serializer.text(imei);
			serializer.endTag(null, "imei");
			
			serializer.startTag(null, "index");
			serializer.text(index);
			serializer.endTag(null, "index");
			
			serializer.startTag(null, "phone");
			serializer.text(phone);
			serializer.endTag(null, "phone");
			
			serializer.startTag(null, "name");
			serializer.text(name);
			serializer.endTag(null, "name");
			
			serializer.endTag(null, "resource");
			serializer.endDocument();
			serializer.flush();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return writer.toString();
	}
	/**
	 * 接听设置
	 */
	public String callSet(String userid,String imei,String autoReceive,String answerScope) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);

			serializer.startTag(null, "resource");
			
			serializer.startTag(null, "userid");
			serializer.text(userid);
			serializer.endTag(null, "userid");
			
			serializer.startTag(null, "imei");
			serializer.text(imei);
			serializer.endTag(null, "imei");
			
			serializer.startTag(null, "autoReceive");
			serializer.text(autoReceive);
			serializer.endTag(null, "autoReceive");
			
			serializer.startTag(null, "answerScope");
			serializer.text(answerScope);
			serializer.endTag(null, "answerScope");
			
			serializer.endTag(null, "resource");
			serializer.endDocument();
			serializer.flush();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return writer.toString();
	}
	/**
	 * 修改事件提醒详情
	 */
	public String updateRemider(String id,String imei,int reminderType,boolean[] chooseWeek,String reminderTime,boolean switchFlag,String content) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);

			serializer.startTag(null, "resource");
			
			serializer.startTag(null, "id");
			serializer.text(id);
			serializer.endTag(null, "id");
			
			serializer.startTag(null, "imei");
			serializer.text(imei);
			serializer.endTag(null, "imei");
			
			serializer.startTag(null, "switchFlag");
			if(switchFlag)serializer.text("1");
			else serializer.text("0");
			serializer.endTag(null, "switchFlag");
			
			serializer.startTag(null, "reminderType");
			switch(reminderType){         //根据模式选择发送的数据类型
			case Reminder.ONE:
				serializer.text("one");
				serializer.endTag(null, "reminderType");
				
				serializer.startTag(null, "oneReminderTime");
				serializer.text(reminderTime);
				serializer.endTag(null, "oneReminderTime");
				
				break;
			case Reminder.DAY:
				serializer.text("day");
				serializer.endTag(null, "reminderType");
				
				serializer.startTag(null, "reminderTime");
				serializer.text(reminderTime);
				serializer.endTag(null, "reminderTime");
				break;
			case Reminder.WEEK:
				serializer.text("week");
				serializer.endTag(null, "reminderType");
				
				serializer.startTag(null, "reminderTime");
				serializer.text(reminderTime);
				serializer.endTag(null, "reminderTime");
				
				serializer.startTag(null, "chooseWeek");
				String tmp = new String();
				for(int i=0;i<7;i++){
					if(chooseWeek[i])tmp+=String.valueOf(i+1)+',';
				}
				if(!tmp.equals(""))tmp = tmp.substring(0,tmp.length()-1);//不为空时去掉最后一个逗号
				serializer.endTag(null, "chooseWeek");
				break;
			}
			
			serializer.startTag(null, "content");
			serializer.text(content);
			serializer.endTag(null, "content");
			
			serializer.endTag(null, "resource");
			serializer.endDocument();
			serializer.flush();
		}catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return writer.toString();
	}
	/**
	 * 新增事件详情
	 */
	public String addReminder(String imei,int reminderType,boolean[] chooseWeek,boolean switchFlag,String reminderTime,String content) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try { 
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);

			serializer.startTag(null, "resource");
			
			serializer.startTag(null, "imei");
			serializer.text(imei);
			serializer.endTag(null, "imei");
			

			serializer.startTag(null, "switchFlag");
			if(switchFlag)serializer.text("1");
			else serializer.text("0");
			serializer.endTag(null, "switchFlag");
			
			serializer.startTag(null, "reminderType");
			switch(reminderType){         //根据模式选择发送的数据类型
			case Reminder.ONE:
				serializer.text("one");
				serializer.endTag(null, "reminderType");
				
				serializer.startTag(null, "oneReminderTime");
				serializer.text(reminderTime);
				serializer.endTag(null, "oneReminderTime");
				
				break;
			case Reminder.DAY:
				serializer.text("day");
				serializer.endTag(null, "reminderType");
				
				serializer.startTag(null, "reminderTime");
				serializer.text(reminderTime);
				serializer.endTag(null, "reminderTime");
				break;
			case Reminder.WEEK:
				serializer.text("week");
				serializer.endTag(null, "reminderType");
				
				serializer.startTag(null, "reminderTime");
				serializer.text(reminderTime);
				serializer.endTag(null, "reminderTime");
				
				serializer.startTag(null, "chooseWeek");
				String tmp = new String();
				for(int i=0;i<7;i++){
					if(chooseWeek[i])tmp+=String.valueOf(i+1)+',';
				}
				if(!tmp.equals(""))tmp = tmp.substring(0,tmp.length()-1);//不为空时去掉最后一个逗号
				serializer.endTag(null, "chooseWeek");
				break;
			}
			
			serializer.startTag(null, "content");
			serializer.text(content);
			serializer.endTag(null, "content");
			
			serializer.endTag(null, "resource");
			serializer.endDocument();
			serializer.flush();
			}catch (Exception e) {
			e.toString();
		}
		return writer.toString();
	}
	/**
	 * 新增关护通终端
	 */
	public String addTermine(String userId,String imei,String Code,String username,String nickname,boolean isMale,String bpHigh,String bpLow,int bloodType,String height,String weight,String medicalHistory,String Allergy,String birthday,String remark) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);

			serializer.startTag(null, "resource");
			
			serializer.startTag(null, "userId");
			serializer.text(userId);
			serializer.endTag(null, "userId");
			
			serializer.startTag(null, "imei");
			serializer.text(imei);
			serializer.endTag(null, "imei");
			
			serializer.startTag(null, "Code");
			serializer.text(Code);
			serializer.endTag(null, "Code");
			
			serializer.startTag(null, "username");
			serializer.text(username);
			serializer.endTag(null, "username");
			
			serializer.startTag(null, "nickname");
			serializer.text(nickname);
			serializer.endTag(null, "nickname");
			
			serializer.startTag(null, "sex");
			if(isMale)serializer.text("1");
			else serializer.text("0");
			serializer.endTag(null, "sex");
			
			serializer.startTag(null, "sbp");
			serializer.text(bpHigh);
			serializer.endTag(null, "sbp");
			
			serializer.startTag(null, "dbp");
			serializer.text(bpLow);
			serializer.endTag(null, "dbp");
			
			serializer.startTag(null, "bloodtype");
			serializer.text(String.valueOf(bloodType));
			serializer.endTag(null, "bloodtype");
			
			serializer.startTag(null, "height");
			serializer.text(height);
			serializer.endTag(null, "height");
			
			serializer.startTag(null, "weight");
			serializer.text(weight);
			serializer.endTag(null, "weight");
			
			serializer.startTag(null, "health");
			serializer.text(medicalHistory);
			serializer.endTag(null, "health");
			
			serializer.startTag(null, "grugallergy");
			serializer.text(Allergy);
			serializer.endTag(null, "grugallergy");
			
			serializer.startTag(null, "birthday");
			serializer.text(birthday);
			serializer.endTag(null, "birthday");
			
			serializer.startTag(null, "remark");
			serializer.text(remark);
			serializer.endTag(null, "remark");
			
			serializer.endTag(null, "resource");
			serializer.endDocument();
			serializer.flush();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return writer.toString();
	}
	/**
	 * 修改关护通终端
	 */
	public String updateTerminal(String id,String userId,String imei,String sn,String code,String username,String nickname,boolean isMale,String bpHigh,String bpLow,int bloodType,String height,String weight,String medicalHistory,String allergy,String birthday,String remark) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("utf-8", false);

			serializer.startTag(null, "resource");
			
			serializer.startTag(null, "id");
			serializer.text(id);
			serializer.endTag(null, "id");
			
			serializer.startTag(null, "userId");
			serializer.text(userId);
			serializer.endTag(null, "userId");
			
			serializer.startTag(null, "imei");
			serializer.text(imei);
			serializer.endTag(null, "imei");
			
			serializer.startTag(null, "sn");
			serializer.text(sn);
			serializer.endTag(null, "sn");
			
			serializer.startTag(null, "code");
			serializer.text(code);
			serializer.endTag(null, "code");
			
			serializer.startTag(null, "username");
			serializer.text(username);
			serializer.endTag(null, "username");
			
			serializer.startTag(null, "nickname");
			serializer.text(nickname);
			serializer.endTag(null, "nickname");
			
			serializer.startTag(null, "sex");
			if(isMale)serializer.text("1");
			else serializer.text("0");
			serializer.endTag(null, "sex");
			
			serializer.startTag(null, "birthday");
			serializer.text(birthday);
			serializer.endTag(null, "birthday");
			
			serializer.startTag(null, "height");
			serializer.text(height);
			serializer.endTag(null, "height");
			
			serializer.startTag(null, "weight");
			serializer.text(weight);
			serializer.endTag(null, "weight");
			
			serializer.startTag(null, "bloodType");
			serializer.text(String.valueOf(bloodType));
			serializer.endTag(null, "bloodType");
			
			serializer.startTag(null, "sbp");
			serializer.text(bpHigh);
			serializer.endTag(null, "sbp");
			
			serializer.startTag(null, "dbp");
			serializer.text(bpLow);
			serializer.endTag(null, "dbp");
			
			serializer.startTag(null, "grugallergy");
			serializer.text(allergy);
			serializer.endTag(null, "grugallergy");
			
			serializer.startTag(null, "health");
			serializer.text(medicalHistory);
			serializer.endTag(null, "health");
			
			serializer.startTag(null, "remark");
			serializer.text(remark);
			serializer.endTag(null, "remark");
			
			serializer.endTag(null, "resource");
			serializer.endDocument();
			serializer.flush();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return writer.toString();
	}
	/**
	 * 解除终端
	 */
	public String deleteTermine(String imei,String userid) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);

			serializer.startTag(null, "resource");
			
			serializer.startTag(null, "imei");
			serializer.text(imei);
			serializer.endTag(null, "imei");
			
			serializer.startTag(null, "userid");
			serializer.text(userid);
			serializer.endTag(null, "userid");
						
			serializer.endTag(null, "resource");
			serializer.endDocument();
			serializer.flush();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return writer.toString();
	}
	/**
	 * 修改密码
	 */
	public String updatePassWord(String username,String oldpassword,String newpassword) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);

			serializer.startTag(null, "resource");
			
			serializer.startTag(null, "username");
			serializer.text(username);
			serializer.endTag(null, "username");
			
			serializer.startTag(null, "oldpassword");
			serializer.text(oldpassword);
			serializer.endTag(null, "oldpassword");
			
			serializer.startTag(null, "newpassword");
			serializer.text(newpassword);
			serializer.endTag(null, "newpassword");
					
			serializer.endTag(null, "resource");
			serializer.endDocument();
			serializer.flush();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return writer.toString();
	}
	/**
	 * 新增白名单
	 */
	public String addWhiteList(String imei,String phone, String name) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);

			serializer.startTag(null, "resource");
			
			serializer.startTag(null, "imei");
			serializer.text(imei);
			serializer.endTag(null, "imei");
			
			serializer.startTag(null, "phone");
			serializer.text(phone);
			serializer.endTag(null, "phone");
			
			serializer.startTag(null, "name");
			serializer.text(name);
			serializer.endTag(null, "name");
			
			serializer.endTag(null, "resource");
			serializer.endDocument();
			serializer.flush();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return writer.toString();
	}
	/**
	 * 更新白名单
	 */
	public String updateWhiteList(String imei,String id,String phone,String name) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);

			serializer.startTag(null, "resource");
			
			
			serializer.startTag(null, "imei");
			serializer.text(imei);
			serializer.endTag(null, "imei");
			
			serializer.startTag(null, "id");
			serializer.text(id);
			serializer.endTag(null, "id");
			
			serializer.startTag(null, "phone");
			serializer.text(phone);
			serializer.endTag(null, "phone");
			
			serializer.startTag(null, "name");
			serializer.text(name);
			serializer.endTag(null, "name");
			
			serializer.endTag(null, "resource");
			serializer.endDocument();
			serializer.flush();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return writer.toString();
	}
	/**
	 * 接听设置 
	 */
	public String terminalSettings(String imei,String userid,boolean auto,boolean receiveAll) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);

			serializer.startTag(null, "resource");
			
			
			serializer.startTag(null, "userid");
			serializer.text(userid);
			serializer.endTag(null, "userid");
			
			serializer.startTag(null, "imei");
			serializer.text(imei);
			serializer.endTag(null, "imei");
			
			serializer.startTag(null, "autoReceive");
			if(auto)serializer.text("开启");
			else serializer.text("关闭");
			serializer.endTag(null, "autoReceive");
			
			serializer.startTag(null, "answerScope");
			if(receiveAll)serializer.text("所有电话");
			else serializer.text("白名单");
			serializer.endTag(null, "answerScope");
			
			serializer.endTag(null, "resource");
			serializer.endDocument();
			serializer.flush();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return writer.toString();
	}
	/**
	 * 联络人设置 
	 */
	public String updateContact(String imei,int index,String phone,String name) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);

			serializer.startTag(null, "resource");
			
			serializer.startTag(null, "imei");
			serializer.text(imei);
			serializer.endTag(null, "imei");
			
			serializer.startTag(null, "index");
			serializer.text(String.valueOf(index));
			serializer.endTag(null, "index");
			
			serializer.startTag(null, "phone");
			serializer.text(phone);
			serializer.endTag(null, "phone");
			
			serializer.startTag(null, "name");
			if(name!=null)serializer.text(name);
			serializer.endTag(null, "name");
			
			serializer.endTag(null, "resource");
			serializer.endDocument();
			serializer.flush();
		}catch (Exception e) {
			// TODO: handle exception
		}
		DebugUtil.Debug("update contacts ==== "  + writer.toString());
		return writer.toString();
	}
	/**
	 * 联络人设置 
	 */
	public String updateAllContact(String imei,ArrayList<Contact> list) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);

			serializer.startTag(null, "resource");
			
			serializer.startTag(null, "imei");
			serializer.text(imei);
			serializer.endTag(null, "imei");
			
			for(int i = 0; i<list.size();i++){
			serializer.startTag(null, "index");
			serializer.text(String.valueOf(i));
			serializer.endTag(null, "index");
			
			serializer.startTag(null, "phone");
			serializer.text(list.get(i).getPhone());
			serializer.endTag(null,"phone" );
			
			serializer.startTag(null, "name");
			serializer.text(list.get(i).getName());
			serializer.endTag(null, "name");
			}
			
			serializer.endTag(null, "resource");
			serializer.endDocument();
			serializer.flush();
		}catch (Exception e) {
			// TODO: handle exception
		}
		DebugUtil.Debug("update contacts ==== "  + writer.toString());
		return writer.toString();
	}
	
	/**
	 * 修改用户
	 */
	public String updateUserInfo(String usercode,String username,boolean isMale,String email,String address,String birthday,String phone){
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			serializer.startTag(null, "resource");

			serializer.startTag(null, "usercode");
			serializer.text(usercode);
			serializer.endTag(null, "usercode");
			
			serializer.startTag(null, "username");
			serializer.text(username);
			serializer.endTag(null, "username");
			
			serializer.startTag(null, "gener");
			if(isMale)serializer.text("1");
			else serializer.text("0");
			serializer.endTag(null, "gener");
			
			serializer.startTag(null, "birthday");
			serializer.text(birthday);
			serializer.endTag(null, "birthday");
			
			serializer.startTag(null, "phone");
			serializer.text(phone);
			serializer.endTag(null, "phone");
			
			serializer.startTag(null, "email");
			serializer.text(email);
			serializer.endTag(null, "email");
			
			serializer.startTag(null, "address");
			serializer.text(address);
			serializer.endTag(null, "address");
			
			serializer.endTag(null, "resource");
			serializer.endDocument();
			serializer.flush();
		}catch (Exception e) {
			// TODO: handle exception
		}
		DebugUtil.Debug("personal setting xml====" + writer.toString());
		
		return writer.toString();
	}
}