package com.project.EcoRe;

import java.util.List;

import com.project.dbLogic.DBOperations;

public class Bootup {

	private String userName;
	private String password;
	private DBOperations dboperations = new DBOperations();
	
	public void login()
	{
		System.out.println(userName);
		System.out.println(password);
		boolean isSuccessfull = false ; 
		// # TODO Need to add validation logic for user
		if(isSuccessfull)
		{
			loadUserSpecificDashboard();
		
		}
	}

	private void loadUserSpecificDashboard() {
		// TODO Auto-generated method stub
		Admin user=loadUser();
		user.setAssociatedRmos(loadRmos());
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private Admin loadUser() {
		// TODO Need to add logic for db connection and fetch user details
		Admin admin = dboperations.getUserByUserName(userName);
		return admin;
	}
	public List<RMOS> loadRmos()
	{
		List <RMOS> rmosList= dboperations.getRmosByUserName(userName);
		return rmosList;
	}
}
