package com.agritap.sisintegracao.server.rest;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.agritap.sisintegracao.client.model.Produtor;

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

	@PUT
	public Produtor save(Produtor produtor){
		System.out.println("Passou no save");
		produtor.setNome(produtor.getNome()+" Moorrra remotting");
		return produtor;
	}
}
