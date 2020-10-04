package com.gk.factory;

import com.gk.dao.StudentDao;
import com.gk.dao.StudentDaoImpl;

public class StudentDaoFactory {

	private static StudentDao studentDao;

	static {
		studentDao = new StudentDaoImpl();
	}

	public static StudentDao getStudentDao() {
		return studentDao;
	}

}
