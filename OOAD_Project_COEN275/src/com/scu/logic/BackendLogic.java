package com.scu.logic;

import com.project.EcoRe.RCMRecycle;
import com.scu.connecctions.ScuDbConn;
import com.scu.dbsql.SelectQueries;

public class BackendLogic {

	ScuDbConn dbConnection = new ScuDbConn();
	SelectQueries query = new SelectQueries();

	public boolean validateUser(String userName, String password) {
		String sql = query.getPasswordByUserName(userName);
		String passwordFromDB = dbConnection.getValueFromSql(sql);
		if (password != null && password.equals(passwordFromDB)) {
			return true;
		} else {
			return false;
		}
	}

	public String addRCM(RCMRecycle rcm) {
		String sql = query.createSqlForAddRCM(rcm);
		boolean isAdded = dbConnection.insertIntoDB(sql);
		if (isAdded) {
			sql = null;
			sql = query.getRCMID();
			String rcmId = dbConnection.getValueFromSql(sql);
			return "RCM ID " + rcmId + " is added successfully.";
		} else {
			return "RCM is not added Sucessful";
		}
	}

	public boolean activateRCM(String rcmId) {
		String sql = query.createSqlForActivateRCM(rcmId);
		boolean isUpdate = dbConnection.updateToDB(sql);
		return isUpdate;
	}
	
	public boolean deactivateRCM(String rcmId) {
		String sql = query.createSqlForDeactivateRCM(rcmId);
		boolean isUpdate = dbConnection.updateToDB(sql);
		return isUpdate;
	}

	public boolean removeRCM(String rcmId) {
		String sql = query.createSqlForDeleteRCMById(rcmId);
		boolean isDeleted = dbConnection.deleteFromDB(sql);
		return isDeleted;
	}

	public void getRCMList() {
		// TODO Auto-generated method stub

	}
	public void updatePrice(int temp) {
		// TODO Auto-generated method stub

	}

	public void getItemList() {
		// TODO Auto-generated method stub

	}

	public boolean isvalidRcmId(String rcmId) {
		String sql = query.createSqlForValidRCMById(rcmId);
		String location = dbConnection.getValueFromSql(sql);
		return Validation.isNotNullorEmplty(location);
	}

	public boolean setFund(String rcmId, int amount) {
		String sql = query.createSqlForSetFund(rcmId,amount);
		boolean isUpdate = dbConnection.updateToDB(sql);
		return isUpdate;
	}

	public boolean clearRCMWeight(String rcmId) {
		int weight = 0; //set to 0 for clearing
		String sql = query.createSqlForsetWeight(rcmId,weight);
		boolean isUpdate = dbConnection.updateToDB(sql);
		return isUpdate;
	}
}
