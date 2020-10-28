package com.gk.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gk.dao.EmployeeDao;
import com.gk.dto.Employee;

public class Test {

	public static void main(String[] args) throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext("/com/gk/resources/applicationContext.xml");
		EmployeeDao dao = (EmployeeDao) context.getBean("employeeDao");

		Employee employee = new Employee();
		employee.setEmpId(1);
		employee.setEmpName("abc");
		employee.setEmpSalary(1000f);
		employee.setEmpAddress("xyz");
		dao.addEmployee(employee);
		System.out.println("Employee Inserted Successfully");
	}
}
