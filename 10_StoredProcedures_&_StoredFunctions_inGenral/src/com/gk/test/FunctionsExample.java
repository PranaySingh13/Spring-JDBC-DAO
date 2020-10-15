/*
 * --------------Stored Functions is used to perform calculation(Only SELECT QUERIES ALLOWED).-------------

----CREATE A FUNCION IN DATABASE----------

DELIMITER $$
CREATE FUNCTION getAverage(no1 int,no2 int,no3 int)
RETURNS FLOAT
DETERMINISTIC
BEGIN
DECLARE esal1 FLOAT;
DECLARE esal2 FLOAT;
DECLARE esal3 FLOAT;
DECLARE avg FLOAT;
select empSalary into esal1 from EMPLOYEE where empId=no1;
select empSalary into esal2 from EMPLOYEE where empId=no2;
select empSalary into esal3 from EMPLOYEE where empId=no3;
SET avg=(esal1+esal2+esal3)/3;
RETURN avg;
END $$
DELIMITER ;

----CALLING OR SELECTING A FUNCTION IN DATABASE--------

SELECT getAverage(1,2,3);
+-------------------+
| getAverage(1,2,3) |
+-------------------+
|              2000 |
+-------------------+

----DROP A FUNCION IN DATABASE------------

DROP FUNCTION springjdbcdaodb.getAverage;

 --------------------------------------*/

package com.gk.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class FunctionsExample {

	public static void main(String[] args) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/springjdbcdaodb", "root", "root");
		CallableStatement cst = con.prepareCall("{? = call getAverage(?,?,?)}");
		//Set IN parameter
		cst.setInt(2, 1);
		cst.setInt(3, 2);
		cst.setInt(4, 3);
		//Set OUT parameter
		cst.registerOutParameter(1, Types.FLOAT);
		//Execute stored procedure
		cst.execute();
		//Get OUT parameter
		System.out.println("Average Salary = " + cst.getFloat(1));
		cst.close();
		con.close();

	}

}
