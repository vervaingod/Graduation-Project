package com.classroom.biz;

import java.util.List;

import com.classroom.bean.ApplyInfoBean;

public interface ApplyInfoBiz {

	public List<ApplyInfoBean> fetchAllApplyInfo();

	public List<ApplyInfoBean> fetchWaitingApply();

	public List<ApplyInfoBean> fetchAppledInfo();
	
	public List<ApplyInfoBean> fetchApplyByUsername(String applystudent) throws Exception;

	public int passApply(int id) throws Exception;

	public int refuseApply(int id) throws Exception;

	public int deleteApply(int id) throws Exception;

	public int updateEvaluate(int id, String remark) throws Exception;

	public int addApply(ApplyInfoBean applyInfoBean) throws Exception;
}
