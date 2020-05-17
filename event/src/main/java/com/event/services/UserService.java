package com.event.services;

import java.util.List;

import com.event.models.User;

public interface UserService {

	public void saveUser(User user);
	
	public User findUserByEmail(String email);
	
	public User getUser(String email);
	
	public List<User> getfriendsInvites(int id);

	List<User> listOfFriends(int userId);
}

