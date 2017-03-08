package com.equipment.dao;

import java.util.List;

import com.equipment.bean.LogBean;

public interface LogDao {

	public int addLog(LogBean logBean) throws Exception;

	public List<LogBean> fetchAllLogByMaintenanceId(int log_maintenance_id)
			throws Exception;
}
