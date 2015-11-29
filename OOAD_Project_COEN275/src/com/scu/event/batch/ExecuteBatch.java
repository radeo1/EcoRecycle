package com.scu.event.batch;

import java.util.List;

import com.project.EcoRe.Constant;
import com.scu.logic.BackendLogic;

public class ExecuteBatch {

	BackendLogic logic =  new BackendLogic();
	public void executeBatch() {
		
		List<Event> eventList = logic.getCurrentEventList();
		
		for(Event event : eventList){
			if(Constant.FILLED100P.equalsIgnoreCase(event.getEvent()) ||Constant.FILLED75P.equalsIgnoreCase(event.getEvent()) ){
				logic.clearRCMWeight(event.getRcmId()+"");
			}
			if(Constant.CASH0P.equalsIgnoreCase(event.getEvent()) ){
				logic.setFund(event.getRcmId()+"", Constant.CREDIT_CASH);
			}
			if(Constant.CASH25P.equalsIgnoreCase(event.getEvent()) ){
				logic.setFund(event.getRcmId()+"", (0.75 * Constant.CREDIT_CASH));
			}
			boolean isDeleted = logic.clearEvent(event.getId());
			if(!isDeleted){
				System.out.println("Need Manual efforts as Event id " + event.getId() +" is not deleted. Please take care.");
			}
		}
	}

}
