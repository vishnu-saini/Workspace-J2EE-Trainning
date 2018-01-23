package com.bankapp.model.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankapp.model.dao.AccountDao;
import com.bankapp.model.dto.Account;
import com.bankapp.model.dto.TransactionRecord;
import com.bankapp.model.service.AccountService;
import com.bankapp.model.service.BankFacade;
import com.bankapp.model.service.EmailService;
import com.bankapp.model.service.TransactionRecordService;

@Service
@Transactional
public class BankFacadeImp implements BankFacade {

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionRecordService transactionService;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AccountDao accountDao;

	@Override
	@Secured("Banker")
	public void transfer(int from, int to, int amount,String systemIp) {

		Account fromAccount=accountDao.findOne(from);
		Account toAccount=accountDao.findOne(to);
		accountService.transfer(fromAccount, toAccount, amount);		
		transactionService.addTransaction(new TransactionRecord(fromAccount, toAccount, new Date(), "Transfer", amount, "successfull", systemIp));
		emailService.sendEmail("a", "b");

		//throw new NullPointerException();

	}


}
