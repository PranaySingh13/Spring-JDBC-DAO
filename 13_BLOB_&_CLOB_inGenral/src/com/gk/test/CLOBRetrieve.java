package com.gk.test;

import java.io.File;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * BLOB 					CLOB
 * ----						----
 * blob					->	clob
 * InputStream 			->	Reader
 * FileInputStream 		->	FileReader
 * FileOutputStream 	->	FileWriter
 * setBinaryStream()	->	setCharacterStream()
 * getBinaryStream()	->	getCharacterStream()
 * 	
 */
public class CLOBRetrieve {

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

			PreparedStatement pst = con.prepareStatement("select * from employee2 where EMPNAME = ?");
			pst.setString(1, "Pranay Details");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {// now on 1st row
				Clob c = rs.getClob("EMPCONFIG");
				Reader r = c.getCharacterStream();
				File f = new File("F:/My ImAgEs/details_retrieve.txt");
				FileWriter fw = new FileWriter(f);
				int charItem;
				while ((charItem = r.read()) != -1) {
					fw.write(charItem);
				}
				fw.flush();// It flushes the stream it must be applied.
				fw.close();
				r.close();

			}
			rs.close();
			pst.close();
			con.close();
			System.out.println("CLOB Data Retrieved Successfully");
		} catch (Exception e) {
			System.out.println("ClOB Data Retrieved Failure");
			e.printStackTrace();
		}
	}

}
