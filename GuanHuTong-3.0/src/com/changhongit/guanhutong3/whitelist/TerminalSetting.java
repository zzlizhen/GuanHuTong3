package com.changhongit.guanhutong3.whitelist;

public class TerminalSetting {
	
	private String imei;
	private boolean auto;
	private boolean receiveAll;
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public boolean isAuto() {
		return auto;
	}
	public void setAuto(boolean auto) {
		this.auto = auto;
	}
	public boolean isReceiveAll() {
		return receiveAll;
	}
	public void setReceiveAll(boolean receiveAll) {
		this.receiveAll = receiveAll;
	}

}
