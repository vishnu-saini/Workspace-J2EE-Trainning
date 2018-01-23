package com.bankingsystem.web.formbean;

public class MoneyTransferForm {

	private int fromAccount;
	private int toAccount;
	private int amount;
	private int sysIp;

	public MoneyTransferForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MoneyTransferForm(int fromAccount, int toAccount, int amount, int sysIp) {
		super();
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.sysIp = sysIp;
	}

	public int getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}

	public int getToAccount() {
		return toAccount;
	}

	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getSysIp() {
		return sysIp;
	}

	public void setSysIp(int sysIp) {
		this.sysIp = sysIp;
	}

	@Override
	public String toString() {
		return "BalanceTransfer [fromAccount=" + fromAccount + ", toAccount=" + toAccount + ", amount=" + amount
				+ ", sysIp=" + sysIp + "]";
	}

}
