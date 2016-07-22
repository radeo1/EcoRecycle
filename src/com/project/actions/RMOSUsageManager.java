/**
 * @author Rachana Deolikar
 * @version 1.0
 */

package com.project.actions;

import com.project.logic.BackendLogic;

public class RMOSUsageManager 
{
    private final BackendLogic logic = new BackendLogic();
	
    
    /**
     * this method returns the RCM most used for recycling items
     * 
     * @return String
     */
	public String getMostUsedRCM()
	{
		String mostusedrcm = logic.getMostUsedRCM();
		return mostusedrcm;
	}      
        
}

