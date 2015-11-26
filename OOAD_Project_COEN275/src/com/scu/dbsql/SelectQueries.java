package com.scu.dbsql;

import com.project.EcoRe.RCMRecycle;

public class SelectQueries {

	public String getPasswordByUserName(String userName) {
		String sql = "SELECT PASSWORD FROM USER where USERNAME='" + userName
				+ "';";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForAddRCM(RCMRecycle rcm) 
	{
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
		System.out.println(sql);
		return sql;
	}

	public String createSqlForSetFund(String rcmId, int amount) {
		String sql = "UPDATE RCMRECYCLE SET FUND=" + amount + " WHERE RCMID ="
				+ rcmId + ";";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForsetWeight(String rcmId, int weight) {
		String sql = "UPDATE RCMRECYCLE SET WEIGHT=" + weight
				+ " WHERE RCMID =" + rcmId + ";";
		System.out.println(sql);
		return sql;
	}

	public String createSqlForFetchRMSids() {
		String sql = "SELECT RCMID FROM RCMRECYCLE";
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
		String sql = "SELECT  SUM(TRANSACTION_WEIGHT) AS MONTHLY_ACCUMULATED_WEIGHT FROM RCMRECYCLE WHERE RCMID ="
				+ rcmId +"And Month(Start_Date) = Month(getdate()) AND Month(End_Date) = Month(getdate())";
		System.out.println(sql);
		return sql;
	} 

	public String createSqlForCurrItemsRecycled(String rcmId) {
		String sql = "SELECT AVAILABLE_CASH FROM RCMRECYCLE WHERE RCMID ="
				+ rcmId;
		System.out.println(sql);
		return sql;
	}

	public String createSqlForRecycledByMonth(String rcmId) {
		String sql = "SELECT AVAILABLE_CASH FROM RCMRECYCLE WHERE RCMID ="
				+ rcmId;
		System.out.println(sql);
		return sql;
	}

public String createSqlForMostUsedRCM(){
            String sql;
            sql = "SELECT RCMNUM, RCMID, MONTHLYWEIGHT, LOCATION FROM RcmRecycle ORDER BY SUM(TRANSACTION_WEIGHT) DESC; ";
            return sql;
        }
        
        public String createSqlForAddItem(String itemType, double itemWeightUnit, double unitPrice){
            String sql;
            sql = "INSERT INTO RECYCLEITEM(ITEMTYPE, ITEM_UNIT_WEIGHT,UNIT_PRICE) VALUES ('"+itemType+"',"+itemWeightUnit + ",'"+unitPrice+"'); ";
            return sql;
        }
        
        public String createSqlForDeleteItem(String itemType){
            String sql;
            sql = "Delete from RECYCLEITEM where ITEMTYPE ='" + itemType+"';";
            return sql;
        }
        
        public String createSqlForUpdatePrice(String itemType, double unitPrice){
            String sql;
            sql = "Update RECYCLEITEM set UNIT_PRICE = "+ unitPrice +" where ITEMTYPE ='"+itemType +"';";
            return sql;
        }
        
        public String createSqlForGetItemList(){
            String sql;
            sql = "Select ITEMTYPE, ITEM_UNIT_WEIGHT, UNIT_PRICE FROM RECYCLEITEM;";
            return sql;
        }
}
