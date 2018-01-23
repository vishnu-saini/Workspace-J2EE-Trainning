package com.bankapp.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bankapp.model.dao.TransactionDao;
import com.bankapp.model.dto.Transaction;
import com.bankapp.model.service.TransactionService;

@Service
@Transactional
public class TransactionServiceImp implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void addTransaction(Transaction transaction) {
		transactionDao.saveAndFlush(transaction);
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return transactionDao.findAll();
	}

}
