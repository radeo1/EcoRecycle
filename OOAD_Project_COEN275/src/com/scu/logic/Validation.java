package com.scu.logic;

public class Validation {
	static BackendLogic logic = new BackendLogic();

	public static boolean isNotNullorEmplty(String str) {
		if (str == null || "".equals(str)) {
			return false;
		} else { 
			return true;
		}
	}

	public static boolean isValidRcmId(String rcmId) {

		return logic.isvalidRcmId(rcmId);
	}
	
	public static boolean isNumber(String s) {
		s = s.trim();
		if (!isNotNullorEmplty(s)) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isValidMapping(String rcmId, String rmosId){
		
		return logic.isMappingRcmIdRmosId(rcmId,rcmId);
		
	}
}
