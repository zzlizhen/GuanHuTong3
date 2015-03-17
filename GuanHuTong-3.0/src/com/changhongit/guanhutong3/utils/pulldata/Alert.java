package com.changhongit.guanhutong3.utils.pulldata;

import android.os.Parcel;
import android.os.Parcelable;

public class Alert implements Parcelable{
	
	private String id;
	private String imei;
	private String devicename;
	private String latitude;
	private String longitude;
	private String time;
	private String type;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Alert() {
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(id);
		dest.writeString(imei);
		dest.writeString(devicename);
		dest.writeString(latitude);
		dest.writeString(longitude);
		dest.writeString(time);
		dest.writeString(type);
	}

	private Alert(Parcel in){
		id = in.readString();
		imei = in.readString();
		devicename = in.readString();
		latitude = in.readString();
		longitude = in.readString();
		time = in.readString();
		type = in.readString();
	}
	

	public static Parcelable.Creator<Alert> CREATOR = new Parcelable.Creator<Alert>() {

		@Override
		public Alert createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Alert(source);
		}

		@Override
		public Alert[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Alert[size];
		}
	};


}
