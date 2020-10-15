
/*-------Stored Procedure is used to perform business logic(All CRUD QUERIES ALLOWED).----------------
 
 --------CREATING A STORED PROCEDURE IN DATABASE--------------
 
 DELIMITER $$ 
 CREATE PROCEDURE getEmpSalary(IN eid int,OUT esal decimal(10,2))
 BEGIN
 select empSalary into esal from EMPLOYEE where empId = eid;
 END $$
 DELIMITER ;
 
 --------CALLING A STORED PROCEDURE IN DATABASE---------------
 
 CALL getEmpSalary(1,@empSalary);
 SELECT @empSalary;
 +------------+
| @empSalary |
+------------+
|    1000.00 |
+------------+
 
 --------DROPING A STORED PROCEDURE IN DATABASE---------------
 
 DROP PROCEDURE getEmpSalary
 
 ----------------------------------------*/

package com.gk.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class ProcedureExample {

	public static void main(String[] args) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/springjdbcdaodb", "root", "root");
		CallableStatement cst = con.prepareCall("{call getEmpSalary(?,?)}");
		//Set IN parameter
		cst.setInt(1, 2);
		//Set OUT parameter
		cst.registerOutParameter(2, Types.FLOAT);
		//Execute stored procedure
		cst.execute();
		// Get Out parameters
		System.out.println("Salary: " + cst.getFloat(2));
		cst.close();
		con.close();
	}

}
