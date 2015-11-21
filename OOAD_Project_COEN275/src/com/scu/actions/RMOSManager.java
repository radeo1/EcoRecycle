/**
 * @author Pragati Shrivastava
 * @version 1.0
 */
package com.scu.actions;

import com.project.EcoRe.RCMRecycle;
import com.scu.logic.BackendLogic;

public class RMOSManager 
{
	private BackendLogic logic = new BackendLogic();
	public String addRCM (RCMRecycle rcm)
	{
		return logic.addRCM(rcm);
	}
	public void activateRCM (int rcmNum)
	{
		boolean isUpdated = logic.activateRCM(rcmNum);
	}
	public String removeRCM (String rcmNum)
	{
		boolean isRemoved = logic.removeRCM(rcmNum);
		if(isRemoved){
			return "RCM Successfully Removed";
		}else{
			return "RCM is not Removed, Please try again";
		}
	}
	public int activeRCM(int rcmNum)
	{
		int res=0;
		
		return res;
	}
	public void setFunds() {
	}
	public void clearRcmWeight() {
		// TODO Auto-generated method stub
		
	}
	public void getRCMList() {
		// TODO Auto-generated method stub
		
	}
	public int deactiveRCM(int rcmNum) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
