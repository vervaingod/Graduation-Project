package com.classroom.biz;

import java.util.List;

import com.classroom.bean.PeopleBean;
import com.classroom.dao.PeopleDao;
import com.classroom.dao.PeopleDaoImpl;

public class PeopleBizImpl implements PeopleBiz {

	PeopleDao peopleDao = new PeopleDaoImpl();

	@Override
	public PeopleBean login(PeopleBean peopleBean) {
		PeopleBean peopleBean2 = null;
		try {
			peopleBean2 = peopleDao.login(peopleBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return peopleBean2;
	}

	@Override
	public List<PeopleBean> fetchAllStudentList() {
		List<PeopleBean> peopleBeanList = null;
		try {
			peopleBeanList = peopleDao.fetchAllStudentList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return peopleBeanList;
	}

	@Override
	public int registerManager(PeopleBean peopleBean) {
		int rows = 0;
		try {
			rows = peopleDao.registermanager(peopleBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rows;
	}

	@Override
	public int fetchUsername(String username) {
		// TODO Auto-generated method stub
		int rows = 0;
		try {
			rows = peopleDao.fetchUsername(username);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int fetchName(String name) {
		// TODO Auto-generated method stub
		int rows = 0;
		try {
			rows = peopleDao.fetchName(name);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int StartStudent(String username) throws Exception {
		int rows = 0;
		try {
			rows = peopleDao.StartStudent(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int StopStudent(String username) throws Exception {
		int rows = 0;
		try {
			rows = peopleDao.StopStudent(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public List<PeopleBean> fetchAllManageList() {
		List<PeopleBean> peopleBeanList = null;
		try {
			peopleBeanList = peopleDao.fetchAllmanageList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return peopleBeanList;
	}

	@Override
	public List<PeopleBean> searchStudentList(String name, String username,
			int state) throws Exception {
		List<PeopleBean> peopleBeanList = null;
		try {
			peopleBeanList = peopleDao.searchStudentList(name, username, state);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return peopleBeanList;
	}

	@Override
	public int registerStudent(PeopleBean peopleBean) {
		int rows = 0;
		try {
			rows = peopleDao.registerStudent(peopleBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rows;
	}

}
