package com.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.event.models.FollowEvent;

@Repository
public interface FollowEventRepository extends JpaRepository<FollowEvent, Integer>{

	@Modifying
	@Transactional
	@Query(value = "insert into follow_event (user_id, event_id) values	(:userId, :eventId)", nativeQuery=true)
	public void followEvent(@Param("userId") int userId, @Param("eventId") int eventId);
	
	@Query(value="select * from follow_event where user_id =:userId and event_id =:eventId", nativeQuery = true)
	public FollowEvent getFollow(@Param("userId") int userId, @Param("eventId") int eventId);
	
	@Query(value="select count(*) from follow_event where user_id =:userId and event_id =:eventId",nativeQuery = true)
	public int countFollow(@Param("userId") int userId, @Param("eventId") int eventId);
}
