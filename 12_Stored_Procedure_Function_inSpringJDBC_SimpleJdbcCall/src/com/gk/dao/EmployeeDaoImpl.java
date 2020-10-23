package com.gk.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.gk.dto.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	private DataSource dataSource;
	private SimpleJdbcCall simpleJdbcCall;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		simpleJdbcCall = new SimpleJdbcCall(dataSource);
		/*
		 * Similarly stored function can also be called by jdbcCall=new
		 * SimpleJdbcCall(dataSource).withFunctionName(functionName);
		 */
	}

	@Override
	public void addEmployee(Employee employee) {
		try {
			simpleJdbcCall = simpleJdbcCall.withProcedureName("insertEmployee");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("eid", employee.getEmpId());
			map.put("ename", employee.getEmpName());
			map.put("esal", employee.getEmpSalary());
			map.put("eaddr", employee.getEmpAddress());
			simpleJdbcCall.execute(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public float getEmployeeSalary(int empId) {
		float sal = 0.0f;
		try {
			simpleJdbcCall = simpleJdbcCall.withProcedureName("getSalary");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("eid", empId);
			Map<String, Object> outValues = simpleJdbcCall.execute(map);
			sal = (Float) outValues.get("esal");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sal;
	}

	@Override
	public Employee getEmployee(int empId) {
		Employee employee = null;
		try {
			simpleJdbcCall = simpleJdbcCall.withProcedureName("getEmployee");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("no", empId);
			Map<String, Object> outValues = simpleJdbcCall.execute(map);
			int eid = (Integer) outValues.get("eid");
			String ename = (String) outValues.get("ename");
			float esal = (Float) outValues.get("esal");
			String eaddr = (String) outValues.get("eaddr");
			employee = new Employee();
			employee.setEmpId(eid);
			employee.setEmpName(ename);
			employee.setEmpSalary(esal);
			employee.setEmpAddress(eaddr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> allEmps = null;
		try {
			simpleJdbcCall = simpleJdbcCall.withProcedureName("getAllEmployees");
			Map<String, Object> outValues=simpleJdbcCall.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allEmps;
	}

	@Override
	public void updateEmployee(Employee employee) {
		try {
			simpleJdbcCall = simpleJdbcCall.withProcedureName("updateEmployee");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("eid", employee.getEmpId());
			map.put("ename", employee.getEmpName());
			map.put("esal", employee.getEmpSalary());
			map.put("eaddr", employee.getEmpAddress());
			simpleJdbcCall.execute(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEmployee(int empId) {
		try {
			simpleJdbcCall = simpleJdbcCall.withProcedureName("deleteEmployee");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("eid", empId);
			simpleJdbcCall.execute(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
