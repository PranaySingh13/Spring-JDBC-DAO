package com.gk.factory;

import com.gk.service.StudentService;
import com.gk.service.StudentServiceImpl;

public class StudentServiceFactory {

	private static StudentService studentService;
	static {
		studentService=new StudentServiceImpl();
	}
	public static StudentService getStudentService() {
		return studentService;
	}
	
}
