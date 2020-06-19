package com.event.services;

import java.util.List;

import com.event.models.City;

public interface CityService {

	public List<City> findByCityName(String cityName);
	
	public int getCityId(String cityName);
	
	public void saveCity(String cityName);
	
	public List<City> getAll();
}
