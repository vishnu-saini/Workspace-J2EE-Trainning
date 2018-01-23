package com.customerapp.global.constants;

public class DatabaseConstants {

	/*Table Names*/
	public static final String CUSTOMER_TABLE="customer";
	
	/*Database Queries for customer table*/
	public static final String CREATE_CUSTOMER="insert into customer (name,address,phone) values (?,?,?)"; 
	public static final String UPDATE_CUSTOMER="update customer set name=?, address=?, phone=? where id=?";
	public static final String SELECT_ALL_CUSTOMER="select * from customer";
	public static final String GET_CUSTOMER_BYID="select * from customer where id=?";
	public static final String DELETE_CUSTOMER="delete from customer where id=?";
	
	/*Database Queries for user table */
	public static final String CREATE_USER="insert into user (username,password) values (?,?)"; 
	public static final String UPDATE_USER="update user set username=?, password=? where id=?";
	public static final String SELECT_ALL_USER="select * from user";
	public static final String GET_USER_BYUSERNAME="select * from user where username=?";
	public static final String DELETE_USER="delete from user where id=?";
	
}
