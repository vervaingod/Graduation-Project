package com.equipment.dao;

import java.util.List;

import com.equipment.bean.DispatchBean;

public interface DispatchDao {

	public int addDispatch(int dispatch_user_id, int maintenance_id,
			String dispatch_maintenance_phone, String dispatch_expect_time)
			throws Exception;

	public List<DispatchBean> fetchAllDispatchByUserId(int dispatch_user_id,
			int pageno) throws Exception;

	public DispatchBean fetchDispatchByMaintenanceId(int dispatch_maintenance_id)
			throws Exception;

	public int countAllDispatchByUserId(int dispatch_user_id,
			int maintenance_id, String maintenance_department,
			String maintenance_fail_type, String maintenance_time,
			int maintenance_accept_state, int maintenance_confirm)
			throws Exception;
}
