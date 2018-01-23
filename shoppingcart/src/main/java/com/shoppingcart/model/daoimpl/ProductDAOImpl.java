package com.shoppingcart.model.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.shoppingcart.global.constants.DataSources;
import com.shoppingcart.global.constants.DatabaseConstants;
import com.shoppingcart.model.dao.ProductDAO;
import com.shoppingcart.model.dao.exception.DAOException;
import com.shoppingcart.model.dao.exception.ProductNotFoundException;
import com.shoppingcart.model.dto.Product;
import com.shoppingcart.model.utility.ConnectionFactory;

public class ProductDAOImpl implements ProductDAO {

	Logger logger = Logger.getLogger(ProductDAOImpl.class);
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	@Override
	public Product addProduct(Product product) throws DAOException {
		logger.debug(product);
		try {
			connection = ConnectionFactory.getConnection(DataSources.JNDI);
			PreparedStatement preparedStatement = connection.prepareStatement(DatabaseConstants.CREATE_PRODUCT,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setDouble(2, product.getPrice());
			preparedStatement.setString(3, product.getCategory());
			int result = preparedStatement.executeUpdate();
			if (result == 1) {
				ResultSet rs = preparedStatement.getGeneratedKeys();
				rs.next();
				product.setId(rs.getInt(1));
			} else {
				logger.debug("product not inserted");
			}

		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			releaseResources();
		}

		return product;
	}

	@Override
	public Product updateProduct(Product product) throws DAOException, ProductNotFoundException {
		try {
			connection = ConnectionFactory.getConnection(DataSources.JNDI);
			preparedStatement = connection.prepareStatement(DatabaseConstants.UPDATE_PRODUCT);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setDouble(2, product.getPrice());
			preparedStatement.setString(3, product.getCategory());
			preparedStatement.setInt(4, product.getId());
			int result =preparedStatement.executeUpdate();
			if(result == 0 ){
				throw new ProductNotFoundException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources();
		}
		return product;
	}

	@Override
	public List<Product> getAllProducts() throws DAOException {
		List<Product> products = new ArrayList<Product>();
		try {
			connection = ConnectionFactory.getConnection(DataSources.JNDI);
			preparedStatement = connection.prepareStatement(DatabaseConstants.SELECT_ALL_PRODUCT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("name"));
				product.setPrice(Double.parseDouble(resultSet.getString("price")));
				product.setCategory(resultSet.getString("category"));
				products.add(product);
				logger.debug(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources();
		}
		return products;
	}

	@Override
	public Product findProductById(int id) throws ProductNotFoundException, DAOException {
		Product product = null;
		try {
			connection = ConnectionFactory.getConnection(DataSources.JNDI);
			preparedStatement = connection.prepareStatement(DatabaseConstants.GET_PRODUCT_BYID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("name"));
				product.setPrice(Double.parseDouble(resultSet.getString("price")));
				product.setCategory(resultSet.getString("category"));
			}else
				throw new ProductNotFoundException("Product not found");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources();
		}
		return product;
	}

	@Override
	public boolean deleteProduct(int id) throws DAOException, ProductNotFoundException {
		boolean isDeleted = false;
		try {
			connection = ConnectionFactory.getConnection(DataSources.JNDI);
			preparedStatement = connection.prepareStatement(DatabaseConstants.DELETE_PRODUCT);
			preparedStatement.setInt(1, id);
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				isDeleted = true;
			}else{
				throw new ProductNotFoundException();
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
