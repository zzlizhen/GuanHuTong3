package com.changhongit.guanhutong3.utils.pulldata;

import java.util.ArrayList;
import java.util.List;

public class AlertList {
	
	private int totalCount;
	private ArrayList<Alert> list= new ArrayList<Alert>();
	
	public List<Alert> getlist(){
		return list;
	}
	
	public void addAlert(Alert tmp){
		list.add(tmp);
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public void addList(AlertList list){
		this.list.addAll(list.getlist());
	}
	
	public void addList(List<Alert> list){
		this.list.addAll(list);
	}
	
	public Alert get(int i){
		return list.get(i);
	}
	
	
	
}
