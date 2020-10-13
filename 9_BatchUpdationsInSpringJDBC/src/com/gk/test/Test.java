package com.gk.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gk.dao.EmployeeDao;
import com.gk.dto.Employee;

public class Test {

	public static void main(String[] args) throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext("/com/gk/resources/applicationContext.xml");
		EmployeeDao dao = (EmployeeDao) context.getBean("employeeDao");
		List<Employee> empList = new ArrayList<Employee>();
		
		Employee employee1 = new Employee();
		employee1.setEmpId(1);
		employee1.setEmpName("Yuvraj Singh");
		employee1.setEmpSalary(100000.0f);
		employee1.setEmpAddress("Mohali");
		empList.add(employee1);
		
		Employee employee2 = new Employee();
		employee2.setEmpId(2);
		employee2.setEmpName("Ms Dhoni");
		employee2.setEmpSalary(200000.0f);
		employee2.setEmpAddress("Ranchi");
		empList.add(employee2);
		
		Employee employee3 = new Employee();
		employee3.setEmpId(3);
		employee3.setEmpName("Virat Kohli");
		employee3.setEmpSalary(300000.0f);
		employee3.setEmpAddress("Delhi");
		empList.add(employee3);
		
		Employee employee4 = new Employee();
		employee4.setEmpId(4);
		employee4.setEmpName("Rohit Sharma");
		employee4.setEmpSalary(400000.0f);
		employee4.setEmpAddress("Mumbai");
		empList.add(employee4);

		int rowCounts1[] = dao.insert(empList);
		for (int i = 0; i < rowCounts1.length; i++) {
			System.out.println(rowCounts1[i]);
		}
		
		System.out.println("----------");
		int rowCounts2[] = dao.update();
		for (int i = 0; i < rowCounts2.length; i++) {
			System.out.println(rowCounts2[i]);
		}
		
	}

}
