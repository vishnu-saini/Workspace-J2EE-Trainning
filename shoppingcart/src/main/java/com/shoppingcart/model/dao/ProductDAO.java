package com.shoppingcart.model.dao;



import java.util.List;

import com.shoppingcart.model.dao.exception.DAOException;
import com.shoppingcart.model.dao.exception.ProductNotFoundException;
import com.shoppingcart.model.dto.Product;

public interface ProductDAO {
	public Product addProduct(Product product) throws DAOException;

	public Product updateProduct(Product product) throws DAOException, ProductNotFoundException;

	public List<Product> getAllProducts() throws DAOException;

	public Product findProductById(int id) throws ProductNotFoundException, DAOException;;

	public boolean deleteProduct(int id) throws DAOException, ProductNotFoundException;
}
