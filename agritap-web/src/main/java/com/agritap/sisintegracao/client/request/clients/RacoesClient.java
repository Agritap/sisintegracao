package com.agritap.sisintegracao.client.request.clients;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.RestService;

import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.model.TabelaRacao;
import com.agritap.sisintegracao.model.TipoRacao;

@Path("/racoes")
@Produces(MediaType.APPLICATION_JSON)
public interface RacoesClient extends RestService {

	@GET
	@Path("/tipo/todos")
	public void todosTipos(RestCallback<List<TipoRacao>> cb);
	
	@PUT
	@Path("/tabela")
	public void update(TabelaRacao tabelaRacao, RestCallback<TabelaRacao> cb) ;

	@GET
	@Path("/tabela/todos")
	public void todasTabelas(RestCallback<List<TabelaRacao>> cb);
	
}

