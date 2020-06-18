package com.event.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout/**")
public class LogoutController {

	@RequestMapping("/")
	public String logout(HttpSession session) throws IOException {
		
		session.invalidate();
		
		return "redirect:logowanie";
	}
}
