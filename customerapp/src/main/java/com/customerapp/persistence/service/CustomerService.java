package com.customerapp.persistence.service;

import java.util.List;

import com.customerapp.persistence.dao.exception.CustomerNotFoundException;
import com.customerapp.persistence.dao.exception.DAOException;
import com.customerapp.persistence.dto.Customer;

public interface CustomerService {

	public Customer addCustomer(Customer customer) throws DAOException;

	public Customer updateCustomer(Customer customer) throws DAOException, CustomerNotFoundException;

	public List<Customer> getAllCustomers() throws DAOException;

	public Customer findCustomerById(int id) throws CustomerNotFoundException, DAOException;

	public boolean deleteCustomer(int id) throws DAOException, CustomerNotFoundException;
}
