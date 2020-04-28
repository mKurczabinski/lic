package com.event.services;

import com.event.models.User;

public interface UserService {

	public void saveUser(User user);
	
	public User findUserByEmail(String email);
}

