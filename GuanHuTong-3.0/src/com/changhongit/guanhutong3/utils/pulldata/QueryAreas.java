package com.changhongit.guanhutong3.utils.pulldata;

public class QueryAreas {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public boolean isAreaType() {
        return areaType;
    }

    public void setAreaType(boolean areaType) {
        this.areaType = areaType;
    }

    public String getXcoord() {
        return Xcoord;
    }

    public void setXcoord(String xcoord) {
        Xcoord = xcoord;
    }

    public String getYcoord() {
        return Ycoord;
    }

    public void setYcoord(String ycoord) {
        Ycoord = ycoord;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getLeftXcoord() {
        return leftXcoord;
    }

    public void setLeftXcoord(String leftXcoord) {
        this.leftXcoord = leftXcoord;
    }

    public String getLeftYcoord() {
        return leftYcoord;
    }

    public void setLeftYcoord(String leftYcoord) {
        this.leftYcoord = leftYcoord;
    }

    public String getRightXcoord() {
        return rightXcoord;
    }

    public void setRightXcoord(String rightXcoord) {
        this.rightXcoord = rightXcoord;
    }

    public String getRightYCoord() {
        return rightYCoord;
    }

    public void setRightYCoord(String rightYCoord) {
        this.rightYCoord = rightYCoord;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public int isIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getPreviousUrl() {
        return previousUrl;
    }

    public void setPreviousUrl(String previousUrl) {
        this.previousUrl = previousUrl;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

    private String id = null;
    private String deviceName = null;
    private boolean areaType;
    private String Xcoord = null;
    private String Ycoord = null;
    private String radius = null;
    private String leftXcoord = null;
    private String leftYcoord = null;
    private String rightXcoord = null;
    private String rightYCoord = null;
    private String contactName = null;
    private int index;

    private String previousUrl;
    private String nextUrl;

    private int totalcount;
}
