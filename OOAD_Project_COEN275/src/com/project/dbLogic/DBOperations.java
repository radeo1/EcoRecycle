/**
 * 
 */
/**
 * @author Pragati Shrivastava
 *
 */
package com.project.dbLogic;

import java.sql.SQLException;
import java.util.List;

import com.project.EcoRe.Admin;
import com.project.EcoRe.RCMRecycle;
import com.project.EcoRe.RMOS;

public class DBOperations 
{
	public Admin getUserByUserName(String userName)
	{
		// fetch user from database
		return null;
	}

	public List<RMOS> getRmosByUserName(String userName) 
	{
		// TODO Auto-generated method stub
		//select * from RMOS where userName is userName
		return null;
	}
	
	public static void addRCM(RCMRecycle rcm) throws SQLException 
	{
		
	}
	
	public static void removeRCM(int rcmNum) throws SQLException 
	{
		
	}
	
	public static void activateRCM(int rcmNum) throws SQLException 
	{
		
	}
	
	public static void setFunds() throws SQLException
	{

		
	}
	public static void getRCMList() throws SQLException
	{

		
	}
	public static void clearRCM() throws SQLException
	{

		
	}
}

