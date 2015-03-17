package com.changhongit.guanhutong3.utils.pulldata;

public class BloodSugar {
	
	private String imei;
	private String FPG;
	private String dector;
	private String timestamp;
	private Advice advice = new Advice();
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getFPG() {
		return FPG;
	}
	public void setFPG(String fPG) {
		FPG = fPG;
	}
	public String getDector() {
		return dector;
	}
	public void setDector(String dector) {
		this.dector = dector;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
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
