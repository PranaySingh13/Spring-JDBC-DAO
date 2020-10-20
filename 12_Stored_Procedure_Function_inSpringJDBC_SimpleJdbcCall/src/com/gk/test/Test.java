package com.gk.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gk.dao.EmployeeDao;
import com.gk.dto.Employee;

public class Test {

	public static void main(String[] args) {

		ApplicationContext context=new ClassPathXmlApplicationContext("/com/gk/resources/applicationContext.xml");
		EmployeeDao dao=(EmployeeDao)context.getBean("employeeDao");
		
		Employee emp1=new Employee();
		emp1.setEmpId(4);
		emp1.setEmpName("Shikar dhawan");
		emp1.setEmpSalary(4000f);
		emp1.setEmpAddress("delhi");
		dao.add(emp1);
		Object salary1=dao.getEmployeeSalary(emp1.getEmpId());
		System.out.println(emp1.getEmpId()+" "+salary1);
		
		Employee emp2=new Employee();
		emp2.setEmpId(5);
		emp2.setEmpName("rahul dravid");
		emp2.setEmpSalary(5000f);
		emp2.setEmpAddress("indore");
		dao.add(emp2);
		Object salary2=dao.getEmployeeSalary(emp2.getEmpId());
		System.out.println(emp2.getEmpId()+" "+salary2);
		
		Employee emp3=new Employee();
		emp3.setEmpId(6);
		emp3.setEmpName("sourav ganguly");
		emp3.setEmpSalary(6000f);
		emp3.setEmpAddress("kolkata");
		dao.add(emp3);
		Object salary3=dao.getEmployeeSalary(emp3.getEmpId());
		System.out.println(emp3.getEmpId()+" "+salary3);
		
	}

}
