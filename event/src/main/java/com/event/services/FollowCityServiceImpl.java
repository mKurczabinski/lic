package com.event.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.repository.FollowCityRepository;

@Service
public class FollowCityServiceImpl implements FollowCityService{

	@Autowired
	FollowCityRepository followCityRepo;
	
	@Override
	public void followCity(int userId, int cityId) {
		followCityRepo.followCity(userId, cityId);
	}
}
