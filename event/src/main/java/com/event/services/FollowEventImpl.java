package com.event.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.models.FollowEvent;
import com.event.repository.FollowEventRepository;

@Service
public class FollowEventImpl implements FollowEventService{
	
	@Autowired
	FollowEventRepository followEventRepo;
	
	@Override
	public void followEvent(int userId, int eventId) {
		followEventRepo.followEvent(userId, eventId);
	}
	
	@Override
	public FollowEvent getFollow(int userId, int eventId){
		return followEventRepo.getFollow(userId, eventId);
	}
	
	@Override
	public int countFollow(int userId, int eventId) {
		return followEventRepo.countFollow(userId, eventId);
	}
	
	@Override
	public void deleteFollow(int userId, int eventId) {
		followEventRepo.deleteFolow(userId, eventId);
	}
	
}
