/**
 * @author Pragati Shrivastava
 * @author Rachana Deolikar
 * @version 1.0
 *  This is an interface with the database and GUI
 */

package com.project.logic;

import java.util.List;
import java.util.Map;

import com.project.EcoRe.Constant;
import com.project.EcoRe.RCMRecycle;
import com.project.connecctions.ScuDbConn;
import com.project.dbsql.SelectQueries;
import com.project.event.batch.Event;

public class BackendLogic {

	ScuDbConn dbConnection = new ScuDbConn();
	SelectQueries query = new SelectQueries();

	/**
	 * This method validates user
	 * 
	 * @param userName
	 * @param password
	 * @return boolean
	 */
	public boolean validateUser(String userName, String password) {
		String sql = query.getPasswordByUserName(userName);
		String passwordFromDB = dbConnection.getValueFromSql(sql);
		if (password != null && password.equals(passwordFromDB)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method adds RCM
	 * 
	 * @param rcm
	 * @return String
	 */
	public String addRCM(RCMRecycle rcm) 
	{
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

	/**
	 * This method activates RCM
	 * 
	 * @param rcmId
	 * @return Boolean
	 */
	public boolean activateRCM(String rcmId) {
		String sql = query.createSqlForActivateRCM(rcmId);
		boolean isUpdate = dbConnection.updateToDB(sql);
		return isUpdate;
	}

	/**
	 * This method deactivates RCM
	 * 
	 * @param rcmId
	 * @return boolean
	 */
	public boolean deactivateRCM(String rcmId) {
		String sql = query.createSqlForDeactivateRCM(rcmId);
		boolean isUpdate = dbConnection.updateToDB(sql);
		return isUpdate;
	}

	/**
	 * This method removes RCM
	 * 
	 * @param rcmId
	 * @return boolean
	 */
	public boolean removeRCM(String rcmId) {
		String sql = query.createSqlForDeleteRCMById(rcmId);
		boolean isDeleted = dbConnection.deleteFromDB(sql);
		return isDeleted;
	}

	/**
	 * This method checks if RCM is valid
	 * 
	 * @param rcmId
	 * @return boolean
	 */
	public boolean isvalidRcmId(String rcmId) 
	{
		String sql = query.createSqlForValidRCMById(rcmId);
		String location = dbConnection.getValueFromSql(sql);
		return Validation.isNotNullorEmplty(location);
	}

	/**
	 * This method sets fund for a particular RCM
	 * 
	 * @param rcmId
	 * @param d
	 * @return boolean
	 */
	public boolean setFund(String rcmId, double d) {//called on transaction or btn press
		String sql = query.createSqlForSetFund(rcmId, d);
		boolean isUpdate = dbConnection.updateToDB(sql);
		checkForEventEntry(rcmId);
		return isUpdate;
	}

	/**
	 * This method empties RCM content
	 * 
	 * @param rcmId
	 * @return boolean
	 */
	public boolean clearRCMWeight(String rcmId) {
		double weight = 0; // set to 0 for clearing
		String sql = query.createSqlForsetWeight(rcmId, weight);
		boolean isUpdate = dbConnection.updateToDB(sql);
		return isUpdate;
	}

	/**
	 * This method updates RCM content
	 * 
	 * @param rcmId
	 * @param weight
	 * @return boolean
	 */
	public boolean updateRCMWeight(String rcmId, double weight) {
		String sql = query.createSqlForsetWeight(rcmId, weight);
		boolean isUpdate = dbConnection.updateToDB(sql);
		checkForEventEntry(rcmId);
		return isUpdate;
	}

	/**
	 * This method Lists all rmos id
	 * 
	 * @param rmosId
	 * @return list
	 */
	public List<String> getAllRmsId(String rmosId) {//getAllRcmId
		String sql = query.createSqlForFetchRMSids(rmosId);//fetch rcmid
		List<String> outputList = dbConnection.getListOfFirstClm(sql);//creates a list of first column and returns
		return outputList;
	}

	/**
	 * This method gets RCM's weight/content status
	 * 
	 * @param rcmId
	 * @return String
	 */
	public String getWeight(String rcmId) {
		String sql = query.createSqlForCurrentWeight(rcmId);
		String weight = dbConnection.getValueFromSql(sql);
		return weight;
	}

	/**
	 * This method gets RCM coupon status
	 * 
	 * @param rcmId
	 * @return String
	 */
	public String getCoupon(String rcmId) {
		String sql = query.createSqlForCurrentCoupon(rcmId);
		String weight = dbConnection.getValueFromSql(sql);
		return weight;
	}

	/**
	 * This method gets RCM cash status
	 * 
	 * @param rcmId
	 * @return String
	 */
	public String getCash(String rcmId) {
		String sql = query.createSqlForCurrentCash(rcmId);
		String weight = dbConnection.getValueFromSql(sql);
		return weight;
	}

	/**
	 * This method gets number of items recycled in a month for a RCM
	 * 
	 * @param rcmId
	 * @return String
	 */
	public String getItemsRecycledByMonth(String rcmId) {
		String sql = query.createSqlForRecycledByMonth(rcmId);
		String tempVal = dbConnection.getValueFromSql(sql);
		return tempVal;
	}

	/**
	 * This method gets number of item recycled on a particular day
	 * 
	 * @param rcmId
	 * @return String
	 */
	public String getCurrItemsRecycled(String rcmId) {
		String sql = query.createSqlForCurrItemsRecycled(rcmId);
		String tempVal = dbConnection.getValueFromSql(sql);
		return tempVal;
	}

	/**
	 * This method gets weight of RCM for a month
	 * 
	 * @param rcmId
	 * @return string
	 */
	public String getWeightPerMonth(String rcmId) {
		String sql = query.createSqlForWeightPerMonth(rcmId);
		String tempVal = dbConnection.getValueFromSql(sql);
		return tempVal;
	}
        
        /**
	 * This method gets cash debited for a month
	 * 
	 * @param rcmId
	 * @return string
	 */
	public String getCashDebitedPerMonth(String rcmId) {
		String sql = query.createSqlForCashDebitedPerMonth(rcmId);
		String tempVal = dbConnection.getValueFromSql(sql);
		return tempVal;
	}

        /**
	 * This method gets the date the RCM was emptied last time
	 * 
	 * @param rcmId
	 * @return string
	 */
	public String getLastEmptied(String rcmId) {
		String sql = query.createSqlForLastEmptied(rcmId);
		String tempVal = dbConnection.getValueFromSql(sql);
		return tempVal;
	}

        /**
	 * This method adds an item to the Recycle item list
	 * 
	 * @param itemType
         * @param itemWeightUnit
         * @param unitPrice
	 * @return boolean
	 */
	public boolean addItem(String itemType, double itemWeightUnit,
			double unitPrice) {
		String sql = query.createSqlForAddItem(itemType, itemWeightUnit,
				unitPrice);
		boolean addItem = dbConnection.updateToDB(sql);
		return addItem;
	}

         /**
	 * This method removes an item from the Recycle item list
	 * 
	 * @param itemType
	 * @return boolean
	 */
	public boolean removeItem(String itemType) {
		String sql = query.createSqlForDeleteItem(itemType);
		boolean deleteItem = dbConnection.deleteFromDB(sql);
		return deleteItem;
	}
        
        /**
	 * This method updates the price of an item in the Recycle item list
	 * 
	 * @param itemType
         * @param uPrice
	 * @return boolean
	 */
	public boolean updatePrice(String itemType, double uPrice) {
		String sql = query.createSqlForUpdatePrice(itemType, uPrice);
		boolean updatePrice = dbConnection.updateToDB(sql);
		return updatePrice;
	}
        
        /**
	 * This method lists all the items in the Recycle item list
	 * 
	 * @return Map
	 */
	public Map<String, String> getItemList() {
		String sql = query.createSqlForGetItemList();
		Map<String, String> itemList = dbConnection.getKeyValueFromSql(sql);
		               //System.out.println(itemList);
		return itemList;
		               
		}


         /**
	 * This method gets the RCM most used in the last month
	 * 
	 * @return String
	 */
	public String getMostUsedRCM() {
		String sql = query.createSqlForMostUsedRCM();
		String mostusedrcm = dbConnection.getValueFromSql(sql);
		return mostusedrcm;
                
	}

         /**
	 * This method adds the details of the transaction in cash for a 
         * recycled item to the database
	 * 
         * @param rcmid
	 * @param itemType
         * @param transweight
         * @param transamount
         * @param dateStr
	 * @return boolean
	 */
	public boolean addTransactionAmount(String rcmid, String itemType,
			double transweight, double transamount,String dateStr) {
		String sql = query.createSqlForTransactionAmount(rcmid, itemType,
				transweight, transamount, dateStr);
		boolean addItem = dbConnection.insertIntoDB(sql);
		return addItem;
	}

         /**
	 * This method adds the details of the transaction in coupon for a 
         * recycled item to the database
	 * 
         * @param rcmid
	 * @param itemType
         * @param transweight
         * @param transcoupon
         * @param dateStr
	 * @return boolean
	 */
	public boolean addTransactionCoupon(String rcmid, String itemType,
			double transweight, double transcoupon,String dateStr) {
		String sql = query.createSqlForTransactionCoupon(rcmid, itemType,
				transweight, transcoupon, dateStr);
		boolean addItem = dbConnection.insertIntoDB(sql);
		return addItem;
	}

         /**
	 * This method gets the current status of the RCM selected
	 * 
         * @param rcmId
	 * @return String
	 */
	public String getCurrentStatus(String rcmId) {
		String sql = query.createSqlForCurrentStatus(rcmId);
		String status = dbConnection.getValueFromSql(sql);
		return status;
	}

        /**
	 * This method gets the total value of transactions made
         * in a RCM in the last month
	 * 
         * @param rcmId
	 * @return String
	 */
	public String monthlyTrasnsaction(String rcmId) {
		String sql = query.createSqlForMonthlyTrasnsaction(rcmId);
		String count = dbConnection.getValueFromSql(sql);
		return count;
	}

        /**
	 * This method gets the list of all RMOS ids
	 * 
         * @param userName
	 * @return List
	 */
	public List<String> getAllRmosId(String userName) {
		String sql = query.createSqlForFetchRMOSids(userName);
		List<String> outputList = dbConnection.getListOfFirstClm(sql);
		return outputList;
	}

        /**
	 * This method maps the RCM to a RMOS
	 * 
         * @param rcmId
         * @param rmosId
	 * @return boolean
	 */
	public boolean isMappingRcmIdRmosId(String rcmId, String rmosId) {
		String sql = query.createSqlTogetRmosIdbyRcmId(rcmId);
		String opRmosId = dbConnection.getValueFromSql(sql);
		if (rmosId.equalsIgnoreCase(opRmosId)) {
			return true;
		} else {
			return false;
		}
	}

        /**
	 * This method gets the list of all current events
	 * 
	 * @return List
	 */
	public List<Event> getCurrentEventList() {
		String sql = query.createSqlForEventList();
		List<Event> outputList = dbConnection.getEventList(sql);
		return outputList;

	}

        /**
	 * This method clears the list of events from the Events table
         * in database
	 * 
         * @param id
	 * @return boolean
	 */
	public boolean clearEvent(int id) {
		String sql = query.createSqlForDeleteEventById(id);
		boolean isDeleted = dbConnection.deleteFromDB(sql);
		return isDeleted;

	}
        
        /**
	 * This method sets the coupon value for a 
         * particular RCM in database
	 * 
         * @param rcmId
         * @param creditCoupon
	 * @return boolean
	 */
	public boolean setCoupon(String rcmId, double creditCoupon) {
		String sql = query.createSqlForSetCoupon(rcmId, creditCoupon);
		boolean isUpdate = dbConnection.updateToDB(sql);
		checkForEventEntry(rcmId);
		return isUpdate;
	}

        /**
	 * This method gets the location for a 
         * particular RCM in database
	 * 
         * @param rcmId
         * @return boolean
	 */
	public String getLocation(String rcmId) {
		String sql = query.createSqlForLocation(rcmId);
		String location = dbConnection.getValueFromSql(sql);
		return location;
	}

         /**
	 * This method checks the events table for entries 
         * corresponding to particular RCM in database
	 * 
         * @param rcmid
         */
	public void checkForEventEntry(String rcmid) {
		String weightIn = getWeight(rcmid);
		String couponIn = getCoupon(rcmid);
		String amountIn = getCash(rcmid);
		double weight = Double.parseDouble(weightIn);
		double coupon = Double.parseDouble(couponIn);
		double cash = Double.parseDouble(amountIn);
		if (weight > (0.75 * Constant.CAPACITY)) {
			makeEventEntry(rcmid, Constant.FILLED75P, "Current weight is "
					+ weight);
		}
		if (weight >= Constant.CAPACITY) {
			makeEventEntry(rcmid, Constant.FILLED100P, "Current weight is "
					+ weight);

		}
		if (cash < (0.25 * Constant.CREDIT_CASH)) {
			makeEventEntry(rcmid, Constant.CASH25P, "Current cash is "
					+ cash);
		}
		if (cash == 0) {
			makeEventEntry(rcmid, Constant.CASH0P, "Current cash is "
					+ cash);
		}
		if (coupon < (0.25 * Constant.CREDIT_COUPON)) {
			makeEventEntry(rcmid, Constant.COUPON25P, "Current Coupon is "
					+ coupon);
		}
		if (coupon == 0) {
			makeEventEntry(rcmid, Constant.COUPON0P, "Current Coupon is "
					+ coupon);
		}
	}

         /**
	 * This method makes the entries to the events table 
         * corresponding to a particular RCM in database
	 * 
         * @param rcmid
         * @param event
         * @param details
	 */
	public void makeEventEntry(String rcmid, String event, String details) {
		String sql = query.createSqlToMakeEventEntry(rcmid, event,details);
		dbConnection.updateToDB(sql);
	}
	//backend logic for rcm list
         /**
	 * This method lists all the active RCMs in the system
	 * 
         * @return List
	 */
	public List<String> getActiveRCMList() {
	String sql = query.createSqlForGetActiveRCMList();
	List<String> itemList = dbConnection.getListOfFirstClm(sql);
	               //System.out.println(itemList);
	return itemList;
	               
	}
}
