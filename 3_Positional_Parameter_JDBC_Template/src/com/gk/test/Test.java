package com.gk.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gk.dao.EmployeeDao;
import com.gk.dto.Employee;

public class Test {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/gk/resources/applicationContext.xml");
		EmployeeDao dao = (EmployeeDao) context.getBean("empDao");

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.println();
			System.out.println("1. Add Employee");
			System.out.println("2. Search Employee");
			System.out.println("3. Update Employee");
			System.out.println("4. Delete Employee");
			System.out.println("5. Exit");
			System.out.print("Your Option [1,2,3,4,5] : ");
			int option=Integer.parseInt(br.readLine());
			System.out.println();
			Employee employee=null;
			int empId=0;
			String empName="";
			float empSalary=0;
			String empAddress="";
			String status="";
			switch(option) {
				case 1:
					System.out.print("Enter the Employee Id: ");
					empId=Integer.parseInt(br.readLine());
					System.out.print("Enter the Employee Name: ");
					empName=br.readLine();
					System.out.print("Enter the Employee Salary: ");
					empSalary=Float.parseFloat(br.readLine());
					System.out.print("Enter the Employee Address: ");
					empAddress=br.readLine();
					
					employee=new Employee();
					employee.setEmpId(empId);
					employee.setEmpName(empName);
					employee.setEmpSalary(empSalary);
					employee.setEmpAddress(empAddress);
					status=dao.add(employee);
					System.out.println(status);
					break;
				case 2:
					System.out.print("Enter the Employee Id: ");
					empId=Integer.parseInt(br.readLine());
					employee=dao.search(empId);
					if(employee==null) {
						System.out.println("Employee Not Existed");
					}else {
						System.out.println("Employee Details");
						System.out.println("----------------");
						System.out.println("Employee Id: "+employee.getEmpId());
						System.out.println("Employee Name: "+employee.getEmpName());
						System.out.println("Employee Salary: "+employee.getEmpSalary());
						System.out.println("Employee Address: "+employee.getEmpAddress());
					}
					break;
				case 3:
					System.out.print("Enter the Employee Id: ");
					empId=Integer.parseInt(br.readLine());
					employee=dao.search(empId);
					if(employee==null) {
						System.out.println("Employee Not Existed");
					}else{
						System.out.println("Employee Name: "+employee.getEmpName());
						System.out.print("Employee Old Salary ['"+employee.getEmpSalary()+"'] --> Enter the New Salary : ");
						float empSalary_New=Float.parseFloat(br.readLine());
						System.out.print("Employee Old Address ['"+employee.getEmpAddress()+"'] --> Enter the New Address : ");
						String empAddress_New=br.readLine();
						Employee empNew=new Employee();
						empNew.setEmpId(empId);
						empNew.setEmpName(empName);
						empNew.setEmpSalary(empSalary_New);
						empNew.setEmpAddress(empAddress_New);
						status=dao.update(empNew);
						System.out.println(status);
					}
					break;
				case 4:
					System.out.print("Enter the Employee Id: ");
					empId=Integer.parseInt(br.readLine());
					status=dao.delete(empId);
					System.out.println(status);
					break;
				case 5:
					System.out.println("****Thank You for using this Application****");
					System.exit(0);
					break;
				default:
					System.out.println("Enter the option from 1,2,3,4,5");
					break;
			}
		}
	}

}
