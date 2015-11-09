package com.project.EcoRe;

import java.util.List;

public class Admin {

	private String userName;
	private String password;
	private List<RMOS> associatedRmos;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<RMOS> getAssociatedRmos() {
		return associatedRmos;
	}
	public void setAssociatedRmos(List<RMOS> associatedRmos) {
		this.associatedRmos = associatedRmos;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
