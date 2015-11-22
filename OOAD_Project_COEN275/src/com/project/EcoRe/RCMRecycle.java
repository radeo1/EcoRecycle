package com.project.EcoRe;

import java.util.Date;
import java.util.Observable;

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

	public RCMRecycle(){
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

}