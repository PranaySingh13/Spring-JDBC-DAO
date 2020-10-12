package com.gk.dao;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.gk.dto.Employee;

public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {

	String query = "";
	String status = "";

	@Override
	public String add(Employee employee) {
		try {
			Employee emp = search(employee.getEmpId());
			if (emp == null) {
				query = "insert into EMPLOYEE values(?,?,?,?)";
				int rowCount = getJdbcTemplate().update(query, new Object[] { employee.getEmpId(),
						employee.getEmpName(), employee.getEmpSalary(), employee.getEmpAddress() });
				if (rowCount == 1) {
					status = "Employee Inserted Successfully";
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
			query = "select * from EMPLOYEE where EMPID=?";
			List<Employee> empList = getJdbcTemplate().query(query, new Object[] { empId }, (rs, rowCount) -> {

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
	public List<Employee> getAllEmployees() {
		List<Employee> empsList=null;
		try {
			empsList=getJdbcTemplate().query("select * from EMPLOYEE", (rs,rowCount)->{
				Employee emp=new Employee();
				emp.setEmpId(rs.getInt("EMPID"));
				emp.setEmpName(rs.getString("EMPNAME"));
				emp.setEmpSalary(rs.getFloat("EMPSALARY"));
				emp.setEmpAddress(rs.getString("EMPADDRESS"));
				return emp;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empsList;
	}

	@Override
	public String update(Employee employee) {
		try {
			query = "update EMPLOYEE set EMPSALARY = ?, EMPADDRESS = ? where EMPID = ?";
			int rowCount = getJdbcTemplate().update(query,
					new Object[] { employee.getEmpSalary(), employee.getEmpAddress(), employee.getEmpId() });
			if(rowCount==1) {
				status="Employee Updated Successfully";
			}else {
				status="Employee Updation Failed";
			}
		} catch (Exception e) {
			status="Employee Updation Failed";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String delete(int empId) {
		try {
			Employee emp = search(empId);
			if (emp == null) {
				status = "Employee Not Existed";
			} else {
				query="delete from EMPLOYEE where EMPID =?";
				int rowCount = getJdbcTemplate().update(query, new Object[] { empId });
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
