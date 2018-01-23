package com.bankingsystem.model.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AccountPOJO implements Serializable {
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AccountPOJO [id=" + id + ", name=" + name + ", branch=" + branch + ", balance=" + balance + ", type="
				+ type + "]";
	}

	public AccountPOJO(int id, String name, String branch, int balance, String type) {
		super();
		this.id = id;
		this.name = name;
		this.branch = branch;
		this.balance = balance;
		this.type = type;
	}

	public AccountPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}

	static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String branch;
	private int balance;
	private String type;

}
