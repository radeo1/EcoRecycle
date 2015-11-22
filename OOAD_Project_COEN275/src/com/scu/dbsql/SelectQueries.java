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
		String sql = "DELETE FROM RCMRECYCLE WHERE RCMID in (" + idToDelete
				+ ");";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForActivateRCM(String rcmId) {
		String sql = "UPDATE RCMRECYCLE SET ISACTIVATED='" + "ACTIVE"
				+ "' WHERE RCMID =" + rcmId + ";";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForDeactivateRCM(String rcmId) {
		String sql = "UPDATE RCMRECYCLE SET ISACTIVATED='" + "DEACTIVE"
				+ "' WHERE RCMID =" + rcmId + ";";
		System.out.println(sql);
		return sql;
	}

	public String getRCMID() {

		return "SELECT MAX(RCMID) FROM RCMRECYCLE;";
	}

	public String createSqlForValidRCMById(String rcmId) {
		String sql = "SELECT LOCATION FROM RCMRECYCLE WHERE RCMID = " + rcmId;
		return sql;
	}

	public String createSqlForSetFund(String rcmId, int amount) {
		String sql = "UPDATE RCMRECYCLE SET FUND=" + amount
				+ " WHERE RCMID =" + rcmId + ";";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForsetWeight(String rcmId, int weight) {
		String sql = "UPDATE RCMRECYCLE SET WEIGHT=" + weight
				+ " WHERE RCMID =" + rcmId + ";";
		System.out.println(sql);
		return sql;
	}

}