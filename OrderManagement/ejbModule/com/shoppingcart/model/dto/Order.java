package com.shoppingcart.model.dto;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {

	private static final long serialVersionUID = -4329693187554974565L;
	private int orderID;
	private Customer customer;
	private List<Product> products;

	public Order(int orderID, Customer customer, List<Product> products) {
		super();
		this.orderID = orderID;
		this.customer = customer;
		this.products = products;
	}

	public Order() {
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", customer=" + customer + ", products=" + products + "]";
	}


}
