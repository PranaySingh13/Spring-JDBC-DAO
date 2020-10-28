package com.gk.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.gk.dto.Employee;

public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {

	@Override
	public void addEmployee(Employee employee) {
		try {
			getJdbcTemplate()
					.execute("insert into EMPLOYEE values('" + employee.getEmpId() + "','" + employee.getEmpName()
							+ "','" + employee.getEmpSalary() + "','" + employee.getEmpAddress() + "')");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
