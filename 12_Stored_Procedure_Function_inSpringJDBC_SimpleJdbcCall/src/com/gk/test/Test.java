package com.gk.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.print.attribute.standard.MediaSize.Other;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gk.dao.EmployeeDao;
import com.gk.dto.Employee;

public class Test {

	public static void main(String[] args) throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext("/com/gk/resources/applicationContext.xml");
		EmployeeDao dao = (EmployeeDao) context.getBean("employeeDao");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("1. Add Employee");
			System.out.println("2. Get Employee");
			System.out.println("3. Get Employee Salary");
			System.out.println("4. Update Employee");
			System.out.println("5. Delete Employee");
			System.out.println("6. Exit");
			System.out.print(" Your Options between [1,2,3,4,5,6]: ");
			int option = Integer.parseInt(br.readLine());
			Employee employee = null;
			int empId = 0;
			String empName = "";
			float empSalary = 0;
			String empAddress = "";
			String status = "";
			switch (option) {
			case 1:
				System.out.print("Enter the Employee Id: ");
				empId = Integer.parseInt(br.readLine());
				System.out.print("Enter the Employee Name: ");
				empName = br.readLine();
				System.out.print("Enter the Employee Salary: ");
				empSalary = Float.parseFloat(br.readLine());
				System.out.print("Enter the Employee Address: ");
				empAddress = br.readLine();
				Employee emp1 = dao.getEmployee(empId);
				if (emp1 != null) {
					System.out.println("Employee Existed Already");
				} else {
					employee = new Employee();
					employee.setEmpId(empId);
					employee.setEmpName(empName);
					employee.setEmpSalary(empSalary);
					employee.setEmpAddress(empAddress);
					dao.addEmployee(employee);
					System.out.println("Employee Inserted Successfully");
				}
				break;
			case 2:
				System.out.print("Enter the Employee Id: ");
				empId = Integer.parseInt(br.readLine());
				Employee emp = dao.getEmployee(empId);
				if (emp == null) {
					System.out.println("Employee Not Existed");
				} else {
					System.out.println("Employee Id: " + emp.getEmpId());
					System.out.println("Employee Name: " + emp.getEmpName());
					System.out.println("Employee Salary: " + emp.getEmpSalary());
					System.out.println("Employee Address: " + emp.getEmpAddress());
				}
				break;
			case 3:
				System.out.print("Enter the Employee Id: ");
				empId = Integer.parseInt(br.readLine());
				Employee emp3 = dao.getEmployee(empId);
				if (emp3 == null) {
					System.out.println("Employee Not Existed");
				} else {
					float outEmpSalary = dao.getEmployeeSalary(empId);
					System.out.println("Employee Salary: " + outEmpSalary);
				}
				break;
			case 4:
				System.out.print("Enter the Employee Id: ");
				empId = Integer.parseInt(br.readLine());
				Employee emp4 = dao.getEmployee(empId);
				if (emp4 == null) {
					System.out.println("Employee Not Existed");
				} else {
					System.out.print("Enter the Employee New Name: ");
					empName = br.readLine();
					System.out.print("Enter the Employee New Salary: ");
					empSalary = Float.parseFloat(br.readLine());
					System.out.print("Enter the Employee New Address: ");
					empAddress = br.readLine();
					Employee empNew = new Employee();
					empNew.setEmpId(empId);
					empNew.setEmpName(empName);
					empNew.setEmpSalary(empSalary);
					empNew.setEmpAddress(empAddress);
					dao.updateEmployee(empNew);
					System.out.println("Employee Updated Successfully");
					System.out.println("Employee Id: " + empNew.getEmpId());
					System.out.println("Employee Name: " + empNew.getEmpName());
					System.out.println("Employee Salary: " + empNew.getEmpSalary());
					System.out.println("Employee Address: " + empNew.getEmpAddress());
				}
				break;
			case 5:
				System.out.print("Enter the Employee Id: ");
				empId = Integer.parseInt(br.readLine());
				Employee emp2 = dao.getEmployee(empId);
				if (emp2 == null) {
					System.out.println("Employee Not Existed");
				} else {
					dao.deleteEmployee(empId);
					System.out.println("Employee Deleted Successfully");
				}
				break;
			case 6:
				System.out.println("****Thank You for using this Application****");
				System.exit(0);
				break;
			default:
				System.out.println("Enter the Option from 1,2,3,4,5,6");
				break;
			}

		}
	}

}
