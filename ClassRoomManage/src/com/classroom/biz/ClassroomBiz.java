package com.classroom.biz;

import java.util.List;

import com.classroom.bean.ClassroomBean;

public interface ClassroomBiz {

	public List<ClassroomBean> fetchAllClassroom();

	public int addClassroom(ClassroomBean classroomBean);

	public ClassroomBean fetchClassroomByRoomnumber(String classroomnumber);

	public int updateClassroom(ClassroomBean classroomBean);

	public int deleteClassroom(String classroomnumber);

	public int startClassroom(String classroomnumber) throws Exception;

	public int changeClassroomToBusy(String classroomnumber) throws Exception;
	
	public List<ClassroomBean> searchClassroom(String classroomnumber, String room)
			throws Exception;
	public List<ClassroomBean> fetchFreeClassroom();
}
