package com.agritap.sisintegracao.server.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.agritap.sisintegracao.model.TipoRacao;
import com.agritap.sisintegracao.server.to.ListAdapter;
import com.google.inject.Inject;

@Path("/racoes")
@Produces(MediaType.APPLICATION_JSON)
public class RacoesServiceImpl {

	@Inject
	transient EntityManager em;

	@GET
	@Path("/tipo/todos")
	public ListAdapter<TipoRacao> tipoRacaoTodos() {
		List<TipoRacao>  resultado =  em.createNamedQuery("tipoRacao.todos", TipoRacao.class).getResultList(); 
		ListAdapter<TipoRacao> r = new ListAdapter<TipoRacao>(resultado);
		return r;
		
		
	}

}