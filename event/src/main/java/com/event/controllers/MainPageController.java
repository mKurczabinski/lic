package com.event.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.interfaces.dto.SearchParams;
import com.event.models.Event;
import com.event.models.User;
import com.event.services.EventService;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/**")
public class MainPageController {

	@Autowired
	EventService eventService;

	final int LIMIT = 3;
	
	@RequestMapping("/mainPage")
	public String showMain(Model model, Event event, HttpSession session) {
		User u = (User) session.getAttribute("user");

		Event eventToAdd = new Event();
		model.addAttribute("eventToAdd", eventToAdd);

		SearchParams searchParams = new SearchParams();
		model.addAttribute("eventToSearch", searchParams);
		

		//event.getMiasto();

		//List<String> sList = eventService.listOfCity();

		if (session.getAttribute("searchFilter") != null) {
			String filtr = (String) session.getAttribute("searchFilter");
			model.addAttribute("searchFilter",filtr);
			model.addAttribute("eventList", getListsOfEvent(filtr,model));
			session.setAttribute("searchFilter", null);
		}
		else
			model.addAttribute("eventList", getListsOfEvent(null,model));

		return "mainPage";

	}

	@RequestMapping(value = "addEvent", method = RequestMethod.POST)
	public String addEvent(Event event, HttpSession session) throws ParseException {

		User addUser = (User) session.getAttribute("user");

		// event.setUserId(addUser.getId()); //pobiera id usera który dodaje event, musi
		// być zalogowany

		// --------------------------CAŁA ZMIANA DATY ZE STRINGA NA DATE I Z DATE NA
		// CALENDAR -- BRAK OBECNIE INNEGO SPOPOBU----------------------------------------------------------------------------------------------------
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm");
		String dateInString = event.getDate();
		Date date = formatter.parse(dateInString);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// -------------------------------------------------------------------------------------------------------------------------------------

		event.setEventTime(calendar);
		eventService.addEvent(event);

		return "redirect:mainPage";

	}

	@RequestMapping(value = "searchEvent", method = RequestMethod.POST)
	public String searchEvent(Event event, Model model, HttpSession session, SearchParams searchParams) {


		session.setAttribute("searchFilter", searchParams.getCity());
		return "redirect:mainPage";
	}

	private List<Event> getListsOfEvent(String filter,Model model) {
		
		int offset = 0;
		if (filter == null || filter.equals("")) {
			model.addAttribute("EventOffSet",offset);
			
			return eventService.getAll(offset, LIMIT);
		} else {
			return eventService.getCity(filter);
		}

	}
	
	@RequestMapping(value = "userPage")
	public String GoToUserPage() {
		return "redirect:User";
	}
	
	@RequestMapping("dynLoad")
	public void getDynamicLoad(HttpServletRequest request, HttpServletResponse response, int offset) throws IOException{
		   response.addHeader("Content-Type", "text/html; charset=utf-8");
	        PrintWriter pw = response.getWriter();
	        String result = "";
	        List<Event> lista = eventService.getAll(offset, LIMIT);
	        for (Event e : lista) {
	        	result += "	<div class='eventDiv'>" + e.getMiasto() + "</div>";
	        }
	        pw.write(result);
	}
}
