
/*-------------------Stored Procedure with Cursor---------------
DROP procedure IF EXISTS `list_name`;
DELIMITER $$
CREATE PROCEDURE list_name(INOUT name_list varchar(4000))
BEGIN
DECLARE finished INTEGER DEFAULT 0;
DECLARE emp_name VARCHAR(100) DEFAULT "";
DECLARE emp_cursor CURSOR FOR select empName from EMPLOYEE;
DECLARE CONTINUE HANDLER
FOR NOT FOUND SET finished=1;
OPEN emp_cursor;
get_list:LOOP
FETCH emp_cursor INTO emp_name;
IF finished =1 THEN 
LEAVE get_list;
END IF;
SET name_list=CONCAT(emp_name,";",name_list);
END LOOP get_list;
CLOSE emp_cursor;
END $$
DELIMITER ;

-------------------------------------Calling a Stored procedure with cursor in database----------
SET @name_list="";

CALL list_name(@name_list);

SELECT @name_list;
+-------------------------------------+
| @name_list                          |
+-------------------------------------+
| Rohit Sharma;Virat Kohli;M S Dhoni; |
+-------------------------------------+
----------------------------------------------------------------*/
package com.gk.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class ProcedureWithCursor {

	public static void main(String[] args) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/springjdbcdaodb", "root", "root");
		CallableStatement cst=con.prepareCall("{call list_name(?)}");
		cst.setString(1, "Rohit Sharma");
		cst.registerOutParameter(1,Types.VARCHAR);
		cst.execute();
		System.out.println(cst.getString(1));
		cst.close();
		con.close();
		
	}

}
