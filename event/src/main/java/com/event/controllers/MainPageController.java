package com.event.controllers;

import java.util.List;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.event.interfaces.dto.SearchParams;
import com.event.models.City;
import com.event.models.Event;
import com.event.models.FollowEvent;
import com.event.models.User;
import com.event.repository.FollowEventRepository;
import com.event.services.CityService;
import com.event.services.EventService;
import com.event.services.FollowEventService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
public class MainPageController implements HandlerExceptionResolver {

	@Autowired
	EventService eventService;

	@Autowired
	FollowEventService followEventService;

	@Autowired
	CityService cityServie;

	final int LIMIT = 3;

	@RequestMapping("/mainPage")
	public String showMain(Model model, Event event, HttpSession session) {
		User u = (User) session.getAttribute("user");

		Event eventToAdd = new Event();
		model.addAttribute("eventToAdd", eventToAdd);

		SearchParams searchParams = new SearchParams();
		model.addAttribute("eventToSearch", searchParams);

		model.addAttribute("user", u);

		// model.addAttribute("FollowEventList",);

		// event.getMiasto();

		// List<String> sList = eventService.listOfCity();
		List<Event> listaDoAktualizacji = null;
		List<City> cityList = cityServie.getAll();
		if (session.getAttribute("searchFilter") != null) {
			String filtr = (String) session.getAttribute("searchFilter");
			model.addAttribute("searchFilter", filtr);
			listaDoAktualizacji = getListsOfEvent(filtr, model, session);
			setLista(listaDoAktualizacji, u, cityList);
			model.addAttribute("eventList", listaDoAktualizacji);

			session.setAttribute("searchFilter", null);
		} else {
			listaDoAktualizacji = getListsOfEvent(null, model, session);
			setLista(listaDoAktualizacji, u, cityList);
			model.addAttribute("eventList", listaDoAktualizacji);
		}
		model.addAttribute("followList", session.getAttribute("followList"));
		List<FollowEvent> fel = (List<FollowEvent>) session.getAttribute("followList");

		return "mainPage";

	}

	@RequestMapping(value = "addEvent", method = RequestMethod.POST)
	public String addEvent(Event event, HttpSession session, @RequestPart(name = "file") MultipartFile file)
			throws ParseException, IOException {

		User addUser = (User) session.getAttribute("user");

		File uploadDirectory = new File("uploads");
		uploadDirectory.mkdirs();

		event.setUserId(addUser.getId());

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm");
		String dateInString = event.getDate();
		Date date = formatter.parse(dateInString);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		event.setEventTime(calendar);

		try {

			int cityId = cityServie.getCityId(event.getMiasto());

			event.setCityId(cityId);
		} catch (Exception e) {
			cityServie.saveCity(event.getMiasto());

			event.setCityId(cityServie.getCityId(event.getMiasto()));
		}
		eventService.addEvent(event);

		try {
			String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

			File oFile = new File("uploads/" + event.getId() + extension);
			OutputStream os = new FileOutputStream(oFile);
			InputStream inputStream = file.getInputStream();
			IOUtils.copy(inputStream, os);
			os.close();
			inputStream.close();

			String imageSource = event.getId() + extension;
			eventService.addImageSource(imageSource, event.getId());

		} catch (IOException e) {
			// TODO: handle exception
		}
		return "redirect:mainPage";

	}

	@RequestMapping(value = "searchEvent", method = RequestMethod.POST)
	public String searchEvent(Event event, Model model, HttpSession session, SearchParams searchParams) {

		session.setAttribute("searchFilter", searchParams.getCity());
		return "redirect:mainPage";
	}

	private List<Event> getListsOfEvent(String filter, Model model, HttpSession session) {
		User u = (User) session.getAttribute("user");
		List<Integer> followIdList = new ArrayList<Integer>();
		List<Event> eventList;

		int offset = 0;
		if (filter == null || filter.equals("")) {
			model.addAttribute("EventOffSet", offset);
			eventList = eventService.getAll(u.getId(), offset, LIMIT);
			/*
			 * for (Event ev : eventList) { followIdList.add(ev.geventListetId()); }
			 */

			return eventList;
		} else {
			eventList = eventService.getCity(filter);
			/*
			 * for (Event ev : eventList) { followIdList.add(ev.getId()); }
			 * session.setAttribute("followList", getFollow(u.getId(), followIdList));
			 */

			return eventList;
		}

	}

