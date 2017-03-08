package com.classroom.dao;

import java.util.List;

import com.classroom.bean.ClassBean;

public interface ClassDao {
	public List<ClassBean> fetchAllClass() throws Exception;

	public int addClass(ClassBean classBean) throws Exception;

	public int startClass(String classnum) throws Exception;

	public int stopClass(String classnum) throws Exception;

	public List<ClassBean> searchClass(String classnum, String classname,
			String classroom) throws Exception;
}
