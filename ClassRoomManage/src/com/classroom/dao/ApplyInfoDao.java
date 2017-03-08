package com.classroom.dao;

import java.util.List;

import com.classroom.bean.ApplyInfoBean;

public interface ApplyInfoDao {

	public List<ApplyInfoBean> fetchAllApplyInfo() throws Exception;

	public List<ApplyInfoBean> fetchWaitingApply() throws Exception;

	public List<ApplyInfoBean> fetchAppledInfo() throws Exception;

	public List<ApplyInfoBean> fetchApplyById(int id) throws Exception;
	
	public List<ApplyInfoBean> fetchApplyByUsername(String applystudent) throws Exception;

	public int passApply(int id) throws Exception;

	public int refuseApply(int id) throws Exception;

	public int deleteApply(int id) throws Exception;

	public int updateEvaluate(int id, String remark) throws Exception;

	public int addApply(ApplyInfoBean applyInfoBean) throws Exception;
}
