package project1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToSQL {
	public static boolean testMode = false;
	public static Connection getConnection() {
		
		// First time we will use a literal user and password
		// and we will refactor to use environment variables for safety
		// in practice you should NEVER use literals
		
		// Note: JDBC url has a specific format
		//  jdbc:database-type://network-location:port/internal-database'
		System.out.println("ConnectionToSQL");
		String user = System.getenv("H_ROLE");
		String pw = System.getenv("H_PASS");
		System.out.println(user + pw);
		String aws = "jdbc:postgresql://database-1.c6vmcdm1hagz.us-east-2.rds.amazonaws.com:5432/postgres"
				+ "?user=" + user + "&password=" + pw;
		
		String url = "jdbc:postgresql://localhost:5432/postgres";
		try {
			Connection conn = DriverManager.getConnection(url, 
								System.getenv("VENDOR_ROLE"), 
								System.getenv("VENDOR_PASS"));

//			Connection conn = DriverManager.getConnection(aws);
			
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to connect to database. Sad :(");
			return null;
		}
	}

}
