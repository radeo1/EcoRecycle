/**
 * @author Pragati Shrivastava
 * @version 1.0
 */
package com.project.EcoRe;

import java.util.List;

public class RMOS 
{
	private String userName = "admin";
	private String passWord = "admin123";	
		
	private String rmosId;
	private String radiusIncharge;
	private List<RCMRecycle> rcmList;
	private RMOSUsageManager rmosUsage;
	private RMOSManager rcmManager;
	private RCMMonitor rcmMonitor;	

	public RMOS() 
	{}
		
	public void displayItemList()
	{
		
	}
	
	public String getRmosId() {
		return rmosId;
	}
	public void setRmosId(String rmosId) {
		this.rmosId = rmosId;
	}
	public String getRadiusIncharge() {
		return radiusIncharge;
	}
	public void setRadiusIncharge(String radiusIncharge) {
		this.radiusIncharge = radiusIncharge;
	}

	public boolean logIn(String userName, String passWord){
		boolean result = false;
		
		// judge user name and password
		if((this.userName.equals(userName)) && (this.passWord.equals(passWord))){
			result = true;
		}
		return result;
	}
}
