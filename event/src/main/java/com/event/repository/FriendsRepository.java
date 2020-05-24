package com.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.event.models.Friends;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Integer> {
	
	@Modifying
	@Transactional
	@Query(value = "update friends set send_invite = false, accept_invite = true where user_id =:userId and friend_id =:friendId", nativeQuery = true)
	public void updateFriendSendInvite(@Param("userId") int userId, @Param("friendId") int friendId);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO friends (user_id, friend_id , send_invite, accept_invite ) VALUES (:userId, :friendId, 0, 1);", nativeQuery = true)
	public void addSecUser(@Param("userId") int userId, @Param("friendId") int friendId);

	@Query(value = "select * from friends f where user_id =:userId and friend_id =:friendId", nativeQuery = true)
	public Friends getFiend(@Param("userId") int userId, @Param("friendId") int friendId);
	
	@Modifying
	@Transactional
	@Query(value = "delete from friends where user_id =:userId and friend_id =:friendId", nativeQuery = true)
	public void deleteFriend(@Param("userId") int userId, @Param("friendId") int friendId);
}
