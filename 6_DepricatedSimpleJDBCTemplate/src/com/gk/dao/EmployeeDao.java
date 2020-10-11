package com.gk.dao;

import com.gk.dto.Employee;

public interface EmployeeDao {

	public String add(Employee employee);

	public Employee search(int empId);

	public String update(Employee employee);

	public String delete(int empId);

}
