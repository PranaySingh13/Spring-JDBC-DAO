package com.gk.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.gk.dto.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	private String status = "";
	private String query = "";
	private SimpleJdbcTemplate simpleJdbcTemplate;

	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}

	@Override
	public String add(Employee employee) {
		try {
			Employee emp = search(employee.getEmpId());
			if (emp == null) {
				query = "insert into EMPLOYEE values(?,?,?,?)";
				int rowCount = simpleJdbcTemplate.getJdbcOperations().update(query, new Object[] { employee.getEmpId(),
						employee.getEmpName(), employee.getEmpSalary(), employee.getEmpAddress() });
				if (rowCount == 1) {
					status = "Employee Insertion Successfully";
				} else {
					status = "Employee Insertion Failed";
				}

			} else {
				status = "Employee Existed Already";
			}
		} catch (Exception e) {
			status = "Employee Insertion Failed";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Employee search(int empId) {
		Employee employee = null;
		try {
			query = "select * from EMPLOYEE where EMPID = ?";
			/*
			 * In case of SimpleJdbcTemplate class, to perform retrieval operations, we have
			 * to use "ParameterizedRowMapper" in place of RowMapper interface.
			 */
			List<Employee> empList = simpleJdbcTemplate.query(query, (rs, rowCount) -> {
				Employee emp = new Employee();
				emp.setEmpId(rs.getInt("EMPID"));
				emp.setEmpName(rs.getString("EMPNAME"));
				emp.setEmpSalary(rs.getFloat("EMPSALARY"));
				emp.setEmpAddress(rs.getString("EMPADDRESS"));
				return emp;
			}, new Object[] { empId });

			boolean b = empList.isEmpty();
			if (b == true) {
				employee = null;
			} else {
				employee = empList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employee;
	}

	@Override
	public String update(Employee employee) {
		try {
			query = "update EMPLOYEE set EMPSALARY = ?,EMPADDRESS = ? where EMPID=?";
			int rowCount = simpleJdbcTemplate.getJdbcOperations().update(query,
					new Object[] { employee.getEmpSalary(), employee.getEmpAddress(), employee.getEmpId() });
			if (rowCount == 1) {
				status = "Employee Updated Successfully";
			} else {
				status = "Employee Updation Failed";
			}
		} catch (Exception e) {
			status = "Employee Updation Failed";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String delete(int empId) {
		Employee employee = null;
		try {
			Employee emp = search(empId);
			if (emp == null) {
				status = "Employee Not Existed";
			} else {
				query = "delete from EMPLOYEE where EMPID = ?";
				int rowCount = simpleJdbcTemplate.getJdbcOperations().update(query, new Object[] { empId });
				if (rowCount == 1) {
					status = "Employee Deleted Successfully";
				} else {
					status = "Employee Deletion Failed";
				}
			}
		} catch (Exception e) {
			status = "Employee Deletion Failed";
			e.printStackTrace();
		}
		return status;
	}

}
