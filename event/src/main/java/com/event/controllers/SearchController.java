package com.event.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.event.models.Event;
import com.event.services.EventService;

@Controller
@RequestMapping("/search/**")
public class SearchController {

	@Autowired
	EventService eventService;
	
	@RequestMapping("/")
	public String Result() {
		
		return "search";
	}
	
	@RequestMapping("/search/navSearch/{param}")
	public String navSearch(@PathVariable(value = "param", required = false) String param, Model model){
		
		List<Event> eventList;
		eventList = eventService.searchEventByParam(param);
		
		model.addAttribute("eventList" , eventList);
		
		
		return "search";
	}
	
	
	
}

