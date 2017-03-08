package com.classroom.biz;

import java.util.List;

import com.classroom.bean.ApplyInfoBean;
import com.classroom.dao.ApplyInfoDao;
import com.classroom.dao.ApplyInfoDaoImpl;

public class ApplyInfoBizImpl implements ApplyInfoBiz {
	ApplyInfoDao applyinfoDao = new ApplyInfoDaoImpl();

	@Override
	public List<ApplyInfoBean> fetchAllApplyInfo() {
		List<ApplyInfoBean> applyInfoBeanList = null;
		try {
			applyInfoBeanList = applyinfoDao.fetchAllApplyInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return applyInfoBeanList;
	}

	@Override
	public List<ApplyInfoBean> fetchWaitingApply() {
		List<ApplyInfoBean> applyInfoBeanList = null;
		try {
			applyInfoBeanList = applyinfoDao.fetchWaitingApply();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return applyInfoBeanList;
	}

	@Override
	public List<ApplyInfoBean> fetchAppledInfo() {
		List<ApplyInfoBean> applyInfoBeanList = null;
		try {
			applyInfoBeanList = applyinfoDao.fetchAppledInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return applyInfoBeanList;
	}

	@Override
	public int passApply(int id) throws Exception {
		int rows = 0;
		try {
			rows = applyinfoDao.passApply(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int refuseApply(int id) throws Exception {
		int rows = 0;
		try {
			rows = applyinfoDao.refuseApply(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int deleteApply(int id) throws Exception {
		int rows = 0;
		try {
			rows = applyinfoDao.deleteApply(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int updateEvaluate(int id, String remark) throws Exception {
		int rows = 0;
		try {
			rows = applyinfoDao.updateEvaluate(id, remark);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rows;
	}

	@Override
	public int addApply(ApplyInfoBean applyInfoBean) throws Exception {
		int rows=0;
		try {
			rows=applyinfoDao.addApply(applyInfoBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return rows;
	}

	@Override
	public List<ApplyInfoBean> fetchApplyByUsername(String applystudent)
			throws Exception {
		List<ApplyInfoBean> applyInfoBeanList = null;
		try {
			applyInfoBeanList = applyinfoDao.fetchApplyByUsername(applystudent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return applyInfoBeanList;
	}

}
