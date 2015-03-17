package com.changhongit.guanhutong3.utils.pulldata;

public class BloodOxygen {
	private String imei;
	private String dector;
	private String oxygenSaturation;
	private String pulse;
	private String tiemStamp;
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
	public String getOxygenSaturation() {
		return oxygenSaturation;
	}
	public void setOxygenSaturation(String oxygenSaturation) {
		this.oxygenSaturation = oxygenSaturation;
	}
	public String getPulse() {
		return pulse;
	}
	public void setPulse(String pulse) {
		this.pulse = pulse;
	}
	public String getTiemStamp() {
		return tiemStamp;
	}
	public void setTiemStamp(String tiemStamp) {
		this.tiemStamp = tiemStamp;
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
