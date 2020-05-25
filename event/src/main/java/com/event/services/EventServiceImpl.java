package com.event.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.models.Event;
import com.event.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public List<Event> getAll(int userId, int off, int limit) {
		return eventRepository.listOfAll(userId, off, limit);
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
	
	@Override
	public void incrementFollower(int eventId) {
		eventRepository.incrementFollower(eventId);
	}
	
	@Override
	public void decrementFollower(int eventId) {
		eventRepository.decrementFollower(eventId);
	}
	
	@Override
	public List<Event> searchEventByParam(String param) {
		return eventRepository.searchEventByParam(param);
	}
	
	@Override
	public Event findEventById(int id) {
		return eventRepository.findEventById(id);
	}
	
	@Override
	public void addImageSource(String imageSource, int eventId) {
		eventRepository.addImageSource(imageSource, eventId);
	}
}
