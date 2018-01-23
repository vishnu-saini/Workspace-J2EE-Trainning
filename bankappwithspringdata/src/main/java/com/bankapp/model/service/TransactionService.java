package com.bankapp.model.service;

import java.util.List;

import com.bankapp.model.dto.Transaction;

public interface TransactionService {

	void addTransaction(Transaction transaction);
	
	public List<Transaction> getAllTransactions();
}
