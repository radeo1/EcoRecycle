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
	public void addRCM (RCMRecycle rcm)
	{
		boolean isAdded = logic.addRCM(rcm);
	}
	public void activateRCM (int rcmNum)
	{
		boolean isUpdated = logic.activateRCM(rcmNum);
	}
	public void removeRCM (int rcmNum)
	{
		boolean isRemoved = logic.removeRCM(rcmNum);
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
}
