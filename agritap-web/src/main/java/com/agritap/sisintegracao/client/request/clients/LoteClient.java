package com.agritap.sisintegracao.client.request.clients;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.RestService;

import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.model.Lote;

@Path("/lotes")
public interface LoteClient extends RestService{

	@GET
	@Path("{id}")
	public void get(@PathParam("id") Integer id,RestCallback<Lote> cb);


}
