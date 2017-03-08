package com.equipment.dao;

import java.util.List;

import com.equipment.bean.FailTypeBean;

public interface FailTypeDao {

	public List<FailTypeBean> fillAllFailType(int pageno) throws Exception;

	public List<FailTypeBean> fetchAllFailType() throws Exception;

	public int updateFailTypeById(int fail_type_id, String fail_type_name,
			String fail_type_service_fee) throws Exception;

	public FailTypeBean fetchFailTypeById(int fail_type_id) throws Exception;

	public int addFailType(String fail_type_name, String fail_type_service_fee)
			throws Exception;

	public int countFailTypeByName(String fail_type_name) throws Exception;

	public int deleteFailTypeById(int id) throws Exception;

	public int countFailType() throws Exception;
}
