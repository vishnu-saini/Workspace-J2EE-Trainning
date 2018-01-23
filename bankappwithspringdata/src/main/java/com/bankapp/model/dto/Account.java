package com.bankapp.model.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	@Id @GeneratedValue
	private int id;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name="branch_id")
	private Branch branch;
	private int balance;
	private String type;
	@OneToMany(mappedBy="fromAccount")
	private List<Transaction> debitTransactions;
	@OneToMany(mappedBy="toAccount")
	private List<Transaction> creditTransactions;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int id, Customer customer, Branch branch, int balance, String type ) {
		super();
		this.id = id;
		this.customer = customer;
		this.branch = branch;
		this.balance = balance;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", customer=" + customer + ", branch=" + branch + ", balance=" + balance
				+ ", type=" + type + "]";
	}
	public List<Transaction> getDebitTransactions() {
		return debitTransactions;
	}
	public void setDebitTransactions(List<Transaction> debitTransactions) {
		this.debitTransactions = debitTransactions;
	}
	public List<Transaction> getCreditTransactions() {
		return creditTransactions;
	}
	public void setCreditTransactions(List<Transaction> creditTransactions) {
		this.creditTransactions = creditTransactions;
	}
	
	
}
