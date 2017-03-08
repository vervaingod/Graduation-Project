package com.classroom.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 
 * 
 * @功能 连接数据库 回收资源
 */
public class DBUtil {

	public Connection getConnection() throws Exception {
		Connection connection = null;
		String className = "com.mysql.jdbc.Driver";
		Class.forName(className).newInstance();
		String url = "jdbc:mysql://127.0.0.1:3306/classroommanage";
		String user = "root";
		String password = "root";
		connection = DriverManager.getConnection(url, user, password);
		return connection;
	}

	public void closeDBResource(Connection connection,
			PreparedStatement preparedStatement, ResultSet resultSet)
			throws Exception {
		if (connection != null) {
			connection.close();
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (resultSet != null) {
			resultSet.close();
		}
	}

}
