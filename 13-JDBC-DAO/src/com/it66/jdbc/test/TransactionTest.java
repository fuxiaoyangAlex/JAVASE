package com.it66.jdbc.test;

import com.it666.jdbc.util.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionTest {
	public static void main(String[] args) throws SQLException {
		/*
		 * 1.检查zs账户余额
		 * 2.减少zs账户1000
		 * 3.增加ls账户1000
		 */
		Connection conn = JDBCUtil.getConnection();
		String sql="select *from account where name=? and money>?";
		//创建语句
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"zs");
		pstmt.setInt(2,1000);
		ResultSet rSet =pstmt.executeQuery();
		if(!rSet.next()) {
			throw new RuntimeException("没钱了");
		}
		//2减少zs账户1000
		try {
			//开启事务
			conn.setAutoCommit(false);
			sql ="update account set money = money-? where name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1,1000);
			pstmt.setString(2,"zs");
			pstmt.executeUpdate();
			
			int i = 2/0;
			//3增加l账户1000
			sql ="update account set money = money+? where name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1,1000);
			pstmt.setString(2,"ls");
			pstmt.executeUpdate();
			pstmt.executeUpdate();
			conn.commit();
		}catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		}finally {
			JDBCUtil.close(conn, pstmt, rSet);
		}
	
	}
}
