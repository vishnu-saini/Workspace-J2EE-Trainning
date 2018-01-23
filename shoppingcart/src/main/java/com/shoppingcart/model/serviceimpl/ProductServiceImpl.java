package com.shoppingcart.model.serviceimpl;

import java.util.List;

import com.shoppingcart.model.dao.ProductDAO;
import com.shoppingcart.model.dao.exception.DAOException;
import com.shoppingcart.model.dao.exception.ProductNotFoundException;
import com.shoppingcart.model.daoimpl.ProductDAOImpl;
import com.shoppingcart.model.dto.Product;
import com.shoppingcart.model.service.ProductService;

public class ProductServiceImpl implements ProductService {

	ProductDAO productDAO = new ProductDAOImpl();

	@Override
	public Product addProduct(Product product) throws DAOException {
		return productDAO.addProduct(product);
	}

	@Override
	public Product updateProduct(Product product) throws DAOException, ProductNotFoundException {
		return productDAO.updateProduct(product);
	}

	@Override
	public List<Product> getAllProducts() throws DAOException {
		return productDAO.getAllProducts();
	}

	@Override
	public Product findProductById(int id) throws ProductNotFoundException, DAOException {
		return productDAO.findProductById(id);
	}

	@Override
	public boolean deleteProduct(int id) throws DAOException, ProductNotFoundException {
		return productDAO.deleteProduct(id);
	}

}
