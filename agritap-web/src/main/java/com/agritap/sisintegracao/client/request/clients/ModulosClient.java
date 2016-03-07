package com.agritap.sisintegracao.client.request.clients;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.RestService;

import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.model.Modulo;

@Path("/modulos")
public interface ModulosClient extends RestService{

	@GET
	@Path("{id}")
	public void get(@PathParam("id") Integer id,RestCallback<Modulo> cb);

	@GET
	@Path("/todos")
	public void todos(RestCallback<List<Modulo>> cb);

	@GET
	@Path("/produtor/{id}")
	public void porProdutor(@PathParam("id") Integer produtorId,RestCallback<List<Modulo>> cb);


	@PUT
	public void update(Modulo modulo, RestCallback<Modulo> cb);

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Integer id,RestCallback<Void> cb);

}
