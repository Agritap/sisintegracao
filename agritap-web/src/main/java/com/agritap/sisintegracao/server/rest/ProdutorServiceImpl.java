package com.agritap.sisintegracao.server.rest;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.agritap.sisintegracao.server.model.Produtor;
import com.agritap.sisintegracao.server.to.ListAdapter;

@Path("/produtores")
@Produces(MediaType.APPLICATION_JSON)
public class ProdutorServiceImpl {

	@GET
	@Path("{id}")
//	application/vnd.agritap.v1.entity.produtor+json
	public Produtor get(@PathParam("id")Integer id){
		Produtor p = new Produtor();
		p.setNome("Lucas Alves");
		p.setId(id);
		return p;
	}
	
	@GET
	@Path("/todos")
	public ListAdapter<Produtor> todos(){
		List<Produtor> l = new LinkedList<>();
		Produtor p = new Produtor();
		p.setNome("Lucas Alves");
		p.setId(1);
		l.add(p);
		Produtor p2 = new Produtor();
		p2.setNome("Ze ruela");
		p2.setId(2);
		l.add(p2);
		
		return new ListAdapter<Produtor>(l);
	}

	@PUT
	public Produtor save(Produtor produtor){
		System.out.println("Passou no save");
		produtor.setNome(produtor.getNome()+" Moorrra remotting");
		return produtor;
	}
}
