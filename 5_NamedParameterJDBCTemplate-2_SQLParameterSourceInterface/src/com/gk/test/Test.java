package com.gk.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gk.dao.CustomerDao;
import com.gk.dto.Customer;

public class Test {

	public static void main(String[] args) throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext("/com/gk/resources/applicationContext.xml");
		CustomerDao customerDao = (CustomerDao) context.getBean("customerDao");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println();
			System.out.println("1. Add Employee");
			System.out.println("2. Search Employee");
			System.out.println("3. Update Employee");
			System.out.println("4. Delete Employee");
			System.out.println("5. Exit");
			System.out.print("Your Option [1,2,3,4,5] : ");
			int option = Integer.parseInt(br.readLine());
			System.out.println();
			Customer customer = null;
			int cid = 0;
			String cname = "";
			String caddr = "";
			String status = "";
			switch (option) {
				case 1:
					System.out.print("Enter the Customer Id: ");
					cid=Integer.parseInt(br.readLine());
					System.out.print("Enter the Customer Name: ");
					cname=br.readLine();
					System.out.print("Enter the Customer Address: ");
					caddr=br.readLine();
					customer=new Customer();
					customer.setCid(cid);
					customer.setCname(cname);
					customer.setCaddr(caddr);
					status=customerDao.add(customer);
					System.out.println(status);
					break;
				case 2:
					System.out.print("Enter the Customer Id: ");
					cid=Integer.parseInt(br.readLine());
					customer=customerDao.search(cid);
					if(customer==null) {
						System.out.println("Customer Not Existed");
					}else {
						System.out.println("Customer Details");
						System.out.println("----------------");
						System.out.println("Customer Id: "+customer.getCid());
						System.out.println("Customer Name: "+customer.getCname());
						System.out.println("Customer Address: "+customer.getCaddr());
						System.out.println();
					}
					break;
				case 3:
					System.out.print("Enter the Customer Id: ");
					cid=Integer.parseInt(br.readLine());
					customer=customerDao.search(cid);
					if(customer==null) {
						System.out.println("Customer Not Existed");
					}else {
						System.out.println("Customer Id: "+customer.getCid());
						System.out.print("Customer Old Name ['"+customer.getCname()+"'] --> Enter the New Name : ");
						String cname_new=br.readLine(); 
						System.out.print("Customer Old Address ['"+customer.getCaddr()+"'] --> Enter the New Address : ");
						String caddr_new=br.readLine();
						Customer customerNew=new Customer();
						customerNew.setCid(cid);
						customerNew.setCname(cname_new);
						customerNew.setCaddr(caddr_new);
						status=customerDao.update(customerNew);
						System.out.println(status);
					}
					break;
				case 4:
					System.out.print("Enter the Customer Id: ");
					cid=Integer.parseInt(br.readLine());
					status=customerDao.delete(cid);
					System.out.println(status);
					break;
				case 5:
					System.out.println("***Thank You For Using this Application***");
					System.exit(0);
					break;
				default:
					System.out.println("Enter the option from 1,2,3,4,5");
					break;
			}
		}
	}

}
