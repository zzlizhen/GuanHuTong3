package com.changhongit.guanhutong3.utils.pulldata;

import java.util.ArrayList;
import java.util.List;

public class LocationHistoryList {

    private int totalcount;
    private ArrayList<Location> list = new ArrayList<Location>();

    public void additem(Location loc) {
        list.add(loc);
    }

    public Location getitem(int i) {
        return list.get(i);
    }

    public ArrayList<Location> getlist() {
        return list;
    }
    
    public LocationHistoryList mergeList(LocationHistoryList loclist){
    	list.addAll(loclist.getlist());
    	totalcount = loclist.gettotalcount();
    	return this;
    }

    public int gettotalcount() {
        return totalcount;
    }

    public void settotalcount(int cou) {
        this.totalcount = cou;
    }

}
