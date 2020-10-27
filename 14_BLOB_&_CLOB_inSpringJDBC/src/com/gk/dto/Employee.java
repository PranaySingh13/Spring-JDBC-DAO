package com.gk.dto;

import java.io.File;

public class Employee {

	private int empId;
	private String empName;
	//BLOB Data
	private File emp_Image;
	//CLOB Data
	private File emp_Resume;
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public File getEmp_Image() {
		return emp_Image;
	}
	public void setEmp_Image(File emp_Image) {
		this.emp_Image = emp_Image;
	}
	public File getEmp_Resume() {
		return emp_Resume;
	}
	public void setEmp_Resume(File emp_Resume) {
		this.emp_Resume = emp_Resume;
	}
	
}
