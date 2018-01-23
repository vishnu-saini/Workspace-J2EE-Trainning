package com.customerapp.rest.exceptionhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.customerapp.persistence.dao.exception.CustomerNotFoundException;

@Provider
public class CustomerNotFoundWrapper implements ExceptionMapper<CustomerNotFoundException> {

	@Override
	public Response toResponse(CustomerNotFoundException exception) {
		
		
		return Response.status(404).entity(exception.getMessage()).type("text/plain").build();
	}

}