package com.it666.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	public static String url = "jdbc:mysql://localhost:3306/jdbc_db?rewriteBatchedStatements=true";
	public static String user = "root";
	public static String password = "123456";
	public static String Driver = "com.mysql.jdbc.Driver";

	static {
		try {
			// 1.加载驱动
			Class.forName(JDBCUtil.Driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			// 2.链接数据库
			return DriverManager.getConnection(JDBCUtil.url, JDBCUtil.user, JDBCUtil.password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void close(Connection conn,Statement stmt, ResultSet rs) {
		// 5.释放资源
					if (rs != null) {
						try {
							rs.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (stmt != null) {
						try {
							stmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
}}