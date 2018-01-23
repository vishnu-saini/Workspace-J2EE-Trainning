package com.customerapp.persistence.service;

import java.util.List;

import com.customerapp.persistence.dao.exception.UserNotFoundException;
import com.customerapp.persistence.dto.User;

public interface UserService {

	public User addUser(User user);

	public User updateUser(User user);

	public List<User> getAllUsers();

	public User findByUserName(String username) throws UserNotFoundException;

	public void deleteUser(int id);
}
