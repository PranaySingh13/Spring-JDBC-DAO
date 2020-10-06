package com.gk.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gk.dto.Employee;

public interface EmployeeDao {

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public String add(Employee employee);

	public Employee search(int empId);

	public String update(Employee employee);

	public String delete(int empId);
}
