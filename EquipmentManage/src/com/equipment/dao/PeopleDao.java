package com.equipment.dao;

import java.util.List;

import com.equipment.bean.PeopleBean;

public interface PeopleDao {
	public PeopleBean login(PeopleBean peopleBean) throws Exception;

	public PeopleBean findUserInformationByID(int user_id) throws Exception;

	public List<PeopleBean> FindAllUserByRole(int findrole, int pageno)
			throws Exception;

	public List<PeopleBean> FindAllGuy(int findrole) throws Exception;

	public List<PeopleBean> FindAllUserByDepeartment(int department_id,
			int pageno) throws Exception;

	public int initializaPassword(int id) throws Exception;

	public String findDepartmentById(int user_department_id) throws Exception;

	public int addPeople(PeopleBean peopleBean) throws Exception;

	public int changePassword(int people_id, String new_password)
			throws Exception;

	public int updateUserByUserBean(PeopleBean peopleBean) throws Exception;

	public int updateUserByUsername(String user_username, String user_name,
			String user_phone) throws Exception;

	public String findPhoneByName(String name) throws Exception;

	public int findUserIdByname(String name) throws Exception;

	public int countUserByusername(String user_username) throws Exception;

	public String findNameByID(int user_id) throws Exception;

	public int countUserRowsByRole(int role) throws Exception;

	public int countUserRowsByDepartment(int user_department_id)
			throws Exception;
}
