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

import com.agritap.sisintegracao.client.vo.ListAdapter;
import com.agritap.sisintegracao.model.Recebimento;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/recebimentos")
@Produces(MediaType.APPLICATION_JSON)
public class RecebimentoServiceImpl {

	@Inject
	transient EntityManager em;

	@GET
	@Path("{id}")
	// application/vnd.agritap.v1.entity.recebimento+json
	public Recebimento get(@PathParam("id") Integer id) {
		Recebimento p = new Recebimento();
		p.setNumero("125896");
		p.setId(id);
		return p;
	}

	@GET
	@Path("/todos")
	public ListAdapter<Recebimento> todos() {
		List<Recebimento> recebimento = em.createNamedQuery("recebimento.todos", Recebimento.class).getResultList();
		return new ListAdapter<Recebimento>(recebimento);
	}

	@PUT
	@Transactional
	public Recebimento save(Recebimento recebimento) {
		em.merge(recebimento);
		return recebimento;
	}

	@DELETE
	@Transactional
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		Recebimento p = em.find(Recebimento.class, id);
		em.remove(p);
	}
}