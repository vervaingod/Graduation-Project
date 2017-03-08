package com.classroom.bean;

import java.io.Serializable;

public class ClassroomBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String classroomnumber;
	private String room;
	private int state;
	private String note;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassroomnumber() {
		return classroomnumber;
	}

	public void setClassroomnumber(String classroomnumber) {
		this.classroomnumber = classroomnumber;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
