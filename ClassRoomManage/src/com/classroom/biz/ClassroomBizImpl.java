package com.classroom.biz;

import java.util.List;

import com.classroom.bean.ClassroomBean;
import com.classroom.dao.ClassroomDao;
import com.classroom.dao.ClassroomDaoImpl;

public class ClassroomBizImpl implements ClassroomBiz {
	ClassroomDao classroomDao = new ClassroomDaoImpl();

	@Override
	public List<ClassroomBean> fetchAllClassroom() {
		List<ClassroomBean> classroomBeanlist = null;
		try {
			classroomBeanlist = classroomDao.fetchAllClassRoom();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return classroomBeanlist;
	}

	@Override
	public int addClassroom(ClassroomBean classroomBean) {
		int result = 0;
		try {
			result = classroomDao.addClassRoom(classroomBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ClassroomBean fetchClassroomByRoomnumber(String classroomnumber) {
		ClassroomBean classroomBean = null;
		try {
			classroomBean = classroomDao
					.fetchClassroomByRoomnumber(classroomnumber);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return classroomBean;
	}

	@Override
	public int updateClassroom(ClassroomBean classroomBean) {
		int result = 0;
		try {
			result = classroomDao.updateClassroom(classroomBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int deleteClassroom(String classroomnumber) {
		int rows = 0;
		try {
			rows = classroomDao.deleteClassroom(classroomnumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int startClassroom(String classroomnumber) throws Exception {
		int rows = 0;
		try {
			rows = classroomDao.startClassroom(classroomnumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int changeClassroomToBusy(String classroomnumber) throws Exception {
		int rows = 0;
		try {
			rows = classroomDao.changeClassroomToBusy(classroomnumber);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rows;
	}

	@Override
	public List<ClassroomBean> searchClassroom(String classroomnumber,
			String room) throws Exception {
		List<ClassroomBean> classroomBeanlist = null;
		try {
			classroomBeanlist = classroomDao.searchClassroom(classroomnumber,
					room);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classroomBeanlist;
	}

	@Override
	public List<ClassroomBean> fetchFreeClassroom() {
		List<ClassroomBean> classroomBeanlist = null;
		try {
			classroomBeanlist = classroomDao.fetchFreeClassRoom();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return classroomBeanlist;
	}

}
