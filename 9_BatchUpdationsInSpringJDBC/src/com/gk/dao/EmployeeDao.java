package com.gk.dao;

import java.util.List;

import com.gk.dto.Employee;

public interface EmployeeDao {

	public int[] insert(List<Employee> empList);

	public int[] update();
}
