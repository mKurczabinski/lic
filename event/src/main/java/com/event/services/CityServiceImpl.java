package com.event.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.event.models.City;
import com.event.repository.CityRepository;


@Service
public class CityServiceImpl implements CityService{
	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public List<City> findByCityName(String cityName) {
		return cityRepository.findByCityName(cityName);
	}


}
