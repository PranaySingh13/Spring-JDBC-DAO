package com.gk.service;

import com.gk.dao.StudentDao;
import com.gk.dto.Student;
import com.gk.factory.StudentDaoFactory;

public class StudentServiceImpl implements StudentService {

	String status = "";

	@Override
	public String addStudent(Student student) {
		StudentDao studentDao = StudentDaoFactory.getStudentDao();
		status = studentDao.add(student);
		return status;
	}

	@Override
	public Student searchStudent(String sid) {
		StudentDao studentDao=StudentDaoFactory.getStudentDao();
		Student student=studentDao.search(sid);
		return student;
	}

	@Override
	public String deleteStudent(String sid) {
		StudentDao studentDao = StudentDaoFactory.getStudentDao();
		status = studentDao.delete(sid);
		return status;
	}

}
