package com.project.actions;

import com.project.EcoRe.Constant;
import com.project.logic.BackendLogic;

public class LoginAction {
	private String username;
	private String password;
	private BackendLogic logic = new BackendLogic();
	private String message = "";

	public String execute() {
		if (!"".equals(username) && username != null && !"".equals(password)
				&& password != null) {
			boolean isValidUser = logic.validateUser(username, password);
			if (isValidUser) {
				message = message + "Log In Successful !";
				Constant.SUCESS_LOGIN = true;//if matches wid input
				return Constant.SUCCESS;
			} else {
				Constant.SUCESS_LOGIN = false;

				message = message
						+ "Please Insert valid Userid and/or Password.";
				return Constant.ERROR;
			}
		} else {
			Constant.SUCESS_LOGIN = false;
			message = message + "Please Insert required fields.";
			return Constant.ERROR;
		}
	}

	public String logout() throws Exception {
		Constant.SUCESS_LOGIN = false;

		return Constant.SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
