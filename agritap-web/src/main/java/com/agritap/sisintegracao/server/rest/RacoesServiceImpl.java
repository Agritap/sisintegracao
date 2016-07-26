package com.agritap.sisintegracao.server.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.agritap.sisintegracao.model.TabelaRacao;
import com.agritap.sisintegracao.model.TipoRacao;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/racoes")
@Produces(MediaType.APPLICATION_JSON)
public class RacoesServiceImpl {

	@Inject
	transient EntityManager em;

	@GET
	@Path("/tipo")
	public List<TipoRacao> tipoRacaoTodos() {
		List<TipoRacao>  resultado =  em.createNamedQuery("tipoRacao.todos", TipoRacao.class).getResultList(); 
		return resultado;	
	}
	
	@GET
	@Path("/tabela")
	public List<TabelaRacao> tabelaRacaoTodos() {
		List<TabelaRacao>  resultado =  em.createNamedQuery("tabelaRacao.todos",TabelaRacao.class).getResultList();
		return resultado;
	}
	@GET
	@Path("/tabela/{id}")
	public TabelaRacao getTabela(@PathParam("id")Integer id) {
		return em.find(TabelaRacao.class,id);
	}


	
	@PUT
	@Path("/tabela")
	@Transactional
	public TabelaRacao salvarTabela(TabelaRacao tab) {
		return em.merge(tab);
	}
}