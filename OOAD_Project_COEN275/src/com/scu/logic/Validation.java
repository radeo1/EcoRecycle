package com.scu.logic;

public class Validation {

	public static boolean isNotNullorEmplty(String str) {
		if(str == null || "".equals(str)){
			return false;
		}else{
			return true;
		}
	}
	
	public static boolean isValidRcmId(String rcmId){
		return false;
	}
}
