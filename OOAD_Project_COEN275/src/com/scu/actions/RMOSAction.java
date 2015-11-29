package com.scu.actions;

import java.util.List;

public class RMOSAction {

	RMOSManager rmosManager = new RMOSManager();

	public List<String> getAllRmosId(String userName) {
		List<String> rcmIds = rmosManager.getAllRmosId(userName);
		return rcmIds;
	}

}
