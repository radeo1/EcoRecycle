package com.scu.actions;

import java.util.ArrayList;
import java.util.List;

import com.project.EcoRe.RCMRecycle;
import com.scu.logic.Validation;

public class RCMAction {

	RMOSManager rmosManager = new RMOSManager();

	public String addRCM(String location, String rmosId) {
		boolean isValid = Validation.isNotNullorEmplty(location);
		if (isValid) {
			RCMRecycle rcm = new RCMRecycle();
			rcm.setLocation(location);
			rcm.setRmosId(rmosId);
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

	public String refillRCM(String rcmId, String amountStr ) {
		boolean isValidRcmId = Validation.isNotNullorEmplty(rcmId);
		boolean isValidNumer = Validation.isNumber(amountStr);
		if (isValidRcmId && isValidNumer ) {
			int amount = Integer.parseInt(amountStr);
			return rmosManager.setFunds(rcmId, amount);
		} else {
			return "Please provide a vaild RCM ID with Valid Amount";
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

	public List<String> getAllRcmId(String rmosId) {
		List<String> rcmIds = rmosManager.getAllRcmId(rmosId);
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
			return rmosManager.monthlyTrasnsaction(rcmId);
		} else {
			return "Please provide a vaild RCM ID";
		}
	}

	public String getLastEmptied(String rcmId) {
		boolean isValid = Validation.isNotNullorEmplty(rcmId);
		if (isValid) {
			return rmosManager.getLastEmptied(rcmId);
		} else {
			return "Please provide a vaild RCM ID";
		}
	}

	public String getCashDebitedPerMonth(String rcmId) {
		boolean isValid = Validation.isNotNullorEmplty(rcmId);
		if (isValid) {
			return rmosManager.getCashDebitedPerMonth(rcmId);
		} else {
			return "Please provide a vaild RCM ID";
		}
	}

	public String getWeightPerMonth(String rcmId) {
		boolean isValid = Validation.isNotNullorEmplty(rcmId);
		if (isValid) {
			return rmosManager.getWeightPerMonth(rcmId);
		} else {
			return "Please provide a vaild RCM ID";
		}
	}

	public String getCurrItemsRecycled(String rcmId) {
		boolean isValid = Validation.isNotNullorEmplty(rcmId);
		if (isValid) {
			return rmosManager.getCurrItemsRecycled(rcmId);
		} else {
			return "Please provide a vaild RCM ID";
		}
	}

	public String getItemsRecycledByMonth(String rcmId) {
		boolean isValid = Validation.isNotNullorEmplty(rcmId);
		if (isValid) {
			return rmosManager.getItemsRecycledByMonth(rcmId);
		} else {
			return "Please provide a vaild RCM ID";
		}
	}

	public String getCurrentStaus(String rcmId) {
		boolean isValid = Validation.isNotNullorEmplty(rcmId);
		if (isValid) {
			return rmosManager.getCurrentStatus(rcmId);
		} else {
			return "Please provide a vaild RCM ID";
		}	}
}
