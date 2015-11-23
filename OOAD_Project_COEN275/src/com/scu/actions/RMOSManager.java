/**
 * @author Pragati Shrivastava
 * @version 1.0
 */
package com.scu.actions;

import java.util.List;

import com.project.EcoRe.RCMRecycle;
import com.scu.logic.BackendLogic;
import com.scu.logic.Validation;

public class RMOSManager {
	private BackendLogic logic = new BackendLogic();

	public String addRCM(RCMRecycle rcm) {
		return logic.addRCM(rcm);
	}

	public String activateRCM(String rcmId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			boolean isUpdated = logic.activateRCM(rcmId);
			if (isUpdated) {
				return "RCM Successfully Activated";
			} else {
				return "RCM is not Activated, Please try again";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

	public String deactivateRCM(String rcmId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			boolean isUpdated = logic.deactivateRCM(rcmId);
			if (isUpdated) {
				return "RCM Successfully Deactivated";
			} else {
				return "RCM is not Deactivated, Please try again";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

	public String removeRCM(String rcmId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			boolean isRemoved = logic.removeRCM(rcmId);
			if (isRemoved) {
				return "RCM Successfully Removed";
			} else {
				return "RCM is not Removed, Please try again";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

	public String setFunds(String rcmId, int amount) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			boolean isRemoved = logic.setFund(rcmId, amount);
			if (isRemoved) {
				return "RCM Successfully Refilled";
			} else {
				return "RCM is not Refilled, Please try again";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}


	public String clearRcmWeight(String rcmId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			boolean isRemoved = logic.clearRCMWeight(rcmId);
			if (isRemoved) {
				return "RCM Successfully Cleared";
			} else { 
				return "RCM is not Cleared, Please try again";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

	public List<String> getAllRcmId() {
		List<String> rmsids = logic.getAllRmsId();
		return rmsids;
	}

	public String currentWeight(String rcmId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			String currentWeight = logic.getWeight(rcmId);
			if (Validation.isNotNullorEmplty(currentWeight)) {
				return "RCM Id "+ rcmId + " has current weight as " +currentWeight;
			} else {
				return "RCM Id "+ rcmId + "current weight is not available, Please try again";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

}
