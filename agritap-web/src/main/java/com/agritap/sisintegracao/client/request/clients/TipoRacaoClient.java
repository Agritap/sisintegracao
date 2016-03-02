package com.agritap.sisintegracao.client.request.clients;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.RestService;

import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.model.TipoRacao;

@Path("/tipoRacao")
@Produces(MediaType.APPLICATION_JSON)
public interface TipoRacaoClient extends RestService {

	@GET
	@Path("{id}")
	public void get(@PathParam("id") Integer id,RestCallback<TipoRacao> cb);

	@GET
	@Path("/todos")
	public void todos(RestCallback<List<TipoRacao>> cb);

	@PUT
	public void update(TipoRacao tipoRacao,RestCallback<TipoRacao> cb);
	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Integer id,RestCallback<Void> cb);

	

}
