package com.event.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.event.models.User;
import com.event.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public User getUser(String email) {
		return userRepository.getUser(email);
	}
	
	@Override
	public List<User> getfriendsInvites(int id) {
		return userRepository.getfriendsInvites(id);
	}
	
	@Override
	public List<User> listOfFriends(int userId) {
		return userRepository.listOfFriends(userId);
	}
	
}
