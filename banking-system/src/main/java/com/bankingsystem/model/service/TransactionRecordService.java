package com.bankingsystem.model.service;

import java.util.List;

import com.bankingsystem.model.dto.Transaction;

public interface TransactionRecordService {

	void addTransaction(Transaction transaction);
	
	public List<Transaction> getAllTransactions();
}
