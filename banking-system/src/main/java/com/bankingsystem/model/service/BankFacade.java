package com.bankingsystem.model.service;

public interface BankFacade {
	public void transfer(int fromAccount, int toAccount, int amount, String systemIp);
}
