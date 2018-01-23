package com.bankapp.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bankapp.model.dto.BankUser;

public interface UserDao extends JpaRepository<BankUser, Integer> {

	@Query("FROM BankUser u where u.username = :username")
	List<BankUser> findByUserName(@Param("username") String username);

}