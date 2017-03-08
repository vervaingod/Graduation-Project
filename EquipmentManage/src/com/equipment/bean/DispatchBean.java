package com.equipment.bean;

public class DispatchBean {
	private int dispatch_id;
	private int dispatch_user_id;
	private int dispatch_maintenance_id;
	private String dispatch_expect_time;
	private String dispatch_maintenance_phone;

	public int getDispatch_id() {
		return dispatch_id;
	}

	public void setDispatch_id(int dispatch_id) {
		this.dispatch_id = dispatch_id;
	}

	public int getDispatch_user_id() {
		return dispatch_user_id;
	}

	public void setDispatch_user_id(int dispatch_user_id) {
		this.dispatch_user_id = dispatch_user_id;
	}

	public int getDispatch_maintenance_id() {
		return dispatch_maintenance_id;
	}

	public void setDispatch_maintenance_id(int dispatch_maintenance_id) {
		this.dispatch_maintenance_id = dispatch_maintenance_id;
	}

	public String getDispatch_expect_time() {
		return dispatch_expect_time;
	}

	public void setDispatch_expect_time(String dispatch_expect_time) {
		this.dispatch_expect_time = dispatch_expect_time;
	}

	public String getDispatch_maintenance_phone() {
		return dispatch_maintenance_phone;
	}

	public void setDispatch_maintenance_phone(String dispatch_maintenance_phone) {
		this.dispatch_maintenance_phone = dispatch_maintenance_phone;
	}

}
