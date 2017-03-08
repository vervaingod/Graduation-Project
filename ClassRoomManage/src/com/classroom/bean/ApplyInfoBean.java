package com.classroom.bean;

import java.io.Serializable;

public class ApplyInfoBean  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String applystudent;
	private String applyclassroom;
	private int roomstate;
	private int applystate;
	private String applyreply;
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApplystudent() {
		return applystudent;
	}

	public void setApplystudent(String applystudent) {
		this.applystudent = applystudent;
	}

	public String getApplyclassroom() {
		return applyclassroom;
	}

	public void setApplyclassroom(String applyclassroom) {
		this.applyclassroom = applyclassroom;
	}

	public int getRoomstate() {
		return roomstate;
	}

	public void setRoomstate(int roomstate) {
		this.roomstate = roomstate;
	}


	public int getApplystate() {
		return applystate;
	}

	public void setApplystate(int applystate) {
		this.applystate = applystate;
	}

	public String getApplyreply() {
		return applyreply;
	}

	public void setApplyreply(String applyreply) {
		this.applyreply = applyreply;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
