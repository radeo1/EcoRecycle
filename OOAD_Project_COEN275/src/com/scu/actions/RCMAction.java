package com.scu.actions;

import com.project.EcoRe.RCMRecycle;

public class RCMAction {

	private RMOSManager rmosManager = new RMOSManager();

	public String addRCM( String location){
		RCMRecycle rcm = new RCMRecycle();
		rcm.setLocation(location);
		return rmosManager.addRCM(rcm);
	}
}
