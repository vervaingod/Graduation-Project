package com.equipment.dao;

import com.equipment.bean.PermissionBean;

public interface PermissionDao {
	public PermissionBean findPermissionByRole(int role) throws Exception;
}
