package com.event.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.event.models.User;
import com.event.services.UserService;

@Controller

public class RegisterController {
	@Autowired
	UserService userService;

	@RequestMapping("/rejestracja")
	public String register(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}

	@RequestMapping("addUser")
	public String addUser(User user, Model model) {

		User checkUser = userService.findUserByEmail(user.getEmail());

		if (checkUser == null && user.getEmail()!=null && user.getPassword()!=null) {

			userService.saveUser(user);
			model.addAttribute("userIsInBase", false);
			return "login";
		} else {
			model.addAttribute("userIsInBase", true);
			return "register";
		}
	}

}
