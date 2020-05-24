package com.event.services;


import com.event.models.FollowEvent;

public interface FollowEventService {

	public void followEvent(int userId, int eventId);

	int countFollow(int userId, int eventId);

	FollowEvent getFollow(int userId, int eventId);

	void deleteFollow(int userId, int eventId);

	
}
