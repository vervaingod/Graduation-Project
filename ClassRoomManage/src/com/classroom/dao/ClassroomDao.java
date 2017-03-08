package com.classroom.dao;

import java.util.List;

import com.classroom.bean.ClassroomBean;

public interface ClassroomDao {

	public List<ClassroomBean> fetchAllClassRoom() throws Exception;

	public int addClassRoom(ClassroomBean classroomBean) throws Exception;

	public ClassroomBean fetchClassroomByRoomnumber(String classroomnumber)
			throws Exception;

	public int updateClassroom(ClassroomBean classroomBean) throws Exception;

	public int deleteClassroom(String classroomnumber) throws Exception;

	public int startClassroom(String classroomnumber) throws Exception;

	public int changeClassroomToBusy(String classroomnumber) throws Exception;

	public List<ClassroomBean> searchClassroom(String classroomnumber,
			String room) throws Exception;

	public List<ClassroomBean> fetchFreeClassRoom() throws Exception;
}
