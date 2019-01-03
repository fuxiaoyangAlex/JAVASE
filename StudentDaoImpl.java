package com.it66.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import com.it666.jdbc.dao.IStudentDao;
import com.it666.jdbc.domain.Student;
import com.it666.jdbc.util.JDBCUtil;
public class StudentDaoImpl implements IStudentDao {

	/*
	 * (non-Javadoc)
	 * @see com.it666.jdbc.dao.IStudentDao#save(com.it666.jdbc.domain.Student)
	 * 1.设计一个方法
	 * 2.要求传入两个参数
	 * 
	 *  一个sql语句
	 *  一个参数
	 *  
	 *  	第一个参数sql语句模板
	 *  	第二个参数为可变参数，设置语句参数值
	 *  3.返回值
	 *  返回值为int， 受影响的行数。
	 *  
	 *  可变参数一个本质，是数组
	 * 
	 */
	
	public int executeUpdate(String sql,Object...params) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//获取的是连接池里面的对象
			conn = JDBCUtil.getConn();
			//获取sql语句
			pstmt = conn.prepareStatement(sql);
			//遍历参数
			for(int i=0;i<params.length;i++) {
				pstmt.setObject(i+1,params[i]);
			}	
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt,null);
		}
		return 0;
	}
	
	@Override
	public void save(Student stu) {
			String sql = "insert into stud(id,name,age)values(?,?,?)";
			this.executeUpdate(sql,stu.getId(),stu.getName(),stu.getAge());
	}
//	
//	public void save(Student stu) {
//			Connection conn =null;
//			PreparedStatement pstmt= null;
//			try {
//				conn=JDBCUtil.getConn();
//				String sql = "insert into stud(id,name,age)values(?,?,?)";
//				pstmt =conn.prepareStatement(sql);
//				pstmt.setInt(1,stu.getId());
//				pstmt.setString(2,stu.getName());
//				pstmt.setInt(3,stu.getAge());
//				
//				pstmt.executeUpdate();
//			}catch (Exception e) {
//				e.printStackTrace();
//			}finally {
//				JDBCUtil.close(conn, pstmt, null);
//			}
//	}
	@Override
	public void delete(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtil.getConn();
			// 3.创建语句
			String sql="delete from stud where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			// 4.执行语句
			pstmt.executeUpdate();
			System.out.println(pstmt.toString());
			
		} catch (Exception e) { 
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, null);
		}
	}

	@Override
	public void update(int id, Student stu) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtil.getConn();
			// 3.创建语句
			String sql ="update stud set name=?,age=? where id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,stu.getName());
			pstmt.setInt(2,stu.getAge());
			pstmt.setInt(3,id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, null);
		}
	}

	@Override
	// 查询指定学生
	public Student get(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConn();
			// 3.创建语句	
			String sql="select * from stud where id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			// 4.执行语句
			rs = pstmt.executeQuery();
			while (rs.next()) { 
				Student student = new Student();
				student.setId(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setAge(rs.getInt(3));
				return student;
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return null;
	}

	@Override
	public List<Student> getAll() {


		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCUtil.getConn(); // 3.创建语句
		
			String sql = "select * from stud";
			pstmt=conn.prepareStatement(sql);
			System.out.println(sql);
			// 4.执行语句
			rs = pstmt.executeQuery(sql);
			// 创建集合
			List<Student> list = new ArrayList<Student>();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setAge(rs.getInt(3));
				list.add(student);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return null;
	}
}
