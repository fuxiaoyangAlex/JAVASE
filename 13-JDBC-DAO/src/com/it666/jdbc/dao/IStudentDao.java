package com.it666.jdbc.dao;

import java.util.List;

import com.it666.jdbc.domain.Student;

public interface IStudentDao {
	//1.保存
	void save(Student stu);
	//2.修改指定学生信息
	void update(int id,Student stu);
	//3.删除学生
	void delete(int id);
	//4.获取指定学生
	Student get(int id);
	//5.获取所有学生
	List<Student>getAll();
}
