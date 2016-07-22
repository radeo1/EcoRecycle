/**
 * @author Pragati Shrivastava
 * @version 1.0
 */
package com.project.actions;

import java.util.List;

import com.project.EcoRe.RCMRecycle;
import com.project.logic.BackendLogic;
import com.project.logic.Validation;

public class RMOSManager {
	private BackendLogic logic = new BackendLogic();

    /**
     * this method is to add RCM
     *
     * @param rcm
     * @return String
     */
    public String addRCM(RCMRecycle rcm) {
		return logic.addRCM(rcm);
	}

    /**
     * this method is to activate RCM
     *
     * @param rcmId
     * @param rmosId
     * @return String
     */
    public String activateRCM(String rcmId, String rmosId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			boolean isMapped = Validation.isValidMapping(rcmId, rmosId);
			if (isMapped) {
				boolean isUpdated = logic.activateRCM(rcmId);
				if (isUpdated) {
					return "RCM Successfully Activated";
				} else {
					return "RCM is not Activated, Please try again";
				}
			} else {
				return "RCM ID " + rcmId + " and RMOS ID " + rmosId
						+ " is not a Mapped, Please select correct RMOS Id";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

    /**
     * this method is to deactivate RCM
     *
     * @param rcmId
     * @param rmosId
     * @return String
     */
    public String deactivateRCM(String rcmId, String rmosId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			boolean isMapped = Validation.isValidMapping(rcmId, rmosId);
			if (isMapped) {
				boolean isUpdated = logic.deactivateRCM(rcmId);
				if (isUpdated) {
					return "RCM Successfully Deactivated";
				} else {
					return "RCM is not Deactivated, Please try again";
				}
			} else {
				return "RCM ID " + rcmId + " and RMOS ID " + rmosId
						+ " is not a Mapped, Please select correct RMOS Id";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

    /**
     * this method is to remove RCM
     *
     * @param rcmId
     * @param rmosId
     * @return String
     */
    public String removeRCM(String rcmId, String rmosId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			boolean isMapped = Validation.isValidMapping(rcmId, rmosId);
			if (isMapped) {
				boolean isRemoved = logic.removeRCM(rcmId);
				if (isRemoved) {
					return "RCM Successfully Removed";
				} else {
					return "RCM is not Removed, Please try again";
				}
			} else {
				return "RCM ID " + rcmId + " and RMOS ID " + rmosId//whetger mapped to RMOS
						+ " is not a Mapped, Please select correct RMOS Id";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

    /**
     * this method is to set cash value for a particular RCM
     *
     * @param rcmId
     * @param amount
     * @param rmosId
     * @return String
     */
    public String setFunds(String rcmId, int amount, String rmosId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			boolean isMapped = Validation.isValidMapping(rcmId, rmosId);
			if (isMapped) {
				boolean isAdded = logic.setFund(rcmId, amount);
				if (isAdded) {
					return "RCM Successfully Refilled";
				} else {
					return "RCM is not Refilled, Please try again";
				}
			} else {
				return "RCM ID " + rcmId + " and RMOS ID " + rmosId
						+ " is not a Mapped, Please select correct RMOS Id";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

    /**
     * this method is to set coupon value for a particular RCM
     * 
     * @param rcmId
     * @param amount
     * @param rmosId
     * @return String
     */
    public String setCoupon(String rcmId, int amount, String rmosId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			boolean isMapped = Validation.isValidMapping(rcmId, rmosId);
			if (isMapped) {
				boolean isAdded = logic.setCoupon(rcmId, amount);
				if (isAdded) {
					return "RCM Successfully Refilled";
				} else {
					return "RCM is not Refilled, Please try again";
				}
			} else {
				return "RCM ID " + rcmId + " and RMOS ID " + rmosId
						+ " is not a Mapped, Please select correct RMOS Id";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

    /**
     * this method is to set weight to 0 once RCM is full
     * for a particular RCM
     * 
     * @param rcmId
     * @param rmosId
     * @return String
     */
    public String clearRcmWeight(String rcmId, String rmosId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			boolean isMapped = Validation.isValidMapping(rcmId, rmosId);
			if (isMapped) {
				boolean isRemoved = logic.clearRCMWeight(rcmId);
				if (isRemoved) {
					return "RCM Successfully Cleared";
				} else {
					return "RCM is not Cleared, Please try again";
				}
			} else {
				return "RCM ID " + rcmId + " and RMOS ID " + rmosId
						+ " is not a Mapped, Please select correct RMOS Id";

			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

    /**
     * this method is to get the list of all RCMs in 
     * a RMOS
     * 
     * @param rmosId
     * @return List
     */
    public List<String> getAllRcmId(String rmosId) {
		List<String> rmsids = logic.getAllRmsId(rmosId);
		return rmsids;
	}

    /**
     * this method is to get the current value of weight
     * for a particular RCM
     * 
     * @param rcmId
     * @return String
     */
    public String currentWeight(String rcmId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			String currentWeight = logic.getWeight(rcmId);
			if (Validation.isNotNullorEmplty(currentWeight)) {
				return "RCM Id " + rcmId + " has current weight as "
						+ currentWeight;
			} else {
				return "RCM Id " + rcmId
						+ "current weight is not available, Please try again";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

    /**
     * this method is to get the current value of Coupon available
     * for a particular RCM
     * 
     * @param rcmId
     * @return String
     */
    public String currentCoupon(String rcmId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			String tempCurrent = logic.getCoupon(rcmId);
			if (Validation.isNotNullorEmplty(tempCurrent)) {
				return "RCM Id " + rcmId + " has current coupon as "
						+ tempCurrent;
			} else {
				return "RCM Id " + rcmId
						+ "current Coupon is not available, Please try again";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

   /**
     * this method is to get the current value of Cash available
     * for a particular RCM
     * 
     * @param rcmId
     * @return String
     */
    public String currentCash(String rcmId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			String tempCurrent = logic.getCash(rcmId);
			if (Validation.isNotNullorEmplty(tempCurrent)) {
				return "RCM Id " + rcmId + " has current cash as "
						+ tempCurrent;
			} else {
				return "RCM Id " + rcmId
						+ "current cash is not available, Please try again";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

    /**
     * this method is to get the date the RCM was last emptied 
     * 
     * @param rcmId
     * @return String
     */
    public String getLastEmptied(String rcmId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			String tempCurrent = logic.getLastEmptied(rcmId);
			if (Validation.isNotNullorEmplty(tempCurrent)) {
				return "RCM Id " + rcmId + " has Last Emptied Date as "
						+ tempCurrent;
			} else {
				return "RCM Id "
						+ rcmId
						+ " Last Emptied Date is not available, Please try again";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

    /**
     * this method is to get the total value of Cash debited 
     * in the month for a particular RCM
     * 
     * @param rcmId
     * @return String
     */
    public String getCashDebitedPerMonth(String rcmId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			String tempCurrent = logic.getCashDebitedPerMonth(rcmId);
			if (Validation.isNotNullorEmplty(tempCurrent)) {
				return "RCM Id " + rcmId + " has Cash Debited Per Month as "
						+ tempCurrent;
			} else {
				return "RCM Id "
						+ rcmId
						+ " Cash Debited Per Month is not available, Please try again";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

    /**
     * this method is to get the total weight of 
     * recycled items in the month for a particular RCM
     * 
     * @param rcmId
     * @return String
     */
    public String getWeightPerMonth(String rcmId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			String tempCurrent = logic.getWeightPerMonth(rcmId);
			if (Validation.isNotNullorEmplty(tempCurrent)) {
				return "RCM Id " + rcmId + " has Weight Per Month as "
						+ tempCurrent;
			} else {
				return "RCM Id "
						+ rcmId
						+ " Weight Per Month is not available, Please try again";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

    /**
     * this method returns the number of items recycled
     * in a particular RCM
     * 
     * @param rcmId
     * @return String
     */
    public String getCurrItemsRecycled(String rcmId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			String tempCurrent = logic.getCurrItemsRecycled(rcmId);
			if (Validation.isNotNullorEmplty(tempCurrent)) {
				return "RCM Id " + rcmId + " has Current Items Recycled as "
						+ tempCurrent;
			} else {
				return "RCM Id "
						+ rcmId
						+ " Current Items Recycled is not available, Please try again";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

    /**
     * this method returns the items recycled
     * in a particular RCM an a month
     * 
     * @param rcmId
     * @return String
     */
    public String getItemsRecycledByMonth(String rcmId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			String tempCurrent = logic.getItemsRecycledByMonth(rcmId);
			if (Validation.isNotNullorEmplty(tempCurrent)) {
				return "RCM Id " + rcmId + " has Items Recycled By Month as "
						+ tempCurrent;
			} else {
				return "RCM Id "
						+ rcmId
						+ " Items Recycled By Month is not available, Please try again";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}
    /** 
     * this method returns the current status
     * of a particular RCM
     * 
     * @param rcmId
     * @return String
     */
    public String getCurrentStatus(String rcmId) {

		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			String tempStatus = logic.getCurrentStatus(rcmId);
			if (Validation.isNotNullorEmplty(tempStatus)) {
				return " Current status of RCM Id " + rcmId + " is "
						+ tempStatus;
			} else {
				return "RCM Id " + rcmId
						+ " Current Status not available, Please try again";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}

	}
    /**
     * this method returns the value of monthly transactions
     * made in a particular RCM in a month
     * 
     * @param rcmId
     * @return String
     */
    public String monthlyTrasnsaction(String rcmId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			String temp = logic.monthlyTrasnsaction(rcmId);
			if (Validation.isNotNullorEmplty(temp)) {
				return " The Monthly Transaction for current month for RCM ID "
						+ rcmId + " is " + temp;
			} else {
				return " The Monthly Transection for current month for RCM ID "
						+ rcmId + " is not available, Please try again";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}

    /**
     * this method returns the list of all RMOS
     *
     * @param userName
     * @return List
     */
    public List<String> getAllRmosId(String userName) {
		List<String> rmsids = logic.getAllRmosId(userName);
		return rmsids;
	}

    /**
     * this method fetches the location for a 
     * particular RCM
     *
     * @param rcmId
     * @return
     */
    public String getLocation(String rcmId) {
		boolean isRcmIdPresentInDB = Validation.isValidRcmId(rcmId);
		if (isRcmIdPresentInDB) {
			String tempCurrent = logic.getLocation(rcmId);
			if (Validation.isNotNullorEmplty(tempCurrent)) {
				return "RCM Id " + rcmId + " has Location as "
						+ tempCurrent;
			} else {
				return "RCM Id "
						+ rcmId
						+ " Location is not available, Please try again";
			}
		} else {
			return "RCM ID " + rcmId + " is not a valid RCM Id";
		}
	}
}
