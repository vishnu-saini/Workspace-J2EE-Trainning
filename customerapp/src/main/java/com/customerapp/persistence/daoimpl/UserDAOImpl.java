package com.customerapp.persistence.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.customerapp.global.constants.DataSources;
import com.customerapp.global.constants.DatabaseConstants;
import com.customerapp.persistence.dao.UserDAO;
import com.customerapp.persistence.dao.exception.DAOException;
import com.customerapp.persistence.dto.User;
import com.customerapp.persistence.utility.ConnectionFactory;

public class UserDAOImpl implements UserDAO {

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	@Override
	public User addUser(User user) {
		try {
			connection = ConnectionFactory.getConnection(DataSources.MYSQL);
			preparedStatement = connection.prepareStatement(DatabaseConstants.CREATE_USER);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				releaseResources();
			} catch (DAOException e) {

				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void deleteUser(int userId) {
		try {
			connection = ConnectionFactory.getConnection(DataSources.MYSQL);
			preparedStatement = connection.prepareStatement(DatabaseConstants.DELETE_USER);
			// ParameteresultSet start with 1
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				releaseResources();
			} catch (DAOException e) {

				e.printStackTrace();
			}
		}
	}

	@Override
	public User updateUser(User user) {
		try {
			connection = ConnectionFactory.getConnection(DataSources.MYSQL);
			preparedStatement = connection.prepareStatement(DatabaseConstants.UPDATE_USER);
			// ParameteresultSet start with 1
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setInt(3, user.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				releaseResources();
			} catch (DAOException e) {

				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> useresultSet = new ArrayList<User>();
		try {
			connection = ConnectionFactory.getConnection(DataSources.MYSQL);
			preparedStatement = connection.prepareStatement(DatabaseConstants.SELECT_ALL_USER);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				useresultSet.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				releaseResources();
			} catch (DAOException e) {

				e.printStackTrace();
			}
		}

		return useresultSet;
	}

	public User findByUserName(String username) {
		User user =null;
		try {
			connection = ConnectionFactory.getConnection(DataSources.MYSQL);
			preparedStatement = connection.prepareStatement(DatabaseConstants.GET_USER_BYUSERNAME);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				user= new User();
				user.setId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				releaseResources();
			} catch (DAOException e) {

				e.printStackTrace();
			}
		}

		return user;
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
	/*		if (connection != null) {
				connection.close();
				connection = null;
			}*/
		} catch (SQLException e) {
			throw new DAOException();
		}
	}

}
