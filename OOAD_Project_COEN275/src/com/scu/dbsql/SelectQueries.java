package com.scu.dbsql;

import com.project.EcoRe.RCMRecycle;

public class SelectQueries {

	public String getPasswordByUserName(String userName) {
		String sql = "SELECT PASSWORD FROM USER where USERNAME='" + userName
				+ "';";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForAddRCM(RCMRecycle rcm) {
		String sql = "INSERT INTO RCMRECYCLE (LOCATION) VALUES ('"
				+ rcm.getLocation() + "');";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForDeleteRCMById(String idToDelete) {
		String sql = "DELETE FROM RCMRECYCLE WHERE RCMNUM in (" + idToDelete
				+ ");";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForActivateRCM(int rcmNum) {
		String sql = "UPDATE RCMRECYCLE SET ISACTIVATED='" + "ACTIVE"
				+ "' WHERE RCMNUM =" + rcmNum + ";";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForDeactivateRCM(int rcmNum) {
		String sql = "UPDATE RCMRECYCLE SET ISACTIVATED='" + "DEACTIVE"
				+ "' WHERE RCMNUM =" + rcmNum + ";";
		System.out.println(sql);
		return sql;
	}

	public String getRCMNum() {

		return "SELECT MAX(RCMNUM) FROM RCMRECYCLE;";
	}

}