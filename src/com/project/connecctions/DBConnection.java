package com.project.connecctions;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection getConnection() throws Exception {
		String url = "jdbc:mysql://localhost:3306/OOAD_db";
		String user = "admin";
		String password = "admin123";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = null;
		con = DriverManager.getConnection(url, user, password);
		System.out.println("connection done!");
		return con;
	}
	
	//THis is just a test connection. Need to remove this if we are not going to use this
	//#TODO
/*	public static Connection getConnectionTest() throws Exception {
		String url = "jdbc:mysql://localhost:3306/testdb";
		String user = "admin";
		String password = "admin123";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = null;
		con = DriverManager.getConnection(url, user, password);
		System.out.println("connection done!");
		return con;
	}*/
}

