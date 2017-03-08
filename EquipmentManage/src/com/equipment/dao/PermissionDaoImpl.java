package com.equipment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.equipment.bean.PermissionBean;
import com.equipment.util.DBUtil;

public class PermissionDaoImpl implements PermissionDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	@Override
	public PermissionBean findPermissionByRole(int role) throws Exception {
		PermissionBean permission = new PermissionBean();
		connection=dbUtil.getConnection();
		String sql="select * from permission where permission_role=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, role);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next()){
		permission.setPermission_information(resultSet.getInt("permission_information"));
		permission.setPermission_account(resultSet.getInt("permission_account"));
		permission.setPermission_department(resultSet.getInt("permission_department"));
		permission.setPermission_fail_type(resultSet.getInt("permission_fail_type"));
		permission.setPermission_unit(resultSet.getInt("permission_unit"));
		permission.setPermission_announcement(resultSet.getInt("permission_announcement"));
		permission.setPermission_sign(resultSet.getInt("permission_sign"));
		permission.setPermission_statistical(resultSet.getInt("permission_statistical"));
		permission.setPermission_password(resultSet.getInt("permission_password"));
		permission.setPermission_declare(resultSet.getInt("permission_declare"));
		permission.setPermission_confirm(resultSet.getInt("permission_confirm"));
		permission.setPermission_accept(resultSet.getInt("permission_accept"));
		permission.setPermission_dispatch(resultSet.getInt("permission_dispatch"));
		permission.setPermission_role(resultSet.getInt("permission_role"));
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return permission;
	}
}
