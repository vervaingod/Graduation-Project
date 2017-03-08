package com.classroom.biz;

import java.util.List;

import com.classroom.bean.ClassBean;

public interface ClassBiz {
	public List<ClassBean> fetchAllClass();

	public int startClass(String classnum) throws Exception;

	public int stopClass(String classnum) throws Exception;

	public int addClass(ClassBean classBean) throws Exception;

	public List<ClassBean> searchClass(String classnum, String classname,
			String classroom) throws Exception;
}
