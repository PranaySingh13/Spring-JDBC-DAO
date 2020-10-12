package com.gk.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.gk.dto.Employee;

public class EmployeeDaoImpl extends NamedParameterJdbcDaoSupport implements EmployeeDao {

	String query = "";
	String status = "";

	@Override
	public String add(Employee employee) {
		try {
			Employee emp = search(employee.getEmpId());
			if (emp == null) {
				query = "insert into EMPLOYEE values(:EMPID,:EMPNAME,:EMPSALARY,:EMPADDRESS)";
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("EMPID", employee.getEmpId());
				map.put("EMPNAME", employee.getEmpName());
				map.put("EMPSALARY", employee.getEmpSalary());
				map.put("EMPADDRESS", employee.getEmpAddress());
				int rowCount = getNamedParameterJdbcTemplate().update(query, map);
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
			query = "select * from EMPLOYEE where EMPID=:EMPID";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("EMPID", empId);
			List<Employee> empList = getNamedParameterJdbcTemplate().query(query, map, (rs, rowCount) -> {

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
		List<Employee> empsList = null;
		try {
			empsList = getNamedParameterJdbcTemplate().query("select * from EMPLOYEE", (rs, rowCount) -> {
				Employee emp = new Employee();
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
			query = "update EMPLOYEE set EMPSALARY = :EMPSALARY, EMPADDRESS = :EMPADDRESS where EMPID = :EMPID";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("EMPSALARY", employee.getEmpSalary());
			map.put("EMPADDRESS", employee.getEmpAddress());
			map.put("EMPID", employee.getEmpId());
			int rowCount = getNamedParameterJdbcTemplate().update(query,map);
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
		try {
			Employee emp = search(empId);
			if (emp == null) {
				status = "Employee Not Existed";
			} else {
				query = "delete from EMPLOYEE where EMPID =:EMPID";
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("EMPID", empId);
				int rowCount = getNamedParameterJdbcTemplate().update(query, map);
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
