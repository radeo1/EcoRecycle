package com.scu.logic;

import com.project.EcoRe.RCMRecycle;
import com.scu.connecctions.ScuDbConn;
import com.scu.dbsql.SelectQueries;


public class BackendLogic {

	ScuDbConn dbConnection = new ScuDbConn();
	SelectQueries query = new SelectQueries();

	public boolean validateUser(String userID, String password) {
		String sql = query.getPasswordByUserId(userID);
		String passwordFromDB = dbConnection.getValueFromSql(sql);
		if (password != null && password.equals(passwordFromDB)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean addRCM(RCMRecycle rcm) {
		String sql = query.createSqlForAddRCM(rcm);
		boolean isAdded = dbConnection.insertIntoDB(sql);
		return isAdded;		
	}

	public boolean activateRCM(int rcmNum) {
		String sql = query.createSqlForActivateRCM(rcmNum);
		boolean isUpdate = dbConnection.updateToDB(sql);
		return isUpdate;		
	}

	public boolean removeRCM(int rcmNum) {
		String sql = query.createSqlForDeleteRCMById(rcmNum);
		boolean isDeleted = dbConnection.deleteFromDB(sql);
		return isDeleted;
	}
}