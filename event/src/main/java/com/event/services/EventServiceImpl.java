package com.event.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.models.Event;
import com.event.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public List<Event> getAll(int off, int limit) {
		return eventRepository.listOfAll(off, limit);
	}
	
	@Override
	public List<Event> getCity(String miasto){
		return eventRepository.getCity(miasto);
	}
	
	@Override
	public List<String> listOfCity(){
		return eventRepository.listOfCity();
	}


	@Override
	public void addEvent(Event event) {
		eventRepository.save(event);
	}
	
}
