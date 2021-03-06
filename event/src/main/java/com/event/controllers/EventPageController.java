package com.event.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.event.models.Event;
import com.event.models.User;
import com.event.services.EventService;
import com.event.services.FollowEventService;
import com.event.services.UserService;

@Controller
@RequestMapping("/event/**")
public class EventPageController {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	FollowEventService followService;
	

	@RequestMapping("/event/eventPage/{eventId}")
	public String EventInfo(@PathVariable(value = "eventId", required = false) String eventId,Model model,User u,HttpSession session) {
		u= (User) session.getAttribute("user");
		
		Event event = eventService.findEventById(Integer.parseInt(eventId));
		model.addAttribute("event", event);
		
		List<User> userList = userService.listFollowEventUser(event.getId());
		model.addAttribute("userList", userList);
		
		
		return "eventPage";
	}
	
}
