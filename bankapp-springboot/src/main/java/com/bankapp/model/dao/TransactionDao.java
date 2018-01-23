package com.bankapp.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.model.dto.TransactionRecord;
@Repository
public interface TransactionDao extends JpaRepository<TransactionRecord, Integer> {
}
