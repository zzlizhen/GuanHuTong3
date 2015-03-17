package com.changhongit.guanhutong3.utils.pulldata;

public class UserInfo {
	
	private String userCode;
	private String userName;
	private boolean isMale;
	private String birthday;
	private String phone;
	private String eMail;
	private String address;
	
	public UserInfo(String usercode,String username,boolean isMale,String birthday,String phone,String eMail,String address){
		this.userCode = usercode;
		this.userName = username;
		this.isMale = isMale;
		this.birthday = birthday;
		this.phone = phone;
		this.eMail = eMail;
		this.address = address;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public UserInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String usercode) {
		this.userCode = usercode;
	}
	public boolean isMale() {
		return isMale;
	}
	public void setisMale(boolean isMale) {
		this.isMale = isMale;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEMail() {
		return eMail;
	}
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	
}
