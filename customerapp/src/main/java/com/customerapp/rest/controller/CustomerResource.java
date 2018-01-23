package com.customerapp.rest.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.customerapp.persistence.dao.exception.CustomerNotFoundException;
import com.customerapp.persistence.dao.exception.DAOException;
import com.customerapp.persistence.dto.Customer;
import com.customerapp.persistence.service.CustomerService;
import com.customerapp.persistence.serviceimpl.CustomerServiceImpl;

@Path("customer")
public class CustomerResource {

	CustomerService customerService=new CustomerServiceImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getAllCustomers() throws DAOException{
		return customerService.getAllCustomers();
	}
	
	@GET
	@Path("/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerById(@PathParam("customerId") int customerId) throws CustomerNotFoundException, DAOException{
		return customerService.findCustomerById(customerId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer addCustomer(Customer customer) throws DAOException{
		return customerService.addCustomer(customer);
		
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{customerId}")
	public Customer updateCustomer(@PathParam("customerId") int customerId, Customer customer) throws DAOException, CustomerNotFoundException{
		customer.setId(customerId);
		customerService.updateCustomer(customer);
		return customer;
	}
	
	@DELETE
	@Path("/{customerId}")
	public void delete(@PathParam("customerId") int customerId) throws DAOException, CustomerNotFoundException{
		customerService.deleteCustomer(customerId);
	}
}
