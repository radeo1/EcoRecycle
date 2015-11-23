package com.project.EcoRe;

import com.scu.logic.BackendLogic;
import com.scu.logic.Validation;

public class RMOSUsageManager 
{
    private BackendLogic logic = new BackendLogic();
	public void displayStatistics()
	{
		
	}

	public String getMostUsedRCM() {
		// TODO Auto-generated method stub
		String mostusedrcm = logic.getMostUsedRCM();
		return mostusedrcm;
		}
	
	public void getMonthlyUsageGraph() {
		// TODO Auto-generated method stub
		
	}

       
        
}

