package com.classroom.dao;

import java.util.List;

import com.classroom.bean.PeopleBean;

public interface PeopleDao {
	public PeopleBean login(PeopleBean peopleBean) throws Exception;

	public List<PeopleBean> fetchAllStudentList() throws Exception;

	public int registermanager(PeopleBean peopleBean) throws Exception;

	public int fetchUsername(String username) throws Exception;

	public int fetchName(String name) throws Exception;

	public int StartStudent(String username) throws Exception;

	public int StopStudent(String username) throws Exception;

	public List<PeopleBean> fetchAllmanageList() throws Exception;

	public List<PeopleBean> searchStudentList(String name, String username,
			int state) throws Exception;

	public int registerStudent(PeopleBean peopleBean) throws Exception;
}
