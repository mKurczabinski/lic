package com.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.event.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{
	
	@Query("Select miasto from Event")
	  public List<String> listOfCity();

	@Query(value = "Select * from Event order by id desc limit :limit offset :off ", nativeQuery = true)
	public List<Event> listOfAll( @Param("off") int off, @Param("limit")int limit);
	
	
	@Query(value = "Select * from Event where miasto like concat(:miasto,'%')", nativeQuery = true)
	public List<Event> getCity(@Param("miasto") String miasto);
}

