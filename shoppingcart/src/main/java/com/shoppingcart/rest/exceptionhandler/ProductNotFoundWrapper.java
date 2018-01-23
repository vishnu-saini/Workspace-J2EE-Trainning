package com.shoppingcart.rest.exceptionhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.shoppingcart.model.dao.exception.ProductNotFoundException;

@Provider
public class ProductNotFoundWrapper implements ExceptionMapper<ProductNotFoundException> {

	@Override
	public Response toResponse(ProductNotFoundException exception) {
		
		return Response.status(404).entity(exception.getMessage()).type("text/plain").build();
	}

}