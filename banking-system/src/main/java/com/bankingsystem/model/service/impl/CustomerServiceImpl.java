package com.bankingsystem.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bankingsystem.model.dao.CustomerDao;
import com.bankingsystem.model.dto.Customer;
import com.bankingsystem.model.service.CustomerService;

@Transactional
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
