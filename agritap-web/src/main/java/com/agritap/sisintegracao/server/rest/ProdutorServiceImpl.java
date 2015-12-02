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

import com.agritap.sisintegracao.model.Produtor;
import com.agritap.sisintegracao.server.to.ListAdapter;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/produtores")
@Produces(MediaType.APPLICATION_JSON)
public class ProdutorServiceImpl {

	@Inject
	transient EntityManager em ;
	
	@GET
	@Path("{id}")
//	application/vnd.agritap.v1.entity.produtor+json
	public Produtor get(@PathParam("id")Integer id){
	
		return em.find(Produtor.class, id);
	
	}
	
	@GET
	@Path("/todos")
	public ListAdapter<Produtor> todos(){
		List<Produtor> produtores = em.createNamedQuery("produtor.todos",Produtor.class).getResultList();
		return new ListAdapter<Produtor>(produtores);
	}

	
	@PUT
	@Transactional
	public Produtor save(Produtor produtor){
		em.merge(produtor);
		return produtor;
	}
	
	@DELETE
	@Transactional
	@Path("{id}")
	public void delete(@PathParam("id")Integer id){
		Produtor p = em.find(Produtor.class, id);
		em.remove(p);
	}
}
