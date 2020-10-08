package com.gk.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gk.dto.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	private JdbcTemplate jdbcTemplate;
	String status = "";

	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public String add(Employee employee) {

		try {
			Employee emp = search(employee.getEmpId());
			if (emp == null) {
				int rowCount = jdbcTemplate.update("insert into EMPLOYEE values(?,?,?,?)",
						new Object[] { employee.getEmpId(), employee.getEmpName(), employee.getEmpSalary(),
								employee.getEmpAddress() });
				if (rowCount == 1) {
					status = "Employee Insertion Successfully!";
				} else {
					status = "Employee Insertion Failed!";
				}
			} else {
				status = "Employee Existed Already";
			}
		} catch (Exception e) {
			status = "Employee Insertion Failed!";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Employee search(int empId) {
		Employee employee = null;
		try {
			/*
			 * In Java 8 we can give RowMapper Interface(Functional Interface which consist
			 * of only one method) as from lambda expression as lambda expressions may only
			 * be used to implement functional interfaces, ie. interfaces containing only a
			 * single abstract method. While performing retrieval operations to convert data
			 * from ResultSet object[records] to Bean objects Spring Framework has provided
			 * a predefined interface.
			 * 
			 * ResultSet are not Serializable, RowMapper are Serializable
			 * 
			 */
			List<Employee> empList = jdbcTemplate.query("select * from EMPLOYEE where EMPID = ?",
					new Object[] { empId }, (rs, rowCount) -> {
						Employee emp = new Employee();
						emp.setEmpId(rs.getInt("EMPID"));
						emp.setEmpName(rs.getString("EMPNAME"));
						emp.setEmpSalary(rs.getFloat("EMPSALARY"));
						emp.setEmpAddress(rs.getString("EMPADDRESS"));
						return emp;
					});
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
		String status = "";
		try {
			int rowCount = jdbcTemplate.update("update EMPLOYEE set EMPSALARY = ?,EMPADDRESS = ? where EMPID=?",
					new Object[] { employee.getEmpSalary(), employee.getEmpAddress(), employee.getEmpId() });
			if (rowCount == 1) {
				status = "Employee Successfully Updated";
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
		String status = "";
		try {
			Employee emp = search(empId);
			if (emp == null) {
				status = "Employee Not Existed";
			} else {
				int rowCount = jdbcTemplate.update("delete from EMPLOYEE where EMPID =?", new Object[] { empId });
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
