package com.agritap.sisintegracao.client.request.clients;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.RestService;

import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.client.vo.UsuarioTO;
import com.agritap.sisintegracao.model.Pessoa;

@Path("/pessoas")
@Produces(MediaType.APPLICATION_JSON)
public interface PessoaClient  extends RestService {


	@POST
	@Path("/auth")
	public void  auth(@FormParam("login")String email,@FormParam("password")String pass,RestCallback<UsuarioTO> cb);

	@GET
	@Path("{id}")
	public void get(@PathParam("id")Integer id,RestCallback<Pessoa> cb);

	@GET
	@Path("/todos")
	public void todos(RestCallback<List<Pessoa>> cb);

	@PUT
	public void update(Pessoa pessoa,RestCallback<Pessoa> cb);

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id")Integer id,RestCallback<Void> cb);

	@POST
	@Path("/authToken")
	public void auth(@FormParam("token")String authToken,RestCallback<UsuarioTO> cb);

	@GET
	@Path("{id}/possuiSenha")
	public void possuiSenha(@PathParam("id")Integer id,RestCallback<Boolean> callback);

	@GET
	@Path("{id}/removeSenha")
	public void removeSenha(@PathParam("usuario.porPessoa")Integer id,RestCallback<Boolean> callback);
	
	@POST
	@Path("{id}/updatePass")
	public void updatePass(@PathParam("id")Integer idPessoa,@FormParam("senha")String senha,RestCallback<Boolean> callback);

	@GET
	@Path("/todos/tipo/{tipo}")
	public void porTipo(@PathParam("tipo")String tipo,RestCallback<List<Pessoa>> cb);

}
