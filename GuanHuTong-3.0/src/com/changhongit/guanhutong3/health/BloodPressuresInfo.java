package com.changhongit.guanhutong3.health;

public class BloodPressuresInfo {
	String imei;
	float pulse;			//脉搏
	String dectorId;
	String dectorType;
	String dectorCotent;
	String timestamp;
	String systolic;  //收缩压
	String diastolic; //舒张压
	String adviceStatus;
	String adviceCode;
	String adviceSource;
	String advice_name_result;
	String advice_name_food;
	String advice_name_sport;
	String advice_name_doctor;
	String advice_name_daily;
	String delete_url;
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getDectorId() {
		return dectorId;
	}
	public void setDectorId(String dectorId) {
		this.dectorId = dectorId;
	}
	public String getDectorType() {
		return dectorType;
	}
	public void setDectorType(String dectorType) {
		this.dectorType = dectorType;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getAdviceStatus() {
		return adviceStatus;
	}
	public void setAdviceStatus(String adviceStatus) {
		this.adviceStatus = adviceStatus;
	}
	public String getAdviceCode() {
		return adviceCode;
	}
	public void setAdviceCode(String adviceString) {
		this.adviceCode = adviceString;
	}
	public String getAdviceSource() {
		return adviceSource;
	}
	public void setAdviceSource(String adviceSource) {
		this.adviceSource = adviceSource;
	}
	public String getAdvice_name_result() {
		return advice_name_result;
	}
	public void setAdvice_name_result(String advice_name_result) {
		this.advice_name_result = advice_name_result;
	}
	public String getAdvice_name_food() {
		return advice_name_food;
	}
	public void setAdvice_name_food(String advice_name_food) {
		this.advice_name_food = advice_name_food;
	}
	public String getAdvice_name_sport() {
		return advice_name_sport;
	}
	public void setAdvice_name_sport(String advice_name_sport) {
		this.advice_name_sport = advice_name_sport;
	}
	public String getAdvice_name_doctor() {
		return advice_name_doctor;
	}
	public void setAdvice_name_doctor(String advice_name_doctor) {
		this.advice_name_doctor = advice_name_doctor;
	}
	public String getAdvice_name_daily() {
		return advice_name_daily;
	}
	public void setAdvice_name_daily(String advice_name_daily) {
		this.advice_name_daily = advice_name_daily;
	}
	public String getDectorCotent() {
		return dectorCotent;
	}
	public void setDectorCotent(String dectorCotent) {
		this.dectorCotent = dectorCotent;
	}
	public float getPulse() {
		return pulse;
	}
	public void setPulse(float pulse) {
		this.pulse = pulse;
	}
	public String getSystolic() {
		return systolic;
	}
	public void setSystolic(String systolic) {
		this.systolic = systolic;
	}
	public String getDiastolic() {
		return diastolic;
	}
	public void setDiastolic(String diastolic) {
		this.diastolic = diastolic;
	}
	public String getDelete_url() {
		return delete_url;
	}
	public void setDelete_url(String delete_url) {
		this.delete_url = delete_url;
	}
		
}
