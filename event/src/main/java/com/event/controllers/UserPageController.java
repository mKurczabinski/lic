package com.event.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.event.interfaces.dto.SearchParams;
import com.event.models.Friends;
import com.event.models.User;
import com.event.services.FriendsService;
import com.event.services.UserService;

@Controller
public class UserPageController {

	@Autowired
	UserService userService;

	@Autowired
	FriendsService friendsService;

	@RequestMapping("/User")
	public String searchUser(Model model, User user, SearchParams searchParams, HttpSession session) {

		SearchParams userToSearchParam = new SearchParams();
		model.addAttribute("userToSearchParam", userToSearchParam);
		
		User u =(User) session.getAttribute("user");
		model.addAttribute("email",u.getEmail());
		String userEmail = searchParams.getEmail();

		if (session.getAttribute("ListOfInvite") != null) {
			List<User> friendsList;
			friendsList = (List<User>) session.getAttribute("ListOfInvite");

			model.addAttribute("friendList", friendsList);
			model.addAttribute("invite", true);
			session.setAttribute("ListOfInvite", null);
		}

		if (session.getAttribute("searchUserFilter") != null) {
			model.addAttribute("search", true);
			User searchUser = (User) session.getAttribute("searchUser"); // pobranie wyszukanego usera
			model.addAttribute("findUser", searchUser);
			model.addAttribute("friendInfo", session.getAttribute("frinedInfo")); // informacje z tabeli Friends
			model.addAttribute("searchUserFilter", session.getAttribute("searchUserFilter")); // wyświetlanie nazwy
																								// użytkownika
			session.setAttribute("searchUserFilter", null); // zerowanie obiektu sesji żeby było wiadomo że już nie
															// wyszukujemy
		} else
			return "userPage";

		return "userPage";
	}

	@RequestMapping("searchUser")
	public String SearchUser(HttpSession session, User searchUser, SearchParams userSearchParams, Friends findFriend) {

		session.setAttribute("searchUserFilter", userSearchParams.getEmail()); // sprawdzenie czy wgl jest robione
																				// wyszukiwanie usera
		User user = (User) session.getAttribute("user");

		searchUser = userService.getUser(userSearchParams.getEmail());

		session.setAttribute("searchUser", searchUser);

		if (searchUser != null) {
			findFriend = friendsService.getFriend(user.getId(), searchUser.getId()); // pobranie z Friends jeśli user ma
																						// go w znajomych
			session.setAttribute("friendInfo", findFriend);
		}
		return "redirect:User";

	}

	// wysyłanie zaprosznia
	@RequestMapping("inviteUser")
	public String InviteUser(HttpSession session, Friends friendToInvite) {

		User user = (User) session.getAttribute("user");
		User userToInvite = (User) session.getAttribute("searchUser");

		friendToInvite.setFriendId(userToInvite.getId());
		friendToInvite.setUserId(user.getId());
		friendToInvite.setSendInvite(true);
		friendToInvite.setAcceptInvite(false);

		friendsService.saveFriend(friendToInvite);
		return "redirect:User";
	}
	
	@RequestMapping("deleteInvite")
	public String deleteInvite(HttpSession session) {
		
		Friends friendToDelete = (Friends) session.getAttribute("friendInfo");
		friendsService.deleteInvite(friendToDelete);
		
		return "redirect:User";
	}

	// lista zaproszeń
	@RequestMapping("invite")
	public String InviteView(HttpSession session, User user, Model model) {

		user = (User) session.getAttribute("user");

		List<User> friendsList;

		friendsList = userService.getfriendsInvites(user.getId());

		session.setAttribute("ListOfInvite", friendsList);
		return "redirect:User";
	}

	@RequestMapping("acceptInvite/{invitedUserId}")
	public String acceptInvite(@PathVariable int invitedUserId,HttpSession session) {
		// userId to Id user od kt
		User user = (User)session.getAttribute("user");
		
		friendsService.updateFriendSendInvite(invitedUserId,user.getId());
		
		return "redirect:User";
	}
}
