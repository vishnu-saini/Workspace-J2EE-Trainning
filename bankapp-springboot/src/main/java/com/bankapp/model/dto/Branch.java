package com.bankapp.model.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Branch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int id;
	private String name;
	private String address;
	
	@OneToMany(mappedBy="branch")
	private List<Account> accounts; 
	
	public Branch() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Branch(int id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}


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


	public String getaddress() {
		return address;
	}


	public void setaddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "BankBranch [id=" + id + ", name=" + name + ", address=" + address + "]";
	}


	public List<Account> getAccounts() {
		return accounts;
	}


	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	
	
}
