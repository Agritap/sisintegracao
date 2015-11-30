package com.agritap.sisintegracao.server.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.agritap.sisintegracao.client.ValidacaoException;
import com.agritap.sisintegracao.model.Pessoa;
import com.agritap.sisintegracao.model.Usuario;
import com.agritap.sisintegracao.server.ServerUtil;
import com.agritap.sisintegracao.server.to.ListAdapter;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/pessoas")
@Produces(MediaType.APPLICATION_JSON)
public class PessoaServiceImpl {

	@Inject
	transient EntityManager em ;
	
	@GET
	@Path("{id}")
//	application/vnd.agritap.v1.entity.produtor+json
	public Pessoa get(@PathParam("id")Integer id){
		Pessoa p = new Pessoa();
		p.setNome("Lucas Alves");
		p.setId(id);
		return p;
	}
	
	@GET
	@Path("/todos")
	public ListAdapter<Pessoa> todos(){
		List<Pessoa> produtores = em.createNamedQuery("pessoa.todos",Pessoa.class).getResultList();
		return new ListAdapter<Pessoa>(produtores);
	}

	
	@PUT
	@Transactional
	public Pessoa save(Pessoa produtor){
		if(produtor.getEmail()==null || produtor.getEmail()==""){
			ValidacaoException v = new ValidacaoException();
			v.addErro("email","Informe o email do usuário");
			throw v;
		}
		em.merge(produtor);
		return produtor;
	}
	
	@DELETE
	@Transactional
	@Path("{id}")
	public void delete(@PathParam("id")Integer id){
		Pessoa p = em.find(Pessoa.class, id);
		em.remove(p);
	}
	
	@POST
	@Path("/auth")
	public Usuario getUsuario(@FormParam("login")String email,@FormParam("password")String pass){
		TypedQuery<Usuario> query  = em.createNamedQuery("usuario.porEmail",Usuario.class).setParameter("email", email);
		List<Usuario> usuarios = query.getResultList();
		if(usuarios.size()==0){
			//Email invalido
			ValidacaoException ex =new ValidacaoException();
			ex.addErro("Email informado inválido");
			throw ex;
		}
		if(usuarios.size()>1){
			//Usuario duplicado
		}
		Usuario user = usuarios.get(0);
		if(user.getSenha()==null){
			//Senha nao definida para o usuario
			ValidacaoException ex =new ValidacaoException();
			ex.addErro("Senha de acesso não definida para esse usuário, procure a associação e registre uma senha");
			throw ex;
		}
		if(user.getSenha().equals(ServerUtil.crypt(pass))){
			return user;
		}else{
			ValidacaoException ex =new ValidacaoException();
			ex.addErro("Senha de acesso inválida");
			throw ex;
		}
	}
}
