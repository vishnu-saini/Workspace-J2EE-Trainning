package com.shoppingcart.rest.exceptionhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.shoppingcart.model.dao.exception.DAOException;

public class DAOExceptionWrapper implements ExceptionMapper<DAOException> {

	@Override
	public Response toResponse(DAOException exception) {
		
		return Response.status(500).entity(exception.getMessage()).type("text/plain").build();
	}

}
