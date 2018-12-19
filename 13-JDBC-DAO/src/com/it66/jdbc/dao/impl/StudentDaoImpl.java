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

	@Override
	public void save(Student stu) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = JDBCUtil.getConnection();
			// 3.创建语句
			String sql = "insert into stud(id,name,age)values(?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,stu.getId());
			pstmt.setString(2,stu.getName());
			pstmt.setInt(3,stu.getAge());
			// 4.执行语句
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, null);
		}
	}

	@Override
	public void delete(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtil.getConnection();
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
			conn = JDBCUtil.getConnection();
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
			conn = JDBCUtil.getConnection();
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

			conn = JDBCUtil.getConnection(); // 3.创建语句
		
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
