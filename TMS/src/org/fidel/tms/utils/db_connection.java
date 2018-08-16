package org.fidel.tms.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class db_connection {
	
	public static Connection getConnection(){
		
		Connection con = null;
		
		String url = "jdbc:mysql://localhost:3309/tms_tilahun";
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, "root", "P@55yamget");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}			
		
		return con;
	}

}
