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

	@Override
	public int getCityId(String cityName) {
		return cityRepository.getCityId(cityName);
	}

	@Override
	public void saveCity(String cityName) {
		cityRepository.saveCity(cityName);
	}

	@Override
	public List<City> getAll() {
		return cityRepository.getAll();
	}

	

}
