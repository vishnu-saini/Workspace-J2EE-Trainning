package com.bankingsystem.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankingsystem.model.dto.Transaction;
@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer> {
}
