package com.event.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.event.models.Event;
import com.event.models.User;
import com.event.repository.EventRepository;
import com.event.services.EventService;
import com.event.services.UserService;

@Controller

public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	EventService eventService;
	
	@RequestMapping("/logowanie")
	public String login(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}

	@RequestMapping("/loginUser")
	public String findUser(User user, Model model,HttpSession session) {

		User checkUser = userService.findUserByEmail(user.getEmail());
		
		if (checkUser != null) {
			
			if (passwordEncoder.matches(user.getPassword(), checkUser.getPassword())) {
				model.addAttribute("user", user);
				
				session.setAttribute("user", checkUser);
				
				User u = (User)session.getAttribute("user");
				
				//show();
				
				return "mainPage";
			}else {

				return "login";
		} 
		}else
		return "login";
	}
	
	
	
//	public void show() {
//		 String pawels = eventService.getCity();
//		 String h = "k";
//	}
}
