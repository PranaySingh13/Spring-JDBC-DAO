package com.gk.dao;

import java.util.List;

import com.gk.dto.Employee;

public interface EmployeeDao {

	public void addEmployee(Employee employee);
	
	public float getEmployeeSalary(int empId);
	
	public Employee getEmployee(int empId);
	
	public void updateEmployee(Employee employee);
	
	public void deleteEmployee(int empId);
	
	public List<Employee> getAllEmployees();
}
