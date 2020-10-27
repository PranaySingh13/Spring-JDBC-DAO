package com.gk.test;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gk.dao.EmployeeDao;
import com.gk.dto.Employee;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/gk/resources/applicationContext.xml");
		EmployeeDao dao = (EmployeeDao) context.getBean("empDao");
		// Image Location
		File file1 = new File("F:/aps.jpg");
		// Document Location
		File file2 = new File("F:/sample.txt");

		Employee employee1 = new Employee();
		employee1.setEmpId(1);
		employee1.setEmpName("Pranay");
		employee1.setEmp_Image(file1);
		employee1.setEmp_Resume(file2);
		dao.insertEmployee(employee1);
		System.out.println("Employee Inserted Successfully");

		Employee employee2 = dao.readEmployee();
		System.out.println("Employee Retreived Successfully");
		System.out.println("Employee Details");
		System.out.println("----------------");
		System.out.println("Employee Id: " + employee2.getEmpId());
		System.out.println("Employee Name: " + employee2.getEmpName());
		System.out.println("Employee Image: " + employee2.getEmp_Image().getAbsolutePath());
		System.out.println("Employee Resume:" + employee2.getEmp_Resume().getAbsolutePath());
	}

}
