package com.event.services;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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

	public Event findEventById(int id);

	void addImageSource(String imageSource, int eventId);

}
