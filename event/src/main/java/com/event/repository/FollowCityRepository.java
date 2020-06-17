package com.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.event.models.FollowCity;

@Repository
public interface FollowCityRepository extends JpaRepository<FollowCity, Integer>{
	
	@Modifying
	@Transactional
	@Query(value = "insert into follow_city (user_id, city_id) values (:userId, :cityId)", nativeQuery=true)
	public void followCity(@Param("userId") int userId, @Param("cityId") int cityId);
}
