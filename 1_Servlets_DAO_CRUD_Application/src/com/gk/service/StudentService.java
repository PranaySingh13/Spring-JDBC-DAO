package com.gk.service;

import com.gk.dto.Student;

public interface StudentService {

	public String addStudent(Student student);

	public Student searchStudent(String sid);

	public String deleteStudent(String sid);
}
