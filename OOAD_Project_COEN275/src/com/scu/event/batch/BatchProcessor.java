package com.scu.event.batch;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import com.project.EcoRe.Constant;

public class BatchProcessor {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);//only one thread created for schedule
	            //ScheduledExecutorService scheduler will take a runable thread and execute that periodically
	ExecuteBatch executer = new ExecuteBatch();// this object will execute event handling process

	/** This method executes events periodically
	 * No i/o parameter
	 */
	public void executeEventsPeriodically() 
	{
		final Runnable eventFinder = new Runnable() 
		{
			public void run() {
				System.out
						.println("Logic to find event and execute those events strats");
				executer.executeBatch();//will do whtever is written in batch
			}
		};
		final ScheduledFuture<?> eventHandler = scheduler.scheduleAtFixedRate(
				eventFinder, Constant.SCHEDULE_AT_FIXEDRATE,
				Constant.SCHEDULE_AT_FIXEDRATE, SECONDS);//creating 
		scheduler.schedule(new Runnable() {
			public void run() {
				eventHandler.cancel(true);
			}
		}, 60*60, SECONDS);//scheduler will run 100 times
	}
}
