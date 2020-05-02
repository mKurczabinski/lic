package com.event.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.event.models.Event;
import com.event.models.User;
import com.event.services.EventService;

@Controller
public class MainPageController {

	@Autowired
	EventService eventService;

	@RequestMapping("/mainPage")
	public String showMain(Model model, Event event, HttpSession session) {
		User u = (User) session.getAttribute("user");
		
		event.getMiasto();
		
		List<Event> listOfCity = eventService.getCity("Bytom");
		
		List<Event> all = eventService.getAll();
		
		//List<Event> listEventm = eventService.getAll();

		model.addAttribute("event", listOfCity);

		// event = eventService.showEventByCity(event.getMiasto());

		// model.addAttribute("event", event);s
		return "mainPage";

	}

}
