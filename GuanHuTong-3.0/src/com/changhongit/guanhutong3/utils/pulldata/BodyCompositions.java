package com.changhongit.guanhutong3.utils.pulldata;

public class BodyCompositions {
	
	private String imei;
	private String dector;
	private String muscle;
	private String adiposerate;
	private String visceralfat;
	private String moisture;
	private String bone;
	private String thermal;
	private String impedance;
	private String bmi;
	private String bmr;
	private String timeStamp;
	private Advice advice = new Advice();
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getDector() {
		return dector;
	}
	public void setDector(String dector) {
		this.dector = dector;
	}
	public String getMuscle() {
		return muscle;
	}
	public void setMuscle(String muscle) {
		this.muscle = muscle;
	}
	public String getAdiposerate() {
		return adiposerate;
	}
	public void setAdiposerate(String adiposerate) {
		this.adiposerate = adiposerate;
	}
	public String getVisceralfat() {
		return visceralfat;
	}
	public void setVisceralfat(String visceralfat) {
		this.visceralfat = visceralfat;
	}
	public String getMoisture() {
		return moisture;
	}
	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}
	public String getBone() {
		return bone;
	}
	public void setBone(String bone) {
		this.bone = bone;
	}
	public String getThermal() {
		return thermal;
	}
	public void setThermal(String thermal) {
		this.thermal = thermal;
	}
	public String getImpedance() {
		return impedance;
	}
	public void setImpedance(String impedance) {
		this.impedance = impedance;
	}
	public String getBmi() {
		return bmi;
	}
	public void setBmi(String bmi) {
		this.bmi = bmi;
	}
	public String getBmr() {
		return bmr;
	}
	public void setBmr(String bmr) {
		this.bmr = bmr;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getAdviceStatus() {
		return advice.getStatus();
	}
	public void setAdviceStatus(String adviceStatus) {
		advice.setStatus(adviceStatus);
	}
	public String getAdviceResult() {
		return advice.getResult();
	}
	public void setAdviceResult(String adviceResult) {
		advice.setResult(adviceResult);
	}
	public String getAdviceFood() {
		return advice.getFood();
	}
	public void setAdviceFood(String adviceFood) {
		advice.setFood(adviceFood);
	}
	public String getAdviceSport() {
		return advice.getSport();
	}
	public void setAdviceSport(String adviceSport) {
		advice.setSport(adviceSport);
	}
	public String getAdviceDoctor() {
		return advice.getDoctor();
	}
	public void setAdviceDoctor(String adviceDoctor) {
		advice.setDoctor(adviceDoctor);
	}
	public String getAdviceDaily() {
		return advice.getDaily();
	}
	public void setAdviceDaily(String adviceDaily) {
		advice.setDaily(adviceDaily);
	}
}