	@RequestMapping(value = "userPage")
	public String GoToUserPage() {
		return "redirect:User";
	}

	@RequestMapping("dynLoad")
	public void getDynamicLoad(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			int offset, User u) throws IOException {

		u = (User) session.getAttribute("user");
		response.addHeader("Content-Type", "text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		String result = "";
		List<Event> lista = eventService.getAll(u.getId(), offset, LIMIT);
		List<City> cityList = cityServie.getAll();

		setLista(lista, u, cityList);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd / HH:mm");

		
		  for (Event e : lista) {
		  
		  result += "	<div class=\"eventDiv\">" +
		  "							<div class=\"eventDivInfo\">" +
		  "								<ul class=\"list-group\">" +
		  "									<li class=\"list-group-item\">" +
		  e.getName() + " || " + e.getMiasto() + " ||" +
		  "										" + e.getEventRange() + " || " +
		  sdf.format(e.getEventTime().getTime()) + " " +
		  "										|| " + e.getFollows() +
		  "<span class=\"glyphicon glyphicon-user\"" +
		  "										style=\"color: grey\"> </span> <a"
		  + "										href=\"/user/followEvent/" +
		  e.getId() + "\" id=\"followButt " + e.getId() + "\"" +
		  "										class=\"btn btn-default\" style=\"float: right\">";
		  if (e.getFollowed() > 0) {
			  result +="przestań obserwować";
		  } else {
			  result +="obserwuj";
		  }
		  result +=
		  "									</a>" +
		  "									</li>" +
		  "								</ul>" +
		  "							</div>" +
		  "							<img style=\"cursor: pointer\" id=\"imageDiv\" alt=\"\""
		  + "								src=\"uploads/" + e.getImageSource() +
		  "\"" +
		  "								onClick=\"window.location.href='event/eventPage/"
		  + e.getId() + "'\">" + "						</div>"; }
		 
		pw.write(result);
	}

	@RequestMapping(value = "/user/followEvent/{followEventId}", method = RequestMethod.GET)
	public String followEvent(@PathVariable(value = "followEventId", required = false) String followEventId,
			HttpSession session) {

		User user = (User) session.getAttribute("user");

		int eventId = Integer.valueOf(followEventId);

		FollowEvent fe = followEventService.getFollow(user.getId(), eventId);

		if (fe == null) {
			followEventService.followEvent(user.getId(), eventId);
			eventService.incrementFollower(eventId);
		} else {
			followEventService.deleteFollow(user.getId(), eventId);
			eventService.decrementFollower(eventId);
		}
		return "redirect:/mainPage";
	}

	@RequestMapping(value = "/filterEvent/")
	public String filterEvent(@RequestParam(name = "city", required = false) String city,
			@RequestParam(name = "startDate", required = false) String startDate,
			@RequestParam(name = "endDate", required = false) String endDate, User user, HttpSession session)
			throws ParseException {

		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		if (startDate != "") {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date sDate = sdf.parse(startDate);
			startCal.setTime(sDate);
		}
		if (endDate != "") {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date eDate = sdf.parse(endDate);
			endCal.setTime(eDate);
		}

		return "redirect:/mainPage";
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<FollowEvent> getFollow(int userId, List<Integer> eventId) {

		List<FollowEvent> followList = new ArrayList<FollowEvent>();
		for (Integer ev : eventId) {
			if (followEventService.countFollow(userId, ev) != 0) {
				followList.add(followEventService.getFollow(userId, ev));

			}
		}
		return followList;
	}

	private void setLista(List<Event> lista, User u, List<City> cityList) {
		for (Event e : lista) {
			if (followEventService.countFollow(u.getId(), e.getId()) > 0) {
				e.setFollowed(1);
			} else {
				e.setFollowed(0);
			}

			for (City c : cityList) {
				if (e.getCityId() == c.getId()) {
					e.setMiasto(c.getCityName());
				}
			}
		}
	}

}
