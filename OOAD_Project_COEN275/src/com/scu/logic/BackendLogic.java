package com.scu.logic;

import java.util.List;

import com.project.EcoRe.RCMRecycle;
import com.scu.connecctions.ScuDbConn;
import com.scu.dbsql.SelectQueries;

import java.sql.ResultSet;

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

	public boolean isvalidRcmId(String rcmId) {
		String sql = query.createSqlForValidRCMById(rcmId);
		String location = dbConnection.getValueFromSql(sql);
		return Validation.isNotNullorEmplty(location);
	}

	public boolean setFund(String rcmId, int amount) {
		String sql = query.createSqlForSetFund(rcmId, amount);
		boolean isUpdate = dbConnection.updateToDB(sql);
		return isUpdate;
	}

	public boolean clearRCMWeight(String rcmId) {
		int weight = 0; // set to 0 for clearing
		String sql = query.createSqlForsetWeight(rcmId, weight);
		boolean isUpdate = dbConnection.updateToDB(sql);
		return isUpdate;
	}

	public List<String> getAllRmsId() {
		String sql = query.createSqlForFetchRMSids();
		List<String> outputList = dbConnection.getListOfFirstClm(sql);
		return outputList;
	}

	public String getWeight(String rcmId) {
		String sql = query.createSqlForCurrentWeight(rcmId);
		String weight = dbConnection.getValueFromSql(sql);
		return weight;
	}

	public String getCoupon(String rcmId) {
		String sql = query.createSqlForCurrentCoupon(rcmId);
		String weight = dbConnection.getValueFromSql(sql);
		return weight;
	}

	public String getCash(String rcmId) {
		String sql = query.createSqlForCurrentCash(rcmId);
		String weight = dbConnection.getValueFromSql(sql);
		return weight;
	}

	public String getItemsRecycledByMonth(String rcmId) {
		String sql = query.createSqlForRecycledByMonth(rcmId);
		String tempVal = dbConnection.getValueFromSql(sql);
		return tempVal;
	}

	public String getCurrItemsRecycled(String rcmId) {
		String sql = query.createSqlForCurrItemsRecycled(rcmId);
		String tempVal = dbConnection.getValueFromSql(sql);
		return tempVal;
	}

	public String getWeightPerMonth(String rcmId) {
		String sql = query.createSqlForWeightPerMonth(rcmId);
		String tempVal = dbConnection.getValueFromSql(sql);
		return tempVal;
	}

	public String getCashDebitedPerMonth(String rcmId) {
		String sql = query.createSqlForCashDebitedPerMonth(rcmId);
		String tempVal = dbConnection.getValueFromSql(sql);
		return tempVal;
	}

	public String getLastEmptied(String rcmId) {
		String sql = query.createSqlForLastEmptied(rcmId);
		String tempVal = dbConnection.getValueFromSql(sql);
		return tempVal;
	}

	public boolean addItem(String itemType, double itemWeightUnit,
			double unitPrice) {
		// TODO Auto-generated method stub

		String sql = query.createSqlForAddItem(itemType, itemWeightUnit,
				unitPrice);
		boolean addItem = dbConnection.updateToDB(sql);
		return addItem;
	}

	public boolean removeItem(String itemType) {
		// TODO Auto-generated method stub
		String sql = query.createSqlForDeleteItem(itemType);
		boolean deleteItem = dbConnection.deleteFromDB(sql);
		return deleteItem;
	}

	public boolean updatePrice(String itemType, double uPrice) {
		// TODO Auto-generated method stub
		String sql = query.createSqlForUpdatePrice(itemType, uPrice);
		boolean updatePrice = dbConnection.updateToDB(sql);
		return updatePrice;
	}

	public ResultSet getItemList() {
		// TODO Auto-generated method stub
		String sql = query.createSqlForGetItemList();
		ResultSet itemList = dbConnection.getResultSet(sql);
		return itemList;

	}

	public String getMostUsedRCM() {
		String sql = query.createSqlForMostUsedRCM();
		String mostusedrcm = dbConnection.getValueFromSql(sql);
		return mostusedrcm;
	}

	public boolean addTransactionAmount(String rcmid, String itemType,
			double transweight, double transamount) {
		String sql = query.createSqlForTransactionAmount(rcmid, itemType,
				transweight, transamount);
		boolean addItem = dbConnection.insertIntoDB(sql);
		return addItem;
	}

	public boolean addTransactionCoupon(String rcmid, String itemType,
			double transweight, double transcoupon) {
		String sql = query.createSqlForTransactionCoupon(rcmid, itemType,
				transweight, transcoupon);
		boolean addItem = dbConnection.insertIntoDB(sql);
		return addItem;
	}

	public String getCurrentStatus(String rcmId) {
		String sql = query.createSqlForCurrentStatus(rcmId);
		String status = dbConnection.getValueFromSql(sql);
		return status;
	}

	public String monthlyTrasnsaction(String rcmId) {
		String sql = query.createSqlForMonthlyTrasnsaction(rcmId);
		String count = dbConnection.getValueFromSql(sql);
		return count;
	}
}
