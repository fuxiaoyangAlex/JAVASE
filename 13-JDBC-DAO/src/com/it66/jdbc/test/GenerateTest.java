package com.it66.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.it666.jdbc.util.JDBCUtil;
import com.mysql.jdbc.Statement;

public class GenerateTest {
	public static void main(String[] args) throws SQLException {
		Connection conn =  JDBCUtil.getConnection();
		String sql="insert into stud(name,age)values(?,?)";
		//		创建语句时，随时可以获取主键
		PreparedStatement pstmt=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,"fxy");
			pstmt.setInt(2, 20);
			pstmt.executeUpdate();
		//获取自动生成的id
			ResultSet rs=pstmt.getGeneratedKeys();
				if(rs.next()) {
					int id=rs.getInt(1);
					System.out.println(id);
				}
				JDBCUtil.close(conn, pstmt, null);
			}
	}

 