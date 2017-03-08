package com.classroom.biz;

import java.util.List;

import com.classroom.bean.ClassBean;
import com.classroom.dao.ClassDao;
import com.classroom.dao.ClassDaoImpl;

public class ClassBizImpl implements ClassBiz {
	ClassDao classDao = new ClassDaoImpl();

	@Override
	public List<ClassBean> fetchAllClass() {
		List<ClassBean> classBeanList = null;
		try {
			classBeanList = classDao.fetchAllClass();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classBeanList;
	}

	@Override
	public int startClass(String classnum) throws Exception {
		int rows = 0;
		try {
			rows = classDao.startClass(classnum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int stopClass(String classnum) throws Exception {
		int rows = 0;
		try {
			rows = classDao.stopClass(classnum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int addClass(ClassBean classBean) throws Exception {
		int rows = 0;
		try {
			rows = classDao.addClass(classBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rows;
	}

	@Override
	public List<ClassBean> searchClass(String classnum, String classname,
			String classroom) throws Exception {
		List<ClassBean> classBeanList = null;
		try {
			classBeanList = classDao
					.searchClass(classnum, classname, classroom);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classBeanList;
	}

}
