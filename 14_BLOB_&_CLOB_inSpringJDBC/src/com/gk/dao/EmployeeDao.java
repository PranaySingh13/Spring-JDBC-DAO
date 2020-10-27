package com.gk.dao;

import com.gk.dto.Employee;

public interface EmployeeDao {

	public void insertEmployee(Employee employee);
	
	public Employee readEmployee();
}
