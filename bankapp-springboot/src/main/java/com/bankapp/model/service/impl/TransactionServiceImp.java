package com.bankapp.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bankapp.model.dao.TransactionDao;
import com.bankapp.model.dto.TransactionRecord;
import com.bankapp.model.service.TransactionService;

@Service
@Transactional
public class TransactionServiceImp implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void addTransaction(TransactionRecord transactionRecord) {
		transactionDao.saveAndFlush(transactionRecord);
	}

	@Override
	public List<TransactionRecord> getAllTransactions() {
		return transactionDao.findAll();
	}

}
