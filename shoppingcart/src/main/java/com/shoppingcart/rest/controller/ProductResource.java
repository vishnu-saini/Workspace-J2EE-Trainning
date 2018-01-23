package com.shoppingcart.rest.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shoppingcart.model.dao.exception.DAOException;
import com.shoppingcart.model.dao.exception.ProductNotFoundException;
import com.shoppingcart.model.dto.Product;
import com.shoppingcart.model.service.ProductService;
import com.shoppingcart.model.serviceimpl.ProductServiceImpl;

@Path("product")
public class ProductResource {

	ProductService productService=new ProductServiceImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts() throws DAOException{
		return productService.getAllProducts();
	}
	
	@GET
	@Path("/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProductById(@PathParam("productId") int productId) throws ProductNotFoundException, DAOException{
		return productService.findProductById(productId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Product addProduct(Product product) throws DAOException{
		return productService.addProduct(product);
		
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{productId}")
	public Product updateProduct(@PathParam("productId") int productId, Product product) throws DAOException, ProductNotFoundException{
		product.setId(productId);
		productService.updateProduct(product);
		return product;
	}
	
	@DELETE
	@Path("/{productId}")
	public void delete(@PathParam("productId") int productId) throws DAOException, ProductNotFoundException{
		productService.deleteProduct(productId);
	}
}
