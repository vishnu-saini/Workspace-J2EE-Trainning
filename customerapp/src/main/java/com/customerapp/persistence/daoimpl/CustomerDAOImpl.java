package com.customerapp.persistence.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.customerapp.global.constants.DataSources;
import com.customerapp.global.constants.DatabaseConstants;
import com.customerapp.persistence.dao.CustomerDAO;
import com.customerapp.persistence.dao.exception.CustomerNotFoundException;
import com.customerapp.persistence.dao.exception.DAOException;
import com.customerapp.persistence.dto.Customer;
import com.customerapp.persistence.utility.ConnectionFactory;

public class CustomerDAOImpl implements CustomerDAO {

	Logger logger = Logger.getLogger(CustomerDAOImpl.class);
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	@Override
	public Customer addCustomer(Customer customer) throws DAOException {
		logger.debug(customer);
		try {
			connection = ConnectionFactory.getConnection(DataSources.MYSQL);
			PreparedStatement preparedStatement = connection.prepareStatement(DatabaseConstants.CREATE_CUSTOMER,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getAddress());
			preparedStatement.setString(3, customer.getPhone());
			int result = preparedStatement.executeUpdate();
			if (result == 1) {
				ResultSet rs = preparedStatement.getGeneratedKeys();
				rs.next();
				customer.setId(rs.getInt(1));
			} else {
				logger.debug("customer not inserted");
			}

		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			releaseResources();
		}

		return customer;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws DAOException, CustomerNotFoundException {
		try {
			connection = ConnectionFactory.getConnection(DataSources.MYSQL);
			preparedStatement = connection.prepareStatement(DatabaseConstants.UPDATE_CUSTOMER);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getAddress());
			preparedStatement.setString(3, customer.getPhone());
			preparedStatement.setInt(4, customer.getId());
			int result =preparedStatement.executeUpdate();
			if(result == 0 ){
				throw new CustomerNotFoundException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources();
		}
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() throws DAOException {
		List<Customer> customers = new ArrayList<Customer>();
		try {
			connection = ConnectionFactory.getConnection(DataSources.JNDI);
			preparedStatement = connection.prepareStatement(DatabaseConstants.SELECT_ALL_CUSTOMER);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getInt("id"));
				customer.setName(resultSet.getString("name"));
				customer.setAddress(resultSet.getString("address"));
				customer.setPhone(resultSet.getString("phone"));
				customers.add(customer);
				logger.debug(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources();
		}
		return customers;
	}

	@Override
	public Customer findCustomerById(int id) throws CustomerNotFoundException, DAOException {
		Customer customer = null;
		try {
			connection = ConnectionFactory.getConnection(DataSources.MYSQL);
			preparedStatement = connection.prepareStatement(DatabaseConstants.GET_CUSTOMER_BYID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				customer = new Customer();
				customer.setId(resultSet.getInt("id"));
				customer.setName(resultSet.getString("name"));
				customer.setAddress(resultSet.getString("address"));
				customer.setPhone(resultSet.getString("phone"));
			}else
				throw new CustomerNotFoundException("Customer not found");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources();
		}
		return customer;
	}

	@Override
	public boolean deleteCustomer(int id) throws DAOException, CustomerNotFoundException {
		boolean isDeleted = false;
		try {
			connection = ConnectionFactory.getConnection(DataSources.MYSQL);
			preparedStatement = connection.prepareStatement(DatabaseConstants.DELETE_CUSTOMER);
			preparedStatement.setInt(1, id);
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				isDeleted = true;
			}else{
				throw new CustomerNotFoundException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources();
		}
		return isDeleted;
	}

	private void releaseResources() throws DAOException {
		try {

			if (resultSet != null) {
				resultSet.close();
				resultSet = null;
			}
			if (preparedStatement != null) {
				preparedStatement.close();
				preparedStatement = null;
			}
			/*
			 * if (connection != null) { connection.close(); connection = null;
			 * }
			 */
		} catch (SQLException e) {
			throw new DAOException();
		}
	}

}
