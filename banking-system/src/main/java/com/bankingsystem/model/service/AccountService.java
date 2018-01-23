package com.bankingsystem.model.service;

import com.bankingsystem.model.dto.Account;

public interface AccountService {
	public void transfer(Account from, Account to, int amout);
	public void deposit(int id, double amount);
	public Account getAccount(int id);
}
