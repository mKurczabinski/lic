package com.event.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.models.Event;
import com.event.models.User;
import com.event.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public List<Event> getAll() {
		return eventRepository.listOfAll();
	}
	
	@Override
	public List<Event> getCity(String miasto){
		return eventRepository.getCity(miasto);
	}


	
}
