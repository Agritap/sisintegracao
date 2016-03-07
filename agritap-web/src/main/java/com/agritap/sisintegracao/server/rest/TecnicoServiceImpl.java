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
import com.agritap.sisintegracao.model.Tecnico;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;



@Path("/tecnicos")
@Produces(MediaType.APPLICATION_JSON)
public class TecnicoServiceImpl {

	@Inject
	transient EntityManager em ;
	
	@GET
	@Path("{id}")
//	application/vnd.agritap.v1.entity.tecnico+json
	public Tecnico get(@PathParam("id")Integer id){
	
		return em.find(Tecnico.class, id);
	
	}
	
	@GET
	@Path("/todos")
	public ListAdapter<Tecnico> todos(){
		List<Tecnico> tecnicos = em.createNamedQuery("tecnico.todos",Tecnico.class).getResultList();
		return new ListAdapter<Tecnico>(tecnicos);
	}

	
	@PUT
	@Transactional
	public Tecnico save(Tecnico tecnico){
		em.merge(tecnico);
		return tecnico;
	}
	
	@DELETE
	@Transactional
	@Path("{id}")
	public void delete(@PathParam("id")Integer id){
		Tecnico t = em.find(Tecnico.class, id);
		em.remove(t);
	}
}
