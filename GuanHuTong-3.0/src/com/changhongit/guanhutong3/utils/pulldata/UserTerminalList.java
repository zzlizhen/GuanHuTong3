package com.changhongit.guanhutong3.utils.pulldata;

import java.util.ArrayList;
import java.util.List;

//用于存放设备信息列表
//有待改动，先放着
public class UserTerminalList {

    private int totalcount;
    private ArrayList<UserTerminal> list = null;

    public UserTerminalList() {
        list = new ArrayList<UserTerminal>();
    }

    public void additem(UserTerminal terminal) {
        list.add(terminal);
    }

    public UserTerminal getitem(int i) {
        return list.get(i);
    }

    public List<UserTerminal> getlist() {
        return (List<UserTerminal>) list;
    }

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

}
