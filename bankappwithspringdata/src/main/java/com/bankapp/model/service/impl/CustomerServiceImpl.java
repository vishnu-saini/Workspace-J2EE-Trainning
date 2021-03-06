package com.bankapp.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bankapp.model.dao.CustomerDao;
import com.bankapp.model.dto.Customer;
import com.bankapp.model.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;

	@Autowired
	public CustomerServiceImpl(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@Override
	public Customer find(int id) {
		return customerDao.findOne(id);
	}

}
