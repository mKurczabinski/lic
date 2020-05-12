package com.event.services;

import java.util.List;

import com.event.models.Friends;

public interface FriendsService {
	public void saveFriend(Friends friend);
	
	public void deleteInvite(Friends friend);
	
	public void updateFriendSendInvite(int userId, int friendId);
	
	public Friends getFriend(int userId, int friendId);
	
}
