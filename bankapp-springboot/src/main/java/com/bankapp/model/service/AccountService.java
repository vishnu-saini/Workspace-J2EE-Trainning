package com.bankapp.model.service;

import com.bankapp.model.dto.Account;

public interface AccountService {
	public void transfer(Account from, Account to, int amout);
	public void deposit(int id, double amount);
	public Account getAccount(int id);
}
