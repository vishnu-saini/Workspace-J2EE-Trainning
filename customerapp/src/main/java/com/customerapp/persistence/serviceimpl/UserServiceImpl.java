package com.customerapp.persistence.serviceimpl;

import java.util.List;

import com.customerapp.persistence.dao.UserDAO;
import com.customerapp.persistence.dao.exception.UserNotFoundException;
import com.customerapp.persistence.daoimpl.UserDAOImpl;
import com.customerapp.persistence.dto.User;
import com.customerapp.persistence.service.UserService;

public class UserServiceImpl implements UserService {

	UserDAO userDAO = new UserDAOImpl();

	@Override
	public User addUser(User user) {
		return userDAO.addUser(user);
	}

	@Override
	public List<User> getAllUsers() {
		return null;
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUserName(String username) throws UserNotFoundException {
		return userDAO.findByUserName(username);
	}

}
