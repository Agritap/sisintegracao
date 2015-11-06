package com.agritap.sisintegracao.server.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.agritap.sisintegracao.model.TipoRacao;
import com.agritap.sisintegracao.server.to.ListAdapter;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/tipoRacao")
@Produces(MediaType.APPLICATION_JSON)
public class TipoRacaoServiceImpl {

	@Inject
	transient EntityManager em;

	@GET
	@Path("{id}")
	// application/vnd.agritap.v1.entity.TipoRacao+json
	public TipoRacao get(@PathParam("id") Integer id) {
		TipoRacao t = new TipoRacao();
		t.setNome(125896);
		t.setId(id);
		return t;
	}

	@GET
	@Path("/todos")
	public ListAdapter<TipoRacao> todos() {
		List<TipoRacao> tipoRacao = em.createNamedQuery("tipoRacao.todos", TipoRacao.class).getResultList();
		return new ListAdapter<TipoRacao>(tipoRacao);
	}

	@PUT
	@Transactional
	public TipoRacao save(TipoRacao tipoRacao) {
		em.merge(tipoRacao);
		return tipoRacao;
	}

	@DELETE
	@Transactional
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		TipoRacao t = em.find(TipoRacao.class, id);
		em.remove(t);
	}
}