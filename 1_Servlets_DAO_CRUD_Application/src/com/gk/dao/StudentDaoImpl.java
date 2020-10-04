package com.gk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gk.dto.Student;
import com.gk.factory.ConnectionFactory;

public class StudentDaoImpl implements StudentDao {

	String status = "";

	@Override
	public String add(Student student) {

		try {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement pst = con.prepareStatement("select * from STUDENT where SID = ?");
			pst.setString(1, student.getSid());
			ResultSet rs = pst.executeQuery();
			boolean b = rs.next();
			if (b == true) {
				status = "existed";
			} else {
				pst = con.prepareStatement("insert into STUDENT values(?,?,?)");
				pst.setString(1, student.getSid());
				pst.setString(2, student.getSname());
				pst.setString(3, student.getSaddr());
				int rowCount = pst.executeUpdate();
				if (rowCount == 1) {
					status = "success";
				} else {
					status = "failure";
				}
			}
		} catch (Exception e) {
			status = "failure";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Student search(String sid) {
		Student student = null;
		try {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement pst = con.prepareStatement("select * from STUDENT where SID = ?");
			pst.setString(1, sid);
			ResultSet rs = pst.executeQuery();
			boolean b = rs.next();
			if (b == true) {
				student = new Student();
				student.setSid(rs.getString("sid"));
				student.setSname(rs.getString("sname"));
				student.setSaddr(rs.getString("saddr"));
			} else {
				student = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String delete(String sid) {
		try {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement pst = con.prepareStatement("select * from STUDENT where SID = ?");
			pst.setString(1, sid);
			ResultSet rs = pst.executeQuery();
			boolean b = rs.next();
			if (b == true) {
				pst = con.prepareStatement("delete from STUDENT where SID = ?");
				pst.setString(1, sid);
				int rowCount = pst.executeUpdate();
				if (rowCount == 1) {
					status = "success";
				} else {
					status = "failure";
				}
			} else {
				status = "notexisted";
			}
		} catch (Exception e) {
			status = "failure";
			e.printStackTrace();
		}
		return status;
	}

}
