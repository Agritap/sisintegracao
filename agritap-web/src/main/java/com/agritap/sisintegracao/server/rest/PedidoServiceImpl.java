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
import com.agritap.sisintegracao.model.Pedido;
import com.agritap.sisintegracao.server.to.ListAdapter;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
public class PedidoServiceImpl {

	@Inject
	transient EntityManager em;

	@GET
	@Path("{id}")
	// application/vnd.agritap.v1.entity.produtor+json
	public Pedido get(@PathParam("id") Integer id) {
		Pedido p = new Pedido();
		p.setNumero("125896");
		p.setId(id);
		return p;
	}

	@GET
	@Path("/todos")
	public ListAdapter<Pedido> todos() {
		List<Pedido> pedidos = em.createNamedQuery("pedido.todos", Pedido.class).getResultList();
		return new ListAdapter<Pedido>(pedidos);
	}

	@PUT
	@Transactional
	public Pedido save(Pedido pedido) {
		em.merge(pedido);
		return pedido;
	}

	@DELETE
	@Transactional
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		Pedido p = em.find(Pedido.class, id);
		em.remove(p);
	}
}