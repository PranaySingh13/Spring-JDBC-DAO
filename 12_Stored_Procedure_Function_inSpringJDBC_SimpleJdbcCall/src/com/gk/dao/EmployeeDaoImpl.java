package com.gk.dao;

/*----------------
DROP procedure IF EXISTS `getSalary`;

DELIMITER $$
CREATE PROCEDURE getSalary(IN no int,OUT sal FLOAT(10,2))
BEGIN
select empSalary into sal from EMPLOYEE where empId=no;
END $$
DELIMITER ;
-------------------*/

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.gk.dto.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	private DataSource dataSource;
	private SimpleJdbcCall jdbcCall;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcCall=new SimpleJdbcCall(dataSource).withProcedureName("getSalary");
		/*
		 * Similarly stored function can also be called by 
		 * jdbcCall=new SimpleJdbcCall(dataSource).withFunctionName(functionName);
		 */	
	}

	@Override
	public void add(Employee employee) {
		try {
			JdbcTemplate jdbcTemp=new JdbcTemplate(dataSource);
			String sql="insert into EMPLOYEE values('"+employee.getEmpId()+"','"+employee.getEmpName()+"','"+employee.getEmpSalary()+"','"+employee.getEmpAddress()+"')";
			jdbcTemp.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getEmployeeSalary(int empId) {
		SqlParameterSource in=new MapSqlParameterSource().addValue("no", empId);
		Map<String, Object> map=jdbcCall.execute(in);
		return map.get("sal");
	}

}
