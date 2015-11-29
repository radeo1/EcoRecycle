package com.scu.event.batch;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import com.project.EcoRe.Constant;

public class BatchProcessor {
	private final ScheduledExecutorService scheduler = Executors
			.newScheduledThreadPool(1);
	ExecuteBatch executer = new ExecuteBatch();

	/** This method executes events periodically
	 * No i/o parameter
	 */
	public void executeEventsPeriodically() {
		final Runnable eventFinder = new Runnable() {
			public void run() {
				System.out
						.println("Logic to find event and execute those events need to go here");
				executer.executeBatch();
			}
		};
		final ScheduledFuture<?> eventHandler = scheduler.scheduleAtFixedRate(
				eventFinder, Constant.SCHEDULE_AT_FIXEDRATE,
				Constant.SCHEDULE_AT_FIXEDRATE, SECONDS);
		scheduler.schedule(new Runnable() {
			public void run() {
				eventHandler.cancel(true);
			}
		}, 100, SECONDS);
	}
}
