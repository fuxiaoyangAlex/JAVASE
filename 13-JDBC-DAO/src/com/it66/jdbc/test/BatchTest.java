package com.it66.jdbc.test;

import com.it666.jdbc.util.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchTest {
	public static void main(String[] args) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql="insert into stud(name,age)values(?,?)";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		long begin =System.currentTimeMillis();
		for(int i=0;i<100;i++) {
			pstmt.setString(1,"zs");
			pstmt.setInt(2,20); 
			//添加到批处理，不再直接执行
//			pstmt.executeUpdate();
			pstmt.addBatch();
		}
		//执行批处理
		pstmt.executeBatch();
		long end = System.currentTimeMillis();
		long time = end-begin;
		System.out.println(time);
	}
}
