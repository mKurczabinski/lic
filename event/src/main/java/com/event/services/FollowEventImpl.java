package com.event.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.repository.FollowEventRepository;

@Service
public class FollowEventImpl implements FollowEventService{
	
	@Autowired
	FollowEventRepository followEventRepo;
	
	@Override
	public void followEvent(int userId, int eventId) {
		followEventRepo.followEvent(userId, eventId);
	}
	
}
