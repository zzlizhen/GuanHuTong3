package com.changhongit.guanhutong3.utils.pulldata;

public class BloodPressure {
	
	private String imei;
	private String dector;
	private String systolic;
	private String diastolic;
	private String pulse;
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
	public String getPulse() {
		return pulse;
	}
	public void setPulse(String pulse) {
		this.pulse = pulse;
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
