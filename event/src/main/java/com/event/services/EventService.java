package com.event.services;

import java.util.List;

import com.event.models.Event;

public interface EventService {
	//public List<String> getCity();
	
	
	public List<Event> getAll(int userId, int off, int limit);
	
	public List<Event> getCity(String miasto); 
	
	public List<String> listOfCity();

	public void addEvent(Event event);

	void incrementFollower(int eventId);

	void decrementFollower(int eventId);
	
	public List<Event> searchEventByParam(String param);

}
