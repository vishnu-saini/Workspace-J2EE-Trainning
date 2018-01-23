package com.bankingsystem.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bankingsystem.model.dao.TransactionDao;
import com.bankingsystem.model.dto.Transaction;
import com.bankingsystem.model.service.TransactionRecordService;
import com.bankingsystem.model.service.aspects.Loggable;

@Service
@Transactional
public class TransactionRecordServiceImp implements TransactionRecordService {

	@Autowired
	private TransactionDao transactionDao;
	
	@Override
	public void addTransaction(Transaction transaction) {
		transactionDao.saveAndFlush(transaction);
	}
	@Override
	public List<Transaction> getAllTransactions() {
		return transactionDao.findAll();
	}

}
