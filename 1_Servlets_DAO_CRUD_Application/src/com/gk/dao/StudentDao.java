package com.gk.dao;

import com.gk.dto.Student;

public interface StudentDao {

	public String add(Student student);

	public Student search(String sid);

	public String delete(String sid);
}
