package org.lanqiao.dao;
//模型层 用于处理登陆（查询数据库）

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.lanqiao.entity.Login;


public class LoginDao {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/student";
	private static final String USER = "root";
	private static final String PWD = "123456";
	
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	
	public static int login(Login login){ //登陆
		//boolean flag = false; //登陆成功与否的标识
		//int flag = -1;//-1：说明系统异常 ， 0：；用户名或密码有误 1:登陆成功
		int result=-1;
		System.out.println(login.getName());
		try {
		//第一步，加载驱动
		Class.forName(DRIVER);
		//连接数据库
		conn = DriverManager.getConnection(URL, USER, PWD);
		String sql ="SELECT COUNT(*) FROM login where name = ? AND upwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,login.getName());
			pstmt.setString(2,login.getUpwd());
			
			rs = pstmt.executeQuery(); 
			while(rs.next()) {
			 result = rs.getInt(1);
			}
			if(result>0) {
				return 1; //登陆成功
			}else {
				return 0; // 登陆失败（用户名或密码有误！）
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1; //登陆失败（系统异常！）
			
		}catch (SQLException e) {
			e.printStackTrace();
			return -1;
			
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
