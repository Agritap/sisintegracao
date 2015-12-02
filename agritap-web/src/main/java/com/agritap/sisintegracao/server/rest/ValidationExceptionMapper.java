package com.agritap.sisintegracao.server.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.agritap.sisintegracao.client.ValidacaoException;
import com.agritap.sisintegracao.server.to.ErrosTO;

@Provider
public class ValidationExceptionMapper  implements ExceptionMapper<ValidacaoException>{

	@Override
	public Response toResponse(ValidacaoException exception) {
//		agritap-custom-type
		ErrosTO to = new ErrosTO();
		to.setErrosFields(exception.getErrosFields());
		to.setErrosGenericos(exception.getErrosGenericos());
		//Conflict
		Response response = Response.status(409).build();
		ResponseBuilder rb = Response.fromResponse(response);
		rb.entity(to);
		return rb.build();
	}

}
