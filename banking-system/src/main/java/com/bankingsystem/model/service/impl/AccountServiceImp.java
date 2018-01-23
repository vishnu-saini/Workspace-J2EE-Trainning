package com.bankingsystem.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankingsystem.model.dao.AccountDao;
import com.bankingsystem.model.dto.Account;
import com.bankingsystem.model.service.AccountService;
import com.bankingsystem.model.service.aspects.Loggable;

@Service
@Transactional
public class AccountServiceImp implements AccountService {

	private AccountDao accountDao;

	@Autowired
	public AccountServiceImp(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void transfer(Account fromAccount, Account toAccount, int amount) {
			fromAccount.setBalance(fromAccount.getBalance()-amount);
			toAccount.setBalance(toAccount.getBalance()+amount);
			accountDao.saveAndFlush(fromAccount);
			accountDao.saveAndFlush(toAccount);
	}

	@Override
	public void deposit(int id, double amount) {

	}
	@Loggable
	@Override
	public Account getAccount(int id) {
		return accountDao.getOne(id);
	}

}
