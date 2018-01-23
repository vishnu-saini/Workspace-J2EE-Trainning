package com.shoppingcart.global.constants;

public class DatabaseConstants {

	/*Table Names*/
	public static final String PRODUCT_TABLE="product";
	
	/*Database Queries for product table*/
	public static final String CREATE_PRODUCT="insert into product (name,price,category) values (?,?,?)"; 
	public static final String UPDATE_PRODUCT="update product set name=?, price=?, category=? where id=?";
	public static final String SELECT_ALL_PRODUCT="select * from product";
	public static final String GET_PRODUCT_BYID="select * from product where id=?";
	public static final String DELETE_PRODUCT="delete from product where id=?";
	
}
