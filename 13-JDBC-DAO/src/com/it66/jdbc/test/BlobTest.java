package com.it66.jdbc.test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement; 

import com.it666.jdbc.util.JDBCUtil;

public class BlobTest {
	public static void main(String[] args)throws Exception {
		Connection conn=JDBCUtil.getConnection();
		String sql = "insert into stud (img) values(?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);	
		FileInputStream in = new FileInputStream("e:/red.png");
		pstmt.setBlob(1,in);
		pstmt.executeUpdate();
		JDBCUtil.close(conn, pstmt, null);
	}
}
