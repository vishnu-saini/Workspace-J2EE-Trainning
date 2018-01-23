package com.bankapp.model.service;

import java.util.List;

import com.bankapp.model.dto.TransactionRecord;

public interface TransactionService {

	void addTransaction(TransactionRecord transactionRecord);
	
	public List<TransactionRecord> getAllTransactions();
}
