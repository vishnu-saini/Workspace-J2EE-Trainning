package com.bankingsystem.model.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankingsystem.model.dao.AccountDao;
import com.bankingsystem.model.dto.Account;
import com.bankingsystem.model.dto.Transaction;
import com.bankingsystem.model.service.AccountService;
import com.bankingsystem.model.service.BankFacade;
import com.bankingsystem.model.service.EmailService;
import com.bankingsystem.model.service.TransactionRecordService;

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
		transactionService.addTransaction(new Transaction(fromAccount, toAccount, new Date(), "Transfer", amount, "successfull", systemIp));
		emailService.sendEmail("a", "b");

		//throw new NullPointerException();

	}


}
