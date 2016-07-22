/**
 * @author Rachana Deolikar
 * @version 1.0
 * this class implements the methods used for backend implementation 
 * 
 */

package com.project.EcoRe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import com.project.logic.BackendLogic;
import com.project.logic.Validation;

public class RCMRecycle extends Observable {

	private String rcmId;
	private String rmosId;
	private String location;
	private String status;					
	private double currentWeight;
	private double transactionWeight;
	private double availableCash;
	private double availableCoupon;
	private Date lastEmptied;
	private RecycleItem item;

	private BackendLogic logic = new BackendLogic();

    /**
     * default constructor
     */
    public RCMRecycle() {
		super();
	}

    /**
     * parameterized constructor
     *
     * @param rcmId
     * @param location
     */
    public RCMRecycle(String rcmId, String location) {

		this.rcmId = rcmId;
		this.location = location;
	}

    /**
     * this method gets the RCM id
     *
     * @return String
     */
    public String getRcmId() {
		return rcmId;
	}

    /**
     * this method sets the RCM id
     *
     * @param rcmId
     */
    public void setRcmId(String rcmId) {
		this.rcmId = rcmId;
	}

     /**
     * this method gets the RMOS id
     *
     * @return String
     */
    public String getRmosId() {
		return rmosId;
	}

     /**
     * this method sets the RMOS id
     *
     * @param rmosId
     */
    public void setRmosId(String rmosId) {
		this.rmosId = rmosId;
	}

    /**
     * this method gets the location for RCM
     * 
     * @return String
     */
    public String getLocation() {
		return location;
	}

    /**
     * this method sets the location for RCM
     *
     * @param location
     */
    public void setLocation(String location) {
		this.location = location;
	}

    /**
     * this method gets the status of a RCM
     *
     * @return String
     */
    public String getStatus() {
		return status;
	}

    /**
     * this method sets the status of a RCM
     *
     * @param status
     */
    public void setStatus(String status) {
		this.status = status;
	}

    /**
     * this method gets the current value of weight of a RCM
     *
     * @return double
     */
    public double getCurrentWeight() {
		return currentWeight;
	}

    /**
     * this method sets the current value of weight of a RCM
     *
     * @param currentWeight
     */
    public void setCurrentWeight(double currentWeight) {
		this.currentWeight = currentWeight;
	}

    /**
     * this method gets the value of the weight recycled 
     * in a transaction
     *
     * @return double
     */
    public double getTransactionWeight() {
		return transactionWeight;
	}

    /**
     * this method sets the value of the weight recycled 
     * in a transaction
     *
     * @param transactionWeight
     */
    public void setTransactionWeight(double transactionWeight) {
		this.transactionWeight = transactionWeight;
	}

    /**
     * this method gets the value of cash available in RCM
     *
     * @return double
     */
    public double getAvailableCash() {
		return availableCash;
	}

    /**
     * this method sets the value of cash available in RCM
     *
     * @param availableCash
     */
    public void setAvailableCash(double availableCash) {
		this.availableCash = availableCash;
	}

    /**
     * this method gets the value of coupon available in RCM
     *
     * @return double
     */
    public double getAvailableCoupon() {
		return availableCoupon;
	}

    /**
     * this method sets the value of coupon available in RCM
     *
     * @param availableCoupon
     */
    public void setAvailableCoupon(double availableCoupon) {
		this.availableCoupon = availableCoupon;
	}

    /**
     * this method gets the date the RCM was last emptied
     *
     * @return Date
     */
    public Date getLastEmptied() {
		return lastEmptied;
	}

    /**
     * this method sets the date the RCM was last emptied
     *
     * @param lastEmptied
     */
    public void setLastEmptied(Date lastEmptied) {
		this.lastEmptied = lastEmptied;
	}

    /**
     * this method fetches the item
     *
     * @return RecycleItem
     */
    public RecycleItem getItem() {
		return item;
	}

    /**
     * this method fetches the item
     *
     * @param item
     */
    public void setItem(RecycleItem item) {
		this.item = item;
	}

