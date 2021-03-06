package com.agritap.sisintegracao.server.rest;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

public class CustomJsonObjectFilter implements ContainerResponseFilter {

	@Override
	public ContainerResponse filter(ContainerRequest requestContext, ContainerResponse responseContext) {
		if(requestContext.getHeaderValue("agritap-custom-type")!=null){
			responseContext.getHttpHeaders().add("agritap-custom-type", requestContext.getHeaderValue("agritap-custom-type"));
		}
		return responseContext;
	}
 
}