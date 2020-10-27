package com.gk.test;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/*
 * TINYBLOB = 255 bytes, BLOB = 64KB, MEDIUMBLOB = 16MB and LONGBLOB = 4GB Run
 */

public class BLOBInsert {

	public static void main(String[] args) throws Exception {
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

			PreparedStatement pst = con.prepareStatement("insert into EMPLOYEE1 values(?,?)");
			pst.setInt(1, 100);

			File f = new File("F:/My ImAgEs/aps.jpg");
			FileInputStream fis = new FileInputStream(f);
			pst.setBinaryStream(2, fis, (int) f.length());
			int rowCount = pst.executeUpdate();
			if (rowCount == 1) {
				System.out.println("Employee 100 Inserted Successfully");
			} else {
				System.out.println("Employee Insertion failure");
			}
			fis.close();
			pst.close();
			con.close();

		} catch (Exception e) {
			System.out.println("Employee Insertion failure");
			e.printStackTrace();
		}
	}

}
