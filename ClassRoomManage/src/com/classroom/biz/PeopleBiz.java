package com.classroom.biz;

import java.util.List;

import com.classroom.bean.PeopleBean;

public interface PeopleBiz {
	public PeopleBean login(PeopleBean peopleBean);

	public List<PeopleBean> fetchAllStudentList();

	public int registerManager(PeopleBean peopleBean);
	
	public int registerStudent(PeopleBean peopleBean);

	public int fetchUsername(String username);

	public int fetchName(String name);

	public int StartStudent(String username) throws Exception;

	public int StopStudent(String username) throws Exception;

	public List<PeopleBean> fetchAllManageList();
	public List<PeopleBean> searchStudentList(String name,String username,int state) throws Exception;
}
