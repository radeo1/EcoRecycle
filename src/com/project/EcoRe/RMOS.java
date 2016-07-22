/**
 * @author Pragati Shrivastava
 * @version 1.0
 */
package com.project.EcoRe;

public class RMOS 
{
	private String userName = "admin";
	private String passWord = "admin123";	
		
	private String rmosId;
	private String radiusIncharge;
	
    /**
     *
     */
    public RMOS() 
	{}
		
    /**
     *
     */
    public void displayItemList()
	{
		
	}
	
    /**
     * this method gets the value of RMOS id
     * 
     * @return String
     */
    public String getRmosId() {
		return rmosId;
	}

    /**
     * his method sets the value of RMOS id
     * @param rmosId
     */
    public void setRmosId(String rmosId) {
		this.rmosId = rmosId;
	}

    /**
     * this method gets the radius for a location
     *
     * @return String
     */
    public String getRadiusIncharge() {
		return radiusIncharge;
	}

    /**
     * this method sets the radius for a location
     *
     * @param radiusIncharge
     */
    public void setRadiusIncharge(String radiusIncharge) {
		this.radiusIncharge = radiusIncharge;
	}

    /**
     * this method checks the username and password for login
     *
     * @param userName
     * @param passWord
     * @return boolean
     */
    public boolean logIn(String userName, String passWord){
		boolean result = false;
		
		// judge user name and password
		if((this.userName.equals(userName)) && (this.passWord.equals(passWord))){
			result = true;
		}
		return result;
	}
}
