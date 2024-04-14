package com.xandra.eventscalendar.events;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateEventDTO {
	
	@NotBlank
	private String event;
	
	@NotNull
	private Date startDate;
	
	@NotNull
	private Date endDate;

	public String getEvent() {
		return event;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	@Override
	public String toString() {
		return "CreateEventDTO [event=" + event + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	//add Locations and Labels here when they are added to Entity
	
	
}
