package com.equipment.dao;

import java.util.List;

import com.equipment.bean.DepartmentBean;

public interface DepartmentDao {

	public List<DepartmentBean> fetchAllLocalPoliceStation(int pageno)
			throws Exception;

	public List<DepartmentBean> fetchAllMaintainingUnit(int pageno)
			throws Exception;

	public String findDepartmentById(int user_department_id) throws Exception;

	public int findDepartmentIdByname(String department_name) throws Exception;

	public DepartmentBean fetchDepartmentById(int department_id)
			throws Exception;

	public int updateDepartmentByUsername(String department_username,
			String department_name, String department_linkman,
			String department_tel) throws Exception;

	public int addDepartment(DepartmentBean departmentBean) throws Exception;

	public int countDepartmentByname(String department_name) throws Exception;

	public int countLocalPoliceStation() throws Exception;

	public List<DepartmentBean> fetchAllPoliceStation() throws Exception;

	public int countMaintainingUnit() throws Exception;

	public List<DepartmentBean> fetchAllUnit() throws Exception;
}
