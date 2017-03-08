package com.equipment.dao;

import java.util.List;

import com.equipment.bean.CostBean;

public interface CostDao {
	public int addCost(String cost_project, String cost_service,
			String cost_material, int cost_maintenance_id) throws Exception;

	public List<CostBean> fetchAllCostByMaintenanceID(int cost_maintenance_id)
			throws Exception;

	public int countSumCost(int cost_maintenance_id) throws Exception;

	public int deleteCostByID(int cost_id) throws Exception;
}
