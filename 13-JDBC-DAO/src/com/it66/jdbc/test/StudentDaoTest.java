package com.it66.jdbc.test;

import java.util.List;

import org.junit.Test;

import com.it66.jdbc.dao.impl.StudentDaoImpl;
import com.it666.jdbc.dao.IStudentDao;
import com.it666.jdbc.domain.Student;

public class StudentDaoTest {
	
	@Test
	public void sava() {
		Student stu = new Student();
		stu.setId(2);
		stu.setName("kimi");
		stu.setAge(7);
		// 保存到数据库
		IStudentDao dao = new StudentDaoImpl();
		dao.save(stu);
	}

	@Test
	public void delete() {
		IStudentDao dao = new StudentDaoImpl();
		dao.delete(1);
	}
	@Test
	public void update() {
		Student stu = new Student();
		stu.setName("kimi");
		stu.setAge(4);
		IStudentDao dao = new StudentDaoImpl();
		dao.update(3,stu);
	} 
	@Test
	public void get() {
		IStudentDao dao = new StudentDaoImpl();
		Student stu = dao.get(1);
		System.out.println(stu);
	}
	@Test
	public void getAll() {
		IStudentDao dao = new StudentDaoImpl();
		List<Student> all=dao.getAll();
		System.out.println(all);
	}
	
}
