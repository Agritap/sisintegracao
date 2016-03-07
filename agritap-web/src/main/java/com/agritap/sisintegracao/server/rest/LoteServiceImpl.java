package com.agritap.sisintegracao.server.rest;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.agritap.sisintegracao.model.Lote;
import com.google.inject.Inject;

@Path("/lotes")
@Produces(MediaType.APPLICATION_JSON)
public class LoteServiceImpl extends AuthRestServiceImpl{

	@Inject
	transient EntityManager em;

	@GET
	@Path("{id}")
	public Lote get(@PathParam("id") Integer id) {
		return em.find(Lote.class, id);
	}

	
}