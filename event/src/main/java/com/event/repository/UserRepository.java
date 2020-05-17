package com.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.event.models.Event;
import com.event.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);

	@Query(value = "Select * from User where email like concat(:email)", nativeQuery = true)
	public User getUser(@Param("email") String emial);

	@Query(value = "select * from  `user` u where exists (select 1 from friends f where user_id = u.id and send_invite = true and friend_id=:userId) ", nativeQuery = true)
	public List<User> getfriendsInvites(@Param("userId") int id);
	
	@Query(value = "select * from  `user` u left join friends f on u.id = f.friend_id where f.user_id =:userId and f.accept_invite = 1 ", nativeQuery = true)
	public List<User> listOfFriends(@Param("userId") int userId);
	
	

}
