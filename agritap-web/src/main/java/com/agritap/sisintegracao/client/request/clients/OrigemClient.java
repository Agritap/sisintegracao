package com.agritap.sisintegracao.client.request.clients;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.RestService;

import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.model.Origem;
import com.agritap.sisintegracao.model.TipoAnimal;

@Path("/origens")
public interface OrigemClient extends RestService{


	@GET
	@Path("porTipo/{tipo}")
	public void porTipo(@PathParam("tipo") TipoAnimal tipo,RestCallback<List<Origem>> cb);

}
