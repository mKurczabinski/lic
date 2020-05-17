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
	
	@Override
	public void deleteInvite(Friends friend) {
		friendsRepository.delete(friend);
	}
	@Override
	public Friends getFriend(int userId, int friendId) {
		return friendsRepository.getFiend(userId, friendId);
	}
	
	@Override
	public void updateFriendSendInvite(int userId, int friendId) {
		friendsRepository.updateFriendSendInvite(userId, friendId);
	}

	@Override
	public void addSecUser(int userId, int friendId) {
		friendsRepository.addSecUser(userId, friendId);
	}
	
	@Override
	public void deleteFriend(int userId, int friendId) {
		friendsRepository.deleteFriend(userId, friendId);
	}


}
