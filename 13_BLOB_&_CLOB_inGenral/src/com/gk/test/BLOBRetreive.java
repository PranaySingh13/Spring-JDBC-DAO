package com.gk.test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BLOBRetreive {

	public static void main(String[] args) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		/*
		 * WARN: Caught while disconnecting... javax.net.ssl.SSLException: closing
		 * inbound before receiving peer's close_notify
		 * 
		 * thats why ssl=false.
		 */
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/springjdbcdaodb?useSSL=false",
				"root", "root");

		PreparedStatement pst = con.prepareStatement("select * from EMPLOYEE1 where EMPID = ?");
		pst.setInt(1, 100);
		ResultSet rs = pst.executeQuery();

		// now on 1st row
		while (rs.next()) {
			Blob b=rs.getBlob(2);
			InputStream is = b.getBinaryStream();
			byte byteArray[] = new byte[is.available()];
			is.read(byteArray);
			FileOutputStream fos = new FileOutputStream("F:\\My ImAgEs\\new.jpg");
			fos.write(byteArray);
			fos.close();
			is.close();
		}

		System.out.println("Image Data Retrieve from Database");
		rs.close();
		pst.close();
		con.close();

	}
}
