package com.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.event.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{
	
	@Query("Select miasto from Event")
	  public List<String> listOfCity();

	@Query(value = "select distinct e.* from event e join follow_event fe on e.id = fe.event_id where e.user_id =:userId or fe.user_id =:userId order by e.id desc limit :limit offset :off", nativeQuery = true)
	public List<Event> listOfAll(@Param("userId") int userId, @Param("off") int off, @Param("limit")int limit);
	
	
	@Query(value = "Select * from Event where miasto like concat(:miasto,'%')", nativeQuery = true)
	public List<Event> getCity(@Param("miasto") String miasto);

	@Modifying
	@Transactional
	@Query(value="UPDATE Event set follows = follows + 1 WHERE id = :eventId", nativeQuery = true)
	public void incrementFollower(@Param("eventId") int eventId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE Event set follows = follows - 1 WHERE id = :eventId", nativeQuery = true)
	public void decrementFollower(@Param("eventId") int eventId);
	
	@Query(value = "select * from event e where miasto =:param or name =:param order by create_time desc limit 5", nativeQuery = true)
	public List<Event> searchEventByParam(@Param("param") String param);

	@Query(value = "Select * from Event where id =:eventId", nativeQuery = true)
	public Event findEventById(@Param("eventId") int eventId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE Event set image_source =:imageSource WHERE id = :eventId", nativeQuery = true)
	public void addImageSource(String imageSource, int eventId);
	
}

