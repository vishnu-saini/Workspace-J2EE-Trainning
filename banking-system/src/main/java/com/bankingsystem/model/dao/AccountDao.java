package com.bankingsystem.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankingsystem.model.dto.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {
}