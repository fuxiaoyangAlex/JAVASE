package com.it666.jdbc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.junit.Test;

public class LoginTest {
	
	@Test
	public void test() throws SQLException {
		//针对Statement,字符串的拼接。
		/*System.out.println(login("fxy","123"));*/ 
		System.out.println(login("' OR 1=1 OR' ","123"));
	}
												//往上抛出异常，所以test()也有异常
		String login(String name,String pwd) throws SQLException {
		//链接数据库
		Connection conn = JDBCUtil.getConnection();
		//查看数据库中是不是有传来的数据，密码也正确
		String sql="select *from user where name=? and pwd =?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.setString(2,pwd);
			System.out.println(pstmt.toString());
		   ResultSet rs =pstmt.executeQuery();
			if(rs.next()) {
				JDBCUtil.close(conn, pstmt, rs);
				return "登陆成功";
			}else {
				JDBCUtil.close(conn, pstmt, rs);
				return "登陆失败";		//此时已经返回，所以关闭放在下面不能执行。
			}
	}
}
