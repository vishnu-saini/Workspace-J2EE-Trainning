package com.bankapp.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.model.dto.Account;



@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {
}