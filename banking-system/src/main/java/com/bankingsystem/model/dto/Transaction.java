package com.bankingsystem.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="transaction_details")
public class Transaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int id;
	@OneToOne
	@JoinColumn(name="from_account_id")
	private Account fromAccount;
	@OneToOne
	@JoinColumn(name="to_account_id")
	private Account toAccount;
	@Column(name="dot")
	private Date date;
	private String type;
	private int amount;
	private String status;
	@Column(name="sys_ip")
	private String systemIp;
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Transaction(Account fromAccount, Account toAccount, Date date, String type, int amount,
			String status, String systemIp) {
		super();
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.date = date;
		this.type = type;
		this.amount = amount;
		this.status = status;
		this.systemIp = systemIp;
	}
	
	public Transaction(int id, Account fromAccount, Account toAccount, Date date, String type, int amount,
			String status, String systemIp) {
		super();
		this.id = id;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.date = date;
		this.type = type;
		this.amount = amount;
		this.status = status;
		this.systemIp = systemIp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Account getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}
	public Account getToAccount() {
		return toAccount;
	}
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSystemIp() {
		return systemIp;
	}
	public void setSystemIp(String systemIp) {
		this.systemIp = systemIp;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", fromAccount=" + fromAccount + ", toAccount=" + toAccount + ", date=" + date
				+ ", type=" + type + ", amount=" + amount + ", status=" + status + ", systemIp=" + systemIp + "]";
	}
	
	
}
