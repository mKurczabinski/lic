package com.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.event.models.Event;
import com.event.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

public User findByEmail(String email);

@Query(value = "Select * from User where email like concat(:email,'%')", nativeQuery = true)
public User getUser(@Param("email") String emial);

}
