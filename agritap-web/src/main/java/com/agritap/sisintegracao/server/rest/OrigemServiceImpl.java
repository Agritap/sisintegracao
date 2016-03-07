package com.agritap.sisintegracao.server.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.agritap.sisintegracao.model.Origem;
import com.agritap.sisintegracao.model.TipoAnimal;
import com.google.inject.Inject;

@Path("/origens")
@Produces(MediaType.APPLICATION_JSON)
public class OrigemServiceImpl extends AuthRestServiceImpl{

	@Inject
	transient EntityManager em;

	@GET
	@Path("porTipo/{tipo}")
	public List<Origem> porTipo(@PathParam("tipo") TipoAnimal tipo) {
		return em.createNamedQuery("origem.porTipoAtivos",Origem.class).setParameter("tipoAnimal", tipo).getResultList();
	}

	
}