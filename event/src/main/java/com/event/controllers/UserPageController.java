package com.event.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.event.interfaces.dto.SearchParams;
import com.event.models.Friends;
import com.event.models.User;
import com.event.services.FriendsService;
import com.event.services.UserService;

@Controller
@RequestMapping("/user/**")
public class UserPageController {

	@Autowired
	UserService userService;

	@Autowired
	FriendsService friendsService;

	@RequestMapping("/")
	public String searchUser(Model model, User user, SearchParams searchParams, HttpSession session) {

		SearchParams userToSearchParam = new SearchParams();
		model.addAttribute("userToSearchParam", userToSearchParam);

		User u = (User) session.getAttribute("user");
		model.addAttribute("email", u.getEmail());
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
			user = (User) session.getAttribute("user");
			model.addAttribute("findUser", searchUser);
			model.addAttribute("user", user);
			model.addAttribute("friendInfo", session.getAttribute("frinedInfo")); // informacje z tabeli Friends
			model.addAttribute("searchUserFilter", session.getAttribute("searchUserFilter")); // wyświetlanie nazwy
																								// użytkownika
			session.setAttribute("searchUserFilter", null); // zerowanie obiektu sesji żeby było wiadomo że już nie
															// wyszukujemy
		}

		if (session.getAttribute("listOfFriends") != null) {
			model.addAttribute("listOfFriends", session.getAttribute("listOfFriends"));
			session.setAttribute("listOfFriends", null);
		} else
			return "userPage";

		return "userPage";
	}

	@RequestMapping("/searchUser")
	public String SearchUser(HttpSession session, User searchUser, SearchParams userSearchParams, Friends findFriend) {

		session.setAttribute("searchUserFilter", userSearchParams.getEmail()); // sprawdzenie czy wgl jest robione
																				// wyszukiwanie usera
		User user = (User) session.getAttribute("user");

		searchUser = userService.getUser(userSearchParams.getEmail());
		session.setAttribute("searchUser", searchUser);

		if (searchUser != null) {
			findFriend = friendsService.getFriend(user.getId(), searchUser.getId()); // pobranie z Friends jeśli user ma
																						// go w znajomych lub są wysłane
																						// zaproszenia
			session.setAttribute("friendInfo", findFriend);
		}
		return "redirect:/user";

	}

	// wysyłanie zaprosznia
	@RequestMapping("/inviteUser")
	public String InviteUser(HttpSession session, Friends friendToInvite) {

		User user = (User) session.getAttribute("user");
		User userToInvite = (User) session.getAttribute("searchUser");

		friendToInvite.setFriendId(userToInvite.getId());
		friendToInvite.setUserId(user.getId());
		friendToInvite.setSendInvite(true);
		friendToInvite.setAcceptInvite(false);
		friendsService.saveFriend(friendToInvite);

		Friends inviteFriend = friendsService.getFriend(userToInvite.getId(), user.getId());
		Friends u = friendsService.getFriend(user.getId(), userToInvite.getId());

		if (u != null && inviteFriend != null) {
			if (inviteFriend.isSendInvite() && u.isSendInvite()) {
				friendsService.updateFriendSendInvite(u.getUserId(), inviteFriend.getUserId());
				friendsService.updateFriendSendInvite(inviteFriend.getUserId(), u.getUserId());
			}
		}
		return "redirect:/user";
	}

	@RequestMapping("/deleteInvite")
	public String deleteInvite(HttpSession session) {

		Friends friendToDelete = (Friends) session.getAttribute("friendInfo");
		friendsService.deleteInvite(friendToDelete);

		return "redirect:/user";
	}

	// lista zaproszeń
	@RequestMapping("/invite")
	public String InviteView(HttpSession session, User user, Model model) {

		user = (User) session.getAttribute("user");

		List<User> friendsList;

		friendsList = userService.getfriendsInvites(user.getId());

		session.setAttribute("ListOfInvite", friendsList);
		return "redirect:/user";
	}

	@RequestMapping(value = "/user/acceptInvite/{invitedUserId}", method = RequestMethod.GET)
	public String acceptInvite(@PathVariable(value = "invitedUserId", required = false) String invitedUserId,
			HttpSession session) {
		// userId to Id user od kt
		User user = (User) session.getAttribute("user");

		int intInvite = Integer.valueOf(invitedUserId);

		if (friendsService.getFriend(user.getId(), intInvite) != null) {
			friendsService.updateFriendSendInvite(user.getId(), intInvite);
		} else {
			friendsService.addSecUser(user.getId(), intInvite);
		}

		friendsService.updateFriendSendInvite(intInvite, user.getId());

		return "redirect:/user";
	}

	@RequestMapping("/friends")
	public String listFriends(User user, HttpSession session) {
		user = (User) session.getAttribute("user");

		List<User> listOfFriends;
		listOfFriends = userService.listOfFriends(user.getId());
		session.setAttribute("listOfFriends", listOfFriends);

		return "redirect:/user";
	}

	@RequestMapping(value = "/user/deleteFriend/{delFriendId}", method = RequestMethod.GET)
	public String deleteFriend(@PathVariable(value = "delFriendId", required = false) String delFriendId, User user,
			HttpSession session) {
		user = (User) session.getAttribute("user");

		int friendId = Integer.valueOf(delFriendId);

		friendsService.deleteFriend(user.getId(), friendId);
		friendsService.deleteFriend(friendId, user.getId());
		return "redirect:/user";
	}

	@RequestMapping("/delSearchFriend")
	public String delSearchFriend(User user, HttpSession session) {
		user = (User) session.getAttribute("user");

		Friends friendToDelete = (Friends) session.getAttribute("friendInfo");

		friendsService.deleteFriend(user.getId(), friendToDelete.getFriendId());
		friendsService.deleteFriend(friendToDelete.getFriendId(), user.getId());

		return "redirect:/user";
	}

}
