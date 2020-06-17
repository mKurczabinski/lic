package com.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.event.models.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{
	
	@Query(value="select * from City where city_name like concat(:cityName,'%')", nativeQuery = true)
	public List<City> findByCityName(@Param("cityName") String cityName);
	
}
