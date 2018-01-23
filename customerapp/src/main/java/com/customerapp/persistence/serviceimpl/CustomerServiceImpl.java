package com.customerapp.persistence.serviceimpl;

import java.util.List;

import com.customerapp.persistence.dao.CustomerDAO;
import com.customerapp.persistence.dao.exception.CustomerNotFoundException;
import com.customerapp.persistence.dao.exception.DAOException;
import com.customerapp.persistence.daoimpl.CustomerDAOImpl;
import com.customerapp.persistence.dto.Customer;
import com.customerapp.persistence.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	CustomerDAO customerDAO = new CustomerDAOImpl();

	@Override
	public Customer addCustomer(Customer customer) throws DAOException {
		return customerDAO.addCustomer(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) throws DAOException, CustomerNotFoundException {
		return customerDAO.updateCustomer(customer);
	}

	@Override
	public List<Customer> getAllCustomers() throws DAOException {
		return customerDAO.getAllCustomers();
	}

	@Override
	public Customer findCustomerById(int id) throws CustomerNotFoundException, DAOException {
		return customerDAO.findCustomerById(id);
	}

	@Override
	public boolean deleteCustomer(int id) throws DAOException, CustomerNotFoundException {
		return customerDAO.deleteCustomer(id);
	}

}
