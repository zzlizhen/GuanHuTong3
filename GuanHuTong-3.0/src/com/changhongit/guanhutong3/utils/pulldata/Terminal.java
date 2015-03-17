package com.changhongit.guanhutong3.utils.pulldata;

public class Terminal {
	
	private String id;
	private String userId;
	private String imei;
	private String sn;//暂时意义不明
	private String code;//同意义不明
	private String name;
	private String nickname;
	private boolean isMale;
	private String birthday;
	private String Height;  //厘米单位
	private String weight; //公斤单位
	private int Blood;
	public static final int TYPE_O = 3;
	public static final int TYPE_AB = 2;
	public static final int TYPE_B = 1;
	public static final int TYPE_A = 0;
	private String BPHigh;
	private String BPLow;
	private String allergy;
	private String Remark;
	private String medicalHistory;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImei() {
		return imei;
	}
	public Terminal setImei(String imei) {
		this.imei = imei;
		return this;
	}
	public String getName() {
		return name;
	}
	public Terminal setName(String name) {
		this.name = name;
		return this;
	}
	public String getNickname() {
		return nickname;
	}
	public Terminal setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}
	public boolean getIsMale() {
		return isMale;
	}
	public Terminal setIsMale(boolean b) {
		this.isMale = b;
		return this;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBirthday() {
		return birthday;
	}
	public Terminal setBirthday(String birthday) {
		this.birthday = birthday;
		return this;
	}
	public String getHeight() {
		return Height;
	}
	public Terminal setHeight(String height) {
		Height = height;
		return this;
	}
	public String getWeight() {
		return weight;
	}
	public Terminal setWeight(String weight) {
		this.weight = weight;
		return this;
	}
	public int getBlood() {
		return Blood;
	}
	public Terminal setBlood(int blood) {
		Blood = blood;
		return this;
	}
	public String getBPHigh() {
		return BPHigh;
	}
	public Terminal setBPHigh(String bPHigh) {
		BPHigh = bPHigh;
		return this;
	}
	public String getBPLow() {
		return BPLow;
	}
	public Terminal setBPLow(String bPLow) {
		BPLow = bPLow;
		return this;
	}
	public String getAllergy() {
		return allergy;
	}
	public Terminal setAllergy(String allergy) {
		this.allergy = allergy;
		return this;
	}
	public String getRemark() {
		return Remark;
	}
	public Terminal setRemark(String remark) {
		this.Remark = remark;
		return this;
	}
	public String getMedicalHistory() {
		return medicalHistory;
	}
	public Terminal setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
