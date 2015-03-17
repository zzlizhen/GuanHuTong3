package com.changhongit.guanhutong3.utils.pulldata;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//查询终端状态
public class Status {

    private String imei = null;
    private Date validTime = null; // 有效期

    private int state;
    public final static int STATE_ONLINE = 1; // 正常在线
    public final static int STATE_OFFLINE = 0; // 离线
    public final static int STATE_ARREARAGE = -1; // 欠费
    public final static int STATE_mARREARAGE = -2;// 即将欠费
    private boolean cash; // 付费方式
    private boolean gift;
    private String power;
    private boolean listenset; // 接听方式
    private boolean autolisten; // 自动接听方式

    public int getState() {
        return state;
    }

    public String getStateToString() {
        String str = null;
        switch (state) {
        case 0:
            str = "离线";
            break;
        case 1:
            str = "正常在线";
            break;
        case -1:
            str = "欠费";
            break;
        case -2:
            str = "即将欠费";
            break;
        }
        return str;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Date getValidTime() {
        return validTime;
    }

    public String getValidTimeToString() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(validTime);
    }

    /**
     * 输入String转换成Date类，如果api改变可能需要改模板
     * 
     */
    public void setValidTime(String validTime) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(validTime);
        this.validTime = date;
    }

    public boolean isCash() {
        return cash;
    }

    public void setCash(boolean cash) {
        this.cash = cash;
    }

    public boolean isGift() {
        return gift;
    }

    public void setGift(boolean gift) {
        this.gift = gift;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public boolean isListenset() {
        return listenset;
    }

    public void setListenset(boolean listenset) {
        this.listenset = listenset;
    }

    public boolean isAutolisten() {
        return autolisten;
    }

    public void setAutolisten(boolean autolisten) {
        this.autolisten = autolisten;
    }

}
