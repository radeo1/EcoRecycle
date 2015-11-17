package com.scu.actions;

import com.project.EcoRe.Constant;
import com.scu.logic.BackendLogic;

public class LoginAction {
	private String username;
	private String userId;
	private String password;
	private BackendLogic logic = new BackendLogic();
	private String message = "";

	public String execute() {
		if (!"".equals(userId) && userId != null && !"".equals(password)
				&& password != null) {
			boolean isValidUser = logic.validateUser(userId, password);
			if (isValidUser) {
				message = message + "Log In Successful !";
				Constant.SUCESS_LOGIN = true;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
