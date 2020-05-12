package com.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.event.models.Event;
import com.event.models.Friends;
import com.event.models.User;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Integer> {

	@Query(value = "update friends set send_invite = false, accept_invite = true where user_id =:userId and friend_id =:friendId", nativeQuery = true)
	public void updateFriendSendInvite(@Param("userId") int userId, @Param("friendId") int friendId);

	@Query(value = "select * from friends f where user_id =:userId and friend_id =:friendId", nativeQuery = true)
	public Friends getFiend(@Param("userId") int userId, @Param("friendId") int friendId);

}
