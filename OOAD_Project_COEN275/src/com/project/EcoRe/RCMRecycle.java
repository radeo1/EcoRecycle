
package com.project.EcoRe;

import java.util.Date;
import java.util.Observable;


public class RCMRecycle extends Observable
{

	private int    rcmNum;
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
	
	public RCMRecycle(int rcmNum) 
	{
		this.rcmNum = rcmNum;
	}

	public RCMRecycle(int rcmNum, String rcmId, String location) 
	{
		this.rcmNum = rcmNum;
		this.rcmId = rcmId;
		this.location = location;
	}

}