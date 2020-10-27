package com.gk.test;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/*
 * CLOB datatypes
 	TINYTEXT: A CLOB type with a maximum of 28-1 (255) characters.

	TEXT: A CLOB type with a maximum of 216-1 (65535) characters.

	MEDIUMTEXT: A CLOB type with a maximum of 224-1 (16777215) characters.

	LONGTEXT: A CLOB type with a maximum of 232-1 (4294967295 ) characters.
 */
public class CLOBInsert {

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			/*
			 * WARN: Caught while disconnecting... javax.net.ssl.SSLException: closing
			 * inbound before receiving peer's close_notify
			 * 
			 * thats why ssl=false.
			 */
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/springjdbcdaodb?useSSL=false",
					"root", "root");
			PreparedStatement pst = con.prepareStatement("insert into EMPLOYEE2 values(?,?)");
			pst.setString(1, "Pranay Details");
			File f=new File("F:/My ImAgEs/sample.txt");
			FileReader reader = new FileReader(f);
			pst.setCharacterStream(2, reader, f.length());
			int rowCount=pst.executeUpdate();
			if(rowCount==1) {
				System.out.println("Employee Inserted Successfully");
			}else {
				System.out.println("Employee Insertion Failed");
			}
		} catch (Exception e) {
			System.out.println("Employee Insertion Failed");
			e.printStackTrace();
		}
	}

}
