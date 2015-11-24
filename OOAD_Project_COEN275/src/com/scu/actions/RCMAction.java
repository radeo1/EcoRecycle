package com.scu.actions;

import java.util.ArrayList;
import java.util.List;

import com.project.EcoRe.RCMRecycle;
import com.scu.logic.Validation;

public class RCMAction {

	RMOSManager rmosManager = new RMOSManager();

	public String addRCM(String location) {
		boolean isValid = Validation.isNotNullorEmplty(location);
		if (isValid) {
			RCMRecycle rcm = new RCMRecycle();
			rcm.setLocation(location);
			return rmosManager.addRCM(rcm);
		} else {
			return "Please provide a valid location";
		}
	}

	public String removeRCM(String rcmId) {
		boolean isValid = Validation.isNotNullorEmplty(rcmId);
		if (isValid) {
			return rmosManager.removeRCM(rcmId);
		} else {
			return "Please provide a vaild RCM ID";
		}
	}

	public String activateRCM(String rcmId) {
		boolean isValid = Validation.isNotNullorEmplty(rcmId);
		if (isValid) {
			return rmosManager.activateRCM(rcmId);
		} else {
			return "Please provide a vaild RCM ID";
		}
	}

	public String deActivateRCM(String rcmId) {
		boolean isValid = Validation.isNotNullorEmplty(rcmId);
		if (isValid) {
			return rmosManager.deactivateRCM(rcmId);
		} else {
			return "Please provide a vaild RCM ID";
		}
	}

	public String refillRCM(String rcmId, int amount) {
		boolean isValid = Validation.isNotNullorEmplty(rcmId);
		if (isValid) {
			return rmosManager.setFunds(rcmId, amount);
		} else {
			return "Please provide a vaild RCM ID";
		}
		
	}

	public String clearRcmWeight(String rcmId) {
		boolean isValid = Validation.isNotNullorEmplty(rcmId);
		if (isValid) {
			return rmosManager.clearRcmWeight(rcmId);
		} else {
			return "Please provide a vaild RCM ID";
		}
	}

	public List<String> getAllRcmId() {
		List<String> rcmIds = rmosManager.getAllRcmId();
		return rcmIds;
	}

	public String currentWeight(String rcmId) {
		boolean isValid = Validation.isNotNullorEmplty(rcmId);
		if (isValid) {
			return rmosManager.currentWeight(rcmId);
		} else {
			return "Please provide a vaild RCM ID";
		}
	}

	public String getCurrentCash(String rcmId) {
		boolean isValid = Validation.isNotNullorEmplty(rcmId);
		if (isValid) {
			return rmosManager.currentCash(rcmId);
		} else {
			return "Please provide a vaild RCM ID";
		}
	}

	public String getCurrentCoupon(String rcmId) {
		boolean isValid = Validation.isNotNullorEmplty(rcmId);
		if (isValid) {
			return rmosManager.currentCoupon(rcmId);
		} else {
			return "Please provide a vaild RCM ID";
		}
	}

	//#TODO
	public String getMonthlyTransactions(String rcmId) {
		boolean isValid = Validation.isNotNullorEmplty(rcmId);
		if (isValid) {
			return null;//rmosManager.monthlyTrasnsaction(rcmId);
		} else {
			return "Please provide a vaild RCM ID";
		}
	}
}
