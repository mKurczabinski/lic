package com.event.services;

import java.util.List;

import com.event.models.Event;

public interface EventService {
	//public List<String> getCity();
	
	
	public List<Event> getAll();
	
	public List<Event> getCity(String miasto); 
	
	
}
