package com.event.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.event.interfaces.dto.SearchParams;
import com.event.models.User;
import com.event.services.UserService;

@Controller
public class UserPageController {

	@Autowired 
	UserService userService;
	
	
	@RequestMapping("/User")
	public String searchUser(Model model,User user,SearchParams searchParams) {
		String userEmail = searchParams.getEmail();
		
		User searchUser = new User();
		
		
		searchUser=userService.getUser(userEmail);
		model.addAttribute("userName", searchUser.getId());
		model.addAttribute("searchUser",searchUser);
		
		return "userPage";
	}
	
}	
