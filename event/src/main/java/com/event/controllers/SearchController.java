package com.event.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.event.models.City;
import com.event.models.Event;
import com.event.models.User;
import com.event.services.CityService;
import com.event.services.EventService;
import com.event.services.FollowCityService;

@Controller
@RequestMapping("/search/**")
public class SearchController {

	@Autowired
	CityService cityService;
	
	@Autowired 
	FollowCityService followCityService;
	
	@RequestMapping("/")
	public String Result() {
		
		return "search";
	}
	
	@RequestMapping("/search/navSearch/{param}")
	public String navSearch(@PathVariable(value = "param", required = false) String param, Model model){
		
		List<City> cityList;
		cityList = cityService.findByCityName(param);
		
		model.addAttribute("cityList" , cityList);
		
		
		return "search";
	}
	
	@RequestMapping("/search/followCity/{cityId}")
	public String followCity(@PathVariable(value = "cityId", required = false) String cityId, User user, HttpSession session){
		
		user = (User) session.getAttribute("user");
		int cityIdInt = Integer.parseInt(cityId);
		
		followCityService.followCity(user.getId(), cityIdInt);
		
		return "search";
	}
}

