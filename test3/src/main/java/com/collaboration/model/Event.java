package com.collaboration.model;

/*import java.util.Date;*/

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*import com.fasterxml.jackson.annotation.JsonFormat;*/

@Entity
@Table(name="C_Event")
public class Event {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int eventId;
	private String name;
	private String venue;
	private String description;
	/*@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")*/
	private String dateTime;
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	/*public Event()
	{
		Date d= new Date();
		dateTime=d;
	}*/
}