    /**
     * this method updates the details of transaction 
     * for a recycled item 
     *
     * @param rcmid
     * @param itemType
     * @param transweight
     * @param transamount
     * @param dateStr
     * @return String
     */
    public String transactionamount(String rcmid, String itemType,
			double transweight, double transamount,String dateStr) {

		String msg = updateRCMCash(rcmid, transweight, transamount);
		System.out.println(msg);
		if (!Validation.isNotNullorEmplty(msg)) {
                    boolean addItem = true;
			if (addItem){
                                logic.addTransactionAmount(rcmid, itemType, transweight, transamount,dateStr);
				return "Your Transaction is successful";
                                
                                
			}
			else
			{
				return "Your Transaction is unsuccessful";
			}
		} else {
			return msg;
		}
	}

	/**
	 * This method this method updates the details of transaction 
         * for a recycled item 
	 *  Added new Logic for updating RCMRecycle table based on Funds Availability
	 * @param rcmid
	 * @param itemType
	 * @param transweight
	 * @param transcoupon
     * @param dateStr
	 * @return String
	 */
	public String transactioncoupon(String rcmid, String itemType,
			double transweight, double transcoupon,String dateStr) {

		String msg = updateRCMCoupon(rcmid, transweight, transcoupon);
		System.out.println(msg);
		if (!Validation.isNotNullorEmplty(msg)) {
			boolean addItem = true;
			if (addItem){
                            logic.addTransactionCoupon(rcmid, itemType,transweight, transcoupon,dateStr);
				return "Your Transaction is successful";
			}
			else
			{
				return "Your Transaction is unsuccessful";
			}
		} else {
			return msg;
		}
	}

	// Start : Added by Pragati
	private String updateRCMCash(String rcmid, double transweight,
			double transamount) {
		String statusIn = logic.getCurrentStatus(rcmid);
		String weightIn = logic.getWeight(rcmid);
		String amountIn = logic.getCash(rcmid);
		double currentWeight=Double.parseDouble(weightIn);
		double weight = Constant.CAPACITY-currentWeight;//avilableweight
		double cash = Double.parseDouble(amountIn);
		if (weight > transweight &&  statusIn.equals("ACTIVE")) {
			if (cash > transamount) {
				updateRCMWeight(rcmid, currentWeight + transweight);
				updateRCMCash(rcmid, cash - transamount);
			} else {
				return "Not having enough Cash availabe.";

			}
		} else {
			if (!statusIn.equals("ACTIVE"))
				return "RCM is Deactivated";
			else
				return "Not having enough weight space availabe.";
		}
		return "";
	}

	private String updateRCMCoupon(String rcmid, double transweight,
			double transcoupon) {
		String statusIn = logic.getCurrentStatus(rcmid);
		String weightIn = logic.getWeight(rcmid);
		String couponIn = logic.getCoupon(rcmid);
		double currentWeight=Double.parseDouble(weightIn);
		double weight = Constant.CAPACITY-currentWeight;
		double coupon = Double.parseDouble(couponIn);
		if (weight > transweight &&  statusIn.equals("ACTIVE")) {
			if (coupon > transcoupon) {
				updateRCMWeight(rcmid, currentWeight + transweight);
				updateRCMCoupon(rcmid, coupon - transcoupon);
			} else {
				return "Not having enough Cash availabe.";
			}
		} else {
			if (!statusIn.equals("ACTIVE"))
				return "RCM is Deactivated";
			else
				return "Not having enough weight space availabe.";
		}
		return "";
	}

	private boolean updateRCMCash(String rcmid, double d) {
		return logic.setFund(rcmid, d);
	}

	private boolean updateRCMCoupon(String rcmid, double d) {
		return logic.setCoupon(rcmid, d);
	}

    /**
     *
     * this method updates the weight in 
     * RCM after every transaction
     * @param rcmid
     * @param weight
     * @return
     */
    public boolean updateRCMWeight(String rcmid, double weight) {
		return logic.updateRCMWeight(rcmid, weight);
	}
	// End : Added by Pragati

    /**
     * this method returns the list of all RCMs
     *
     * @return List
     */
    public List<String> getRCMList() {
		
		List<String> l=new ArrayList<String>();
		l=logic.getActiveRCMList();
		return l;
	}
}
