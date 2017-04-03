package com.collaboration.dao;

import java.util.List;

import com.collaboration.model.Event;

public interface EventDAO {

	public void addEvent(Event event);
	public List<Event> listEvent();
	public void delete(int eventId);
	public Event get(int eventId);
	public void updateEvent(Event event);
	public Event view(int eventId);
}
