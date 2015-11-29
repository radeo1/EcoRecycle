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
		String sql = "INSERT INTO RCMRECYCLE (LOCATION,STATUS,RMOSID) VALUES ('"
				+ rcm.getLocation()
				+ "','INACTIVE','"
				+ rcm.getRmosId()
				+ "');";
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
		String sql = "UPDATE RCMRECYCLE SET STATUS='" + "ACTIVE"
				+ "' WHERE RCMID =" + rcmId + ";";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForDeactivateRCM(String rcmId) {
		String sql = "UPDATE RCMRECYCLE SET STATUS='" + "DEACTIVE"
				+ "' WHERE RCMID =" + rcmId + ";";
		System.out.println(sql);
		return sql;
	}

	public String getRCMID() {

		return "SELECT MAX(RCMID) FROM RCMRECYCLE;";
	}

	public String createSqlForValidRCMById(String rcmId) {
		String sql = "SELECT RCMID FROM RCMRECYCLE WHERE RCMID = " + rcmId;
		System.out.println(sql);
		return sql;
	}

	public String createSqlForSetFund(String rcmId, double d) {
		String sql = "UPDATE RCMRECYCLE SET FUND=" + d + " WHERE RCMID ="
				+ rcmId + ";";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForsetWeight(String rcmId, int weight) {
		String sql = "";
		if (weight > 0) {
			sql = "UPDATE RCMRECYCLE SET CURRENT_WEIGHT=" + weight
					+ " WHERE RCMID =" + rcmId + ";";
		} else {
			sql = "UPDATE RCMRECYCLE SET CURRENT_WEIGHT=" + weight
					+ ", LAST_EMPTIED = CURDATE() WHERE RCMID =" + rcmId + ";";

		}
		System.out.println(sql);
		return sql;
	}

	public String createSqlForFetchRMSids(String rmosId) {
		String sql = "SELECT RCMID FROM RCMRECYCLE WHERE RMOSID = '" + rmosId
				+ "'";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForCurrentWeight(String rcmId) {
		String sql = "SELECT CURRENT_WEIGHT FROM RCMRECYCLE WHERE RCMID ="
				+ rcmId;
		System.out.println(sql);
		return sql;
	}

	public String createSqlForCurrentCoupon(String rcmId) {
		String sql = "SELECT AVAILABLE_COUPON FROM RCMRECYCLE WHERE RCMID ="
				+ rcmId;
		System.out.println(sql);
		return sql;
	}

	public String createSqlForCurrentCash(String rcmId) {
		String sql = "SELECT AVAILABLE_CASH FROM RCMRECYCLE WHERE RCMID ="
				+ rcmId;
		System.out.println(sql);
		return sql;
	}

	public String createSqlForLastEmptied(String rcmId) {
		String sql = "SELECT LAST_EMPTIED FROM RCMRECYCLE WHERE RCMID ="
				+ rcmId;
		System.out.println(sql);
		return sql;
	}

	public String createSqlForWeightPerMonth(String rcmId) {
		String sql = "SELECT SUM(TRANSACTION_WEIGHT) AS MONTHLY_ACCUMULATED_WEIGHT FROM TRANSACTION WHERE RCMID ="
				+ rcmId
				+ " AND TRANSACTION_DATE between DATE_ADD(curdate(), INTERVAL -30 day) and Curdate() GROUP BY RCMID; ";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForCashDebitedPerMonth(String rcmId) {
		String sql = "SELECT SUM(TRANSACTION_AMOUNT)+SUM(TRANSACTION_COUPON) AS MONTHLY_AMOUNT_DEBITED FROM TRANSACTION WHERE RCMID ="
				+ rcmId
				+ " AND TRANSACTION_DATE between DATE_ADD(curdate(), INTERVAL -30 day) and Curdate() GROUP BY RCMID; ";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForCurrItemsRecycled(String rcmId) {
		String sql = "SELECT COUNT(TRANSACTION_DATE) AS CURRENT_ITEMS_RECYCLED FROM TRANSACTION WHERE RCMID ="
				+ rcmId
				+ "AND TRANSACTION_DATE =Curdate() GROUP BY RCMID,TRANSACTION_DATE;";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForRecycledByMonth(String rcmId) {
		String sql = "SELECT COUNT(TRANSACTION_DATE) AS MONTHLY_ITEMS_RECYCLED FROM TRANSACTION WHERE RCMID ="
				+ rcmId
				+ " AND TRANSACTION_DATE between DATE_ADD(curdate(), INTERVAL -30 day) and Curdate() GROUP BY RCMID; ";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForMostUsedRCM() {
		String sql;
		sql = "SELECT RCMID, MONTHLYWEIGHT, LOCATION FROM RcmRecycle ORDER BY SUM(TRANSACTION_WEIGHT) DESC; ";
		return sql;
	}

	public String createSqlForAddItem(String itemType, double itemWeightUnit,
			double unitPrice) {
		String sql;
		sql = "INSERT INTO RECYCLEITEM(ITEMTYPE, ITEM_UNIT_WEIGHT,UNIT_PRICE) VALUES ('"
				+ itemType + "'," + itemWeightUnit + ",'" + unitPrice + "'); ";
		return sql;
	}

	public String createSqlForDeleteItem(String itemType) {
		String sql;
		sql = "Delete from RECYCLEITEM where ITEMTYPE ='" + itemType + "';";
		return sql;
	}

	public String createSqlForUpdatePrice(String itemType, double unitPrice) {
		String sql;
		sql = "Update RECYCLEITEM set UNIT_PRICE = " + unitPrice
				+ " where ITEMTYPE ='" + itemType + "';";
		return sql;
	}

	public String createSqlForGetItemList() {
		String sql;
		sql = "Select ITEMTYPE, ITEM_UNIT_WEIGHT, UNIT_PRICE FROM RECYCLEITEM;";
		return sql;
	}

	public String createSqlForTransactionAmount(String rcmid, String itemType,
			double transweight, double transamount) {
		String sql;
		sql = "Insert into TRANSACTION (rcmid, itemType, transaction_weight, transaction_amount, transaction_coupon) VALUES('"
				+ rcmid
				+ "','"
				+ itemType
				+ "',"
				+ transweight
				+ ","
				+ transamount + "," + 0 + ");";
		return sql;
	}

	public String createSqlForTransactionCoupon(String rcmid, String itemType,
			double transweight, double transcoupon) {
		String sql;
		sql = "Insert into TRANSACTION (rcmid, itemType, transaction_weight, transaction_amount, transaction_coupon) VALUES('"
				+ rcmid
				+ "','"
				+ itemType
				+ "',"
				+ transweight
				+ ","
				+ 0
				+ ","
				+ transcoupon + ");";
		return sql;
	}

	public String createSqlForCurrentStatus(String rcmId) {
		String sql = "SELECT STATUS FROM RCMRECYCLE WHERE RCMID = " + rcmId;
		System.out.println(sql);
		return sql;
	}

	public String createSqlForMonthlyTrasnsaction(String rcmId) {
		String sql = "SELECT COUNT(TRANSACTION_DATE) AS MONTHLY_TRANSACTION FROM TRANSACTION WHERE RCMID ="
				+ rcmId
				+ "  AND TRANSACTION_DATE between DATE_ADD(curdate(), INTERVAL -30 day) and Curdate() GROUP BY RCMID;";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForFetchRMOSids(String userName) {
		String sql = "SELECT RMOSID FROM RMOS_USER_MAPPING WHERE USERNAME = '"
				+ userName + "'";
		System.out.println(sql);
		return sql;
	}

	public String createSqlTogetRmosIdbyRcmId(String rcmId) {
		String sql = "SELECT RMOSID FROM RCMRECYCLE WHERE RCMID = " + rcmId;
		System.out.println(sql);
		return sql;
	}

	public String createSqlForEventList() {
		String sql = "SELECT ID,RCMID,EVENT,DETAILS FROM EVENTS";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForDeleteEventById(int idToDelete) {
		String sql = "DELETE FROM EVENTS WHERE ID =" + idToDelete + ";";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForSetCoupon(String rcmId, double creditCoupon) {
		String sql = "UPDATE RCMRECYCLE SET AVAILABLE_COUPON=" + creditCoupon
				+ " WHERE RCMID =" + rcmId + ";";
		System.out.println(sql);
		return sql;
	}
}
