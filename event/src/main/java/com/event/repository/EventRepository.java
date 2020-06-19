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
public interface EventRepository extends JpaRepository<Event, Integer> {

	@Query(value = "Select c.city_name as miasto from Event e join city c on e.city_id = c.id", nativeQuery = true)
	public List<String> listOfCity();

	@Query(value = " select distinct e.* from city c"
			       + " left join follow_city fc on c.id = fc.city_id "
			       + " join event e on c.id = e.city_id"
			       + " left join follow_event fe on e.id = fe.event_id "
			       + "where fc.user_id =:userId "
			       + "  and e.event_range = 'public' "
			       + "   or e.user_id =:userId "
			       + "   or fe.user_id =:userId "
			       + "order by e.id desc "
			       + "limit :limit offset :off", nativeQuery = true)
	public List<Event> listOfAll(@Param("userId") int userId, @Param("off") int off, @Param("limit") int limit);

	
	@Query(value = "Select e.*, c.city_name as miasto from Event e join city c on e.city_id = c.id where c.city_name like concat(:miasto,'%')", nativeQuery = true)
	public List<Event> getCity(@Param("miasto") String miasto);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Event set follows = follows + 1 WHERE id = :eventId", nativeQuery = true)
	public void incrementFollower(@Param("eventId") int eventId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Event set follows = follows - 1 WHERE id = :eventId", nativeQuery = true)
	public void decrementFollower(@Param("eventId") int eventId);

	@Query(value = "Select e.*, c.city_name as miasto from Event e join city c on e.city_id = c.id where c.city_name =:param or name =:param order by create_time desc limit 5", nativeQuery = true)
	public List<Event> searchEventByParam(@Param("param") String param);

	@Query(value = "Select e.*, c.city_name as miasto from Event e join city c on e.city_id = c.id where e.id =:eventId", nativeQuery = true)
	public Event findEventById(@Param("eventId") int eventId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Event set image_source =:imageSource WHERE id = :eventId", nativeQuery = true)
	public void addImageSource(String imageSource, int eventId);

	@Modifying
	@Transactional
	@Query(value = "insert into event (name, user_id, event_range, create_time, event_time, image_source, follows, city_id) "
			+ "values (:e.name, :e.userId,:e.eventRange,:e.createTime,:e.eventTime,:e.imageSource,:e.follows,:e.cityId, )", nativeQuery = true)
	public void saveEvent(Event e);
	
}
