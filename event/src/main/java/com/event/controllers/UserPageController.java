package com.event.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.event.interfaces.dto.SearchParams;
import com.event.models.Friends;
import com.event.models.User;
import com.event.services.FriendsService;
import com.event.services.UserService;

@Controller
@RequestMapping("/User/**")
public class UserPageController {

	@Autowired
	UserService userService;

	@Autowired
	FriendsService friendsService;
	

	@RequestMapping("/")
	public String searchUser(Model model, User user, SearchParams searchParams, HttpSession session) {

		SearchParams userToSearchParam = new SearchParams();
		model.addAttribute("userToSearchParam", userToSearchParam);

		String userEmail = searchParams.getEmail();

		User searchUser = new User();
		
		if(session.getAttribute("ListOfUser") !=null) {
			List<User> friendsList;
			friendsList = (List<User>) session.getAttribute("ListOfUser");
			
			model.addAttribute("friendList",friendsList);
			model.addAttribute("invite",true);
			session.setAttribute("ListOfUser", null);
		}
		
		if (session.getAttribute("searchUserFilter") != null) {
			String filtrToSearchUser = (String) session.getAttribute("searchUserFilter");
			searchUser = userService.getUser(filtrToSearchUser);
			model.addAttribute("searchUserFilter", searchUser);
			// session.setAttribute("searchUserFilter", null);
		} else
			return "userPage";

		
			
			
		return "userPage";
	}

	@RequestMapping("User/searchUser")
	public String SearchUser(HttpSession session, Friends friend, User user, SearchParams userSearchParams) {

		session.setAttribute("searchUserFilter", userSearchParams.getEmail());

		user = (User) session.getAttribute("user");
		user.getEmail();

		friend.setUserId(user.getId());
		User userToInvite = new User();
		userToInvite = (userService.getUser(userSearchParams.getEmail()));
		friend.setFriendId(userToInvite.getId());
		friend.setSendInvite(true);

		session.setAttribute("userToInvite", friend);

		return "redirect:User";

	}

	@RequestMapping("User/inviteUser")
	public String InviteUser(HttpSession session) {

		Friends friendToInvite = (Friends) session.getAttribute("userToInvite");
		friendsService.saveFriend(friendToInvite);

		return "redirect:User";
	}

	@RequestMapping("User/invite")
	public String InviteView(HttpSession session, User user, Model model) {
		
		user = (User)session.getAttribute("user");
		
		List<User> friendsList;
		
		friendsList = userService.getfriendsInvites(user.getId());
		
		
		session.setAttribute("ListOfUser", friendsList);
		return "redirect:User";
	}

}
