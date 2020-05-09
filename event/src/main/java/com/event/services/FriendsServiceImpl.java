package com.event.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.models.Friends;
import com.event.models.User;
import com.event.repository.FriendsRepository;

@Service
public class FriendsServiceImpl implements FriendsService{

	@Autowired
	private FriendsRepository friendsRepository;
	
	@Override
	public void saveFriend(Friends friend) {
		friendsRepository.save(friend);
	}



}
