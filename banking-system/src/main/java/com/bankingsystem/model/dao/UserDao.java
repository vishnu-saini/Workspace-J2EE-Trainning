package com.bankingsystem.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bankingsystem.model.dto.User;

public interface UserDao extends JpaRepository<User, Integer> {

	@Query("FROM User u where u.username = :username")
	List<User> findByUserName(@Param("username") String username);

}