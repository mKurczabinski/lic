package com.event.services;

import java.util.List;

import com.event.models.Event;
import com.event.models.User;

public interface EventService {
	//public List<String> getCity();
	
	
	public List<Event> getAll();
	
	public List<Event> getCity(String miasto); 
	
	public List<String> listOfCity();

	public void addEvent(Event event);

}
