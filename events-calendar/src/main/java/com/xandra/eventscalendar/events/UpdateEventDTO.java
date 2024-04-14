package com.xandra.eventscalendar.events;

import java.util.Date;

import jakarta.validation.constraints.Pattern;

public class UpdateEventDTO {
	
	@Pattern(regexp = "^(?=\\S).*$", message = "Event cannot be empty")
	private String event;
	
	private Date startDate;
	
	private Date endDate;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	//add label and location when added to entity and createdto
	
	
}
