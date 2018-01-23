package com.bankapp.model.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "account")
@XmlRootElement
public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "branch_id")
	private Branch branch;
	private int balance;
	private String type;
	@OneToMany(mappedBy = "fromAccount")
	private List<TransactionRecord> debitTransactions;
	@OneToMany(mappedBy = "toAccount")
	private List<TransactionRecord> creditTransactions;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int id, Customer customer, Branch branch, int balance, String type) {
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

	public List<TransactionRecord> getDebitTransactions() {
		return debitTransactions;
	}

	public void setDebitTransactions(List<TransactionRecord> debitTransactions) {
		this.debitTransactions = debitTransactions;
	}

	public List<TransactionRecord> getCreditTransactions() {
		return creditTransactions;
	}

	public void setCreditTransactions(List<TransactionRecord> creditTransactions) {
		this.creditTransactions = creditTransactions;
	}

}
