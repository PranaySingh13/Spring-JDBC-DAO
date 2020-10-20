package com.gk.dao;

import com.gk.dto.Employee;

public interface EmployeeDao {

	public void add(Employee employee);
	
	public Object getEmployeeSalary(int empId);
}
