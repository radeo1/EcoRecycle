package com.project.EcoRe;

import java.util.Date;
import java.util.Observable;

import com.scu.logic.BackendLogic;
import com.scu.logic.Validation;

public class RCMRecycle extends Observable {

	private String rcmId;
	private String rmosId;
	private String location;
	private String status;
	private String itemType;
	private double currentWeight;
	private double transactionWeight;
	private double availableCash;
	private double availableCoupon;
	private Date lastEmptied;
	private RecycleItem item;

	private BackendLogic logic = new BackendLogic();

	public RCMRecycle() {
		super();
	}

	public RCMRecycle(String rcmId, String location) {

		this.rcmId = rcmId;
		this.location = location;
	}

	public String getRcmId() {
		return rcmId;
	}

	public void setRcmId(String rcmId) {
		this.rcmId = rcmId;
	}

	public String getRmosId() {
		return rmosId;
	}

	public void setRmosId(String rmosId) {
		this.rmosId = rmosId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getCurrentWeight() {
		return currentWeight;
	}

	public void setCurrentWeight(double currentWeight) {
		this.currentWeight = currentWeight;
	}

	public double getTransactionWeight() {
		return transactionWeight;
	}

	public void setTransactionWeight(double transactionWeight) {
		this.transactionWeight = transactionWeight;
	}

	public double getAvailableCash() {
		return availableCash;
	}

	public void setAvailableCash(double availableCash) {
		this.availableCash = availableCash;
	}

	public double getAvailableCoupon() {
		return availableCoupon;
	}

	public void setAvailableCoupon(double availableCoupon) {
		this.availableCoupon = availableCoupon;
	}

	public Date getLastEmptied() {
		return lastEmptied;
	}

	public void setLastEmptied(Date lastEmptied) {
		this.lastEmptied = lastEmptied;
	}

	public RecycleItem getItem() {
		return item;
	}

	public void setItem(RecycleItem item) {
		this.item = item;
	}

	public boolean transactionamount(String rcmid, String itemType,
			double transweight, double transamount) {

		String msg = updateRCMCash(rcmid, transweight, transamount);
		System.out.println(msg);
		if (!Validation.isNotNullorEmplty(msg)) {
			boolean addItem = logic.addTransactionAmount(rcmid, itemType,
					transweight, transamount);
			return addItem;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param rcmid
	 * @param itemType
	 * @param transweight
	 * @param transcoupon
	 * @param transdate
	 * @return
	 */
	public boolean transactioncoupon(String rcmid, String itemType,
			double transweight, double transcoupon) {

		String msg = updateRCMCoupon(rcmid, transweight, transcoupon);
		System.out.println(msg);
		if (!Validation.isNotNullorEmplty(msg)) {
			boolean addItem = logic.addTransactionCoupon(rcmid, itemType,
					transweight, transcoupon);
			return addItem;
		} else {
			return false;
		}
	}

	// Start : Added by Pragati
	private String updateRCMCash(String rcmid, double transweight,
			double transamount) {
		String weightIn = logic.getWeight(rcmid);
		String amountIn = logic.getCash(rcmid);
		double weight = Constant.CAPACITY-Double.parseDouble(weightIn);
		double cash = Double.parseDouble(amountIn);
		if (weight > transweight) {
			if (cash > transamount) {
				updateRCMWeight(rcmid, weight - transweight);
				updateRCMCash(rcmid, cash - transamount);
			} else {
				return "Not having enough Cash availabe.";

			}
		} else {
			return "Not having enough weight space availabe.";
		}
		return "";
	}

	private String updateRCMCoupon(String rcmid, double transweight,
			double transcoupon) {
		String weightIn = logic.getWeight(rcmid);
		String couponIn = logic.getCoupon(rcmid);
		double weight = Constant.CAPACITY-Double.parseDouble(weightIn);
		double coupon = Double.parseDouble(couponIn);
		if (weight > transweight) {
			if (coupon > transcoupon) {
				updateRCMWeight(rcmid, weight - transweight);
				updateRCMCoupon(rcmid, coupon - transcoupon);
			} else {
				return "Not having enough Cash availabe.";
			}
		} else {
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

	public boolean updateRCMWeight(String rcmid, double weight) {
		return logic.updateRCMWeight(rcmid, weight);
	}
	// End : Added by Pragati
}
