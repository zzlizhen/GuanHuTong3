package com.changhongit.guanhutong3.utils.pulldata;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//位置信息
public class Location {

    private String iemi = null;
    private String username = null;
    private String latitude = null;
    private String longitude = null;
    private Date locationtime = null;

    public String getIemi() {
        return iemi;
    }

    public void setIemi(String iemi) {
        this.iemi = iemi;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getLocationtime() {
        return locationtime;
    }

    /**
     * 输入String转换成Date类，如果api改变可能需要改模板
     * 
     */
    public void setLocationtime(String locationtime) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(locationtime);
        this.locationtime = date;
    }

    public void setLocationInfo(String info) throws ParseException {
        String[] tmp = info.split("\\|"); // 以|分割字符串，分别为时间，纬度，经度
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.locationtime = format.parse(tmp[0]);
        this.latitude = tmp[1];
        this.longitude = tmp[2];
    }

    public String getLocationTimeToString() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(locationtime);
    }

}
