package com.changhongit.guanhutong3.utils.pulldata;

public class Contact {
	private String phone;
	private String name;
	
	
	public Contact() {
		// TODO Auto-generated constructor stub
	}
	
	public Contact( String name,String phone) {
		super();
		this.phone = phone;
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name+"/"+phone;
	}
	
	
}
