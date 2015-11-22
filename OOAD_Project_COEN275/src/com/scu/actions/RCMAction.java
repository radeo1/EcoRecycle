package com.scu.actions;

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
}