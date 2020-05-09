package com.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.event.models.Friends;
import com.event.models.User;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Integer>{

	

}
