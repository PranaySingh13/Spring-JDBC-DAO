package com.gk.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.gk.dto.Employee;

public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {

	@Override
	public int[] insert(List<Employee> empList) {
		int rowCounts[] = null;
		try {
			String query = "insert into EMPLOYEE values(?,?,?,?)";
			rowCounts = getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {
				/*
				 * Spring Container calls this setValues method n times for n times insertion at
				 * proper index location
				 */
				@Override
				public void setValues(PreparedStatement pst, int index) throws SQLException {
					pst.setInt(1, empList.get(index).getEmpId());
					pst.setString(2, empList.get(index).getEmpName());
					pst.setFloat(3, empList.get(index).getEmpSalary());
					pst.setString(4, empList.get(index).getEmpAddress());
				}

				@Override
				public int getBatchSize() {
					return empList.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCounts;
	}

	@Override
	public int[] update() {
		String status = "";
		int rowCounts[] = null;
		try {
			String query1 = "insert into EMPLOYEE values(5,'R Ashwin',600000,'Chennai')";
			String query2 = "update EMPLOYEE set EMPSALARY=EMPSALARY+30000 where EMPSALARY<1000000";
			String query3 = "delete from EMPLOYEE where EMPADDRESS='Mumbai'";
			/*
			 * Spring Container calls this batchUpdate method n times for n times calling
			 * queries.
			 */
			rowCounts = getJdbcTemplate().batchUpdate(query1, query2, query3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCounts;
	}

}
