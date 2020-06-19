package com.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.event.models.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{
	
	@Query(value="select * from City where city_name like concat(:cityName,'%')", nativeQuery = true)
	public List<City> findByCityName(@Param("cityName") String cityName);
	
	@Query(value="select id from City where city_name like :cityName", nativeQuery = true)
	public int getCityId(@Param("cityName") String cityName);
	
	
	@Modifying
	@Transactional
	@Query(value="INSERT INTO city(city_name) values(:cityName) ", nativeQuery = true)
	public void saveCity(@Param("cityName") String cityName);
	
	
	@Query(value="select * from city order by city_name", nativeQuery = true)
	public List<City> getAll();
}
