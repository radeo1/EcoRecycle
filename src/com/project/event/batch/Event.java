package com.project.event.batch;

/* Added by Pragati Shrivastava. This is replica of event table in database*/
public class Event {

	private int id;
	private int rcmId;
	private String event;
	private String details;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRcmId() {
		return rcmId;
	}

	public void setRcmId(int rcmId) {
		this.rcmId = rcmId;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
