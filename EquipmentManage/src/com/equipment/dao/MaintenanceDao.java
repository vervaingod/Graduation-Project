package com.equipment.dao;

import java.util.List;

import com.equipment.bean.MaintenanceBean;

public interface MaintenanceDao {

	public int addMaintenance(MaintenanceBean maintenanceBean) throws Exception;

	public List<MaintenanceBean> fetchAllMaintenance(int pageno,
			int maintenance_id, String maintenance_department,
			String maintenance_fail_type, int maintenance_dispatch_state,
			String maintenance_time, int maintenance_accept_state,
			int maintenance_confirm, String maintenance_unit) throws Exception;

	public List<MaintenanceBean> fetchAllMaintenanceByUnit(
			String maintenance_unit, int pageno, int maintenance_id,
			String maintenance_department, String maintenance_fail_type,
			int maintenance_dispatch_state, String maintenance_time,
			int maintenance_accept_state, int maintenance_confirm)
			throws Exception;

	public List<MaintenanceBean> fetchAllMaintenanceByDepartment(
			String maintenance_department) throws Exception;

	public List<MaintenanceBean> fetchAllConfirmByDepartment(
			String maintenance_department, int pageno, int maintenance_id,
			String maintenance_unit, String maintenance_fail_type,
			String maintenance_time, int maintenance_confirm) throws Exception;

	public MaintenanceBean findMaintenanceById(int maintenance_id)
			throws Exception;

	public int dispatch(int maintenance_id) throws Exception;

	public int fetchDispatchState(int maintenance_id) throws Exception;

	public int updateMaintenanceByAccept(int maintenance_id,
			String maintenance_equipment, String maintenance_IP,
			String maintenance_MAC, int maintenance_record,
			int maintenance_sum_cost, String maintenance_completion)
			throws Exception;

	public int confirmMaintenanceByMaintenanceID(int maintenance_id)
			throws Exception;

	public int fetchConfirmStateByMaintenanceID(int maintenance_id)
			throws Exception;

	public int fetchAcceptStateByMaintenanceID(int maintenance_id)
			throws Exception;

	public int countAllMaintenance(int maintenance_id,
			String maintenance_department, String maintenance_fail_type,
			int maintenance_dispatch_state, String maintenance_time,
			int maintenance_accept_state, int maintenance_confirm,
			String maintenance_unit) throws Exception;

	public int countAllMaintenanceByUnit(String maintenance_unit,
			int maintenance_id, String maintenance_department,
			String maintenance_fail_type, int maintenance_dispatch_state,
			String maintenance_time, int maintenance_accept_state,
			int maintenance_confirm) throws Exception;

	public int countAllConfirmByDepartment(String maintenance_department,
			int maintenance_id, String maintenance_unit,
			String maintenance_fail_type, String maintenance_time,
			int maintenance_confirm) throws Exception;

	public int invalidDispatchByMaintenanceID(int maintenance_id)
			throws Exception;

}
