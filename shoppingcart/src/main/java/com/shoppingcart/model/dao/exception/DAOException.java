package com.shoppingcart.model.dao.exception;

import java.util.HashMap;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	private HashMap<String, String> errorMap = new HashMap<String, String>();

	public DAOException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DAOException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DAOException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public void setErrorMap(HashMap<String, String> errorMap) {
		this.errorMap = errorMap;
	}

	public HashMap<String, String> getErrorMap() {
		return errorMap;
	}
}
