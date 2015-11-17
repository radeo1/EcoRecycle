package com.scu.connecctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ScuDbConn {

	public boolean deleteFromDB(String sql) {
		Statement stmt;
		boolean isDeleted;
		try {
			stmt = DBConnection.getConnection().createStatement();
			stmt.execute(sql);
			isDeleted = true;
		} catch (Exception e) {
			isDeleted = false;
			System.out.println("Error at deleteFromDB() in ScuDbConn.java");
			e.printStackTrace();
		}
		return isDeleted;
	}

	public boolean updateToDB(String sql) {
		Statement stmt;
		boolean isUpdate;
		try {
			stmt = DBConnection.getConnection().createStatement();
			stmt.executeUpdate(sql);
			isUpdate = true;
		} catch (Exception e) {
			isUpdate = false;
			System.out.println("Error at updateToDB() in ScuDbConn.java");
			e.printStackTrace();
		}
		return isUpdate;
	}

	public boolean insertIntoDB(String sql) {
		Statement stmt;
		boolean isInsert;
		try {
			stmt = DBConnection.getConnection().createStatement();
			stmt.execute(sql);
			isInsert = true;
		} catch (Exception e) {
			isInsert = false;
			System.out.println("Error at insertIntoDB() in ScuDbConn.java");
			// e.printStackTrace();
		}
		return isInsert;
	}

	public String getValueFromSql(String sql) {
		String output = null;
		Statement stmt;
		try {
			stmt = DBConnection.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				output = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}

	public Map<String, String> getKeyValueFromSql(String sqlavailability) {

		Map<String, String> output = new HashMap<String, String>();
		Statement stmt;
		try {
			stmt = DBConnection.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sqlavailability);
			while (rs.next()) {
				output.put(rs.getString(1), rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;

	}

	public ResultSet getResultSet(String sql) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost:3306/testdb";
		String user = "admin";
		String password = "admin123";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery(sql);
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				System.out.println(ex);
			}
		}
		return rs;
	}

}
