package com.project.event.batch;

import java.util.List;

import com.project.EcoRe.Constant;
import com.project.logic.BackendLogic;

/** This will execute the batch in every 30 seconds 
 * @author Pragati Shrivastava
 */
public class ExecuteBatch {

	BackendLogic logic =  new BackendLogic();
	public void executeBatch() //logic to perform batch
	{
		
		List<Event> eventList = logic.getCurrentEventList();
		
		for(Event event : eventList){
			if(Constant.FILLED100P.equalsIgnoreCase(event.getEvent()) ||Constant.FILLED75P.equalsIgnoreCase(event.getEvent()) ){
				logic.clearRCMWeight(event.getRcmId()+"");
			}
			if(Constant.CASH0P.equalsIgnoreCase(event.getEvent()) || Constant.CASH25P.equalsIgnoreCase(event.getEvent())  ){
				logic.setFund(event.getRcmId()+"", Constant.CREDIT_CASH);
			}
			if(Constant.COUPON0P.equalsIgnoreCase(event.getEvent()) || Constant.COUPON25P.equalsIgnoreCase(event.getEvent())  ){
				logic.setCoupon(event.getRcmId()+"", Constant.CREDIT_COUPON);
			}
			boolean isDeleted = logic.clearEvent(event.getId());//deletes row on event table
			if(!isDeleted){
				System.out.println("Need Manual efforts as Event id " + event.getId() +" is not deleted. Please take care.");
			}
		}
	}

}
