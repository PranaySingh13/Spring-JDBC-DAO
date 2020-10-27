package com.gk.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.util.FileCopyUtils;

import com.gk.dto.Employee;

public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {

	private LobHandler lobHolder;

	public void setLobHolder(LobHandler lobHolder) {
		this.lobHolder = lobHolder;
	}

	public LobHandler getLobHolder() {
		return lobHolder;
	}

	@Override
	public void insertEmployee(Employee employee) {

		String query = "insert into EMPLOYEE3 values(?,?,?,?)";
		getJdbcTemplate().execute(query, new AbstractLobCreatingPreparedStatementCallback(lobHolder) {

			@Override
			protected void setValues(PreparedStatement pst, LobCreator lobCreator)
					throws SQLException, DataAccessException {
				try {
					pst.setInt(1, employee.getEmpId());
					pst.setString(2, employee.getEmpName());
					FileInputStream fis = new FileInputStream(employee.getEmp_Image());
					FileReader fr = new FileReader(employee.getEmp_Resume());
					// storing blob data at 3rd column index
					lobCreator.setBlobAsBinaryStream(pst, 3, fis, (int) employee.getEmp_Image().length());
					// storing clob data at 4th column index
					lobCreator.setClobAsCharacterStream(pst, 4, fr, (int) employee.getEmp_Resume().length());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public Employee readEmployee() {
		Employee employee = new Employee();
		String query = "select * from EMPLOYEE3";
		getJdbcTemplate().query(query, new AbstractLobStreamingResultSetExtractor<Object>() {

			@Override
			protected void streamData(ResultSet rs) throws SQLException, IOException, DataAccessException {
				employee.setEmpId(rs.getInt(1));
				employee.setEmpName(rs.getString(2));

				/*
				 * We Can take output in any resp. convertible document.
				 *  Ex. For Image jpg to jpeg,gif etc. 
				 *  For Document txt to docx,pdf etc.
				 */
				File file1 = new File("F:/my_image.gif");
				FileOutputStream fos = new FileOutputStream(file1);
				// retrieve blob data from 3rd column index
				FileCopyUtils.copy(lobHolder.getBlobAsBinaryStream(rs, 3), fos);
				employee.setEmp_Image(file1);

				File file2 = new File("F:/my_resume.pdf");
				FileWriter fw = new FileWriter(file2);
				// retrieve clob data from 4th column index
				FileCopyUtils.copy(lobHolder.getClobAsCharacterStream(rs, 4), fw);
				employee.setEmp_Resume(file2);
			}

		});
		return employee;
	}

}
