package com.agritap.sisintegracao.server.rest;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.agritap.sisintegracao.client.ValidacaoException;
import com.agritap.sisintegracao.client.vo.ListAdapter;
import com.agritap.sisintegracao.client.vo.UsuarioTO;
import com.agritap.sisintegracao.model.Pessoa;
import com.agritap.sisintegracao.model.Usuario;
import com.agritap.sisintegracao.server.ServerUtil;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/pessoas")
@Produces(MediaType.APPLICATION_JSON)
public class PessoaServiceImpl extends AuthRestServiceImpl{
	Logger log = Logger.getLogger(PessoaServiceImpl.class.getName());
	@Inject
	transient EntityManager em ;
	
	@GET
	@Path("{id}")
//	application/vnd.agritap.v1.entity.produtor+json
	public Pessoa get(@PathParam("id")Integer id){
		return em.find(Pessoa.class, id);
	}
	
	@GET
	@Path("{id}/possuiSenha")
	public Boolean possuiSenha(@PathParam("id")Integer id,@Context HttpServletResponse servletResponse){
		Pessoa p= em.find(Pessoa.class, id);
		try{
			Usuario u = em.createNamedQuery("usuario.porPessoa",Usuario.class).setParameter("pessoa", p).getSingleResult();
			if(u!=null){
				return true;
			}
		}catch(Exception e){
		}
		return false;
	}
	
	@GET
	@Path("{id}/removeSenha")
	public Boolean removeSenha(@PathParam("usuario.porPessoa")Integer id){
		Pessoa p= em.find(Pessoa.class, id);
		try{
			Usuario u = em.createNamedQuery("usuario.porPessoa",Usuario.class).setParameter("pessoa", p).getSingleResult();
			em.remove(u);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	
	@GET
	@Path("/todos")
	public List<Pessoa> todos(){
		return em.createNamedQuery("pessoa.todos",Pessoa.class).getResultList();
	}


	@GET
	@Path("/todos/tipo/{tipo}")
	public ListAdapter<Pessoa> porTipo(@PathParam("tipo")String tipo){
		String query="select p from Pessoa p where ";
		
		if(tipo.equals("tecnico")){
			query += "tecnico = true";
		}
		if(tipo.equals("granjeiro")){
			query += "granjeiro = true";
		}
		if(tipo.equals("produtor")){
			query += "produtor = true";			
		} 
		TypedQuery<Pessoa> queryP = em.createQuery(query,Pessoa.class);
		List<Pessoa> produtores = queryP.getResultList();
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
	@Path("/authToken")
	public UsuarioTO getUsuario(@FormParam("token")String authToken){
		try{
			Integer id =ServerUtil.getTokenId(authToken);
			Date exp = ServerUtil.getTokenExpiracao(authToken);
		Date agora= new Date();
		if(agora.compareTo(exp)<0){
			//wheee. ta valido
			Usuario user = em.find(Usuario.class, id);
			return geraUsuarioTO(user);
		}
		}catch(Exception e){
			log.log(Level.WARNING,"Erro validando token",e);
			throw new ValidacaoException("Token expirado, inválido ou inexistente");
		}
		throw new ValidacaoException("Token expirado, inválido ou inexistente");
	}
	
	@POST
	@Path("/auth")
	public UsuarioTO getUsuario(@FormParam("login")String email,@FormParam("password")String pass){
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
			return geraUsuarioTO(user);
		}else{
			ValidacaoException ex =new ValidacaoException();
			ex.addErro("Senha de acesso inválida");
			throw ex;
		}
	}
	
	@POST
	@Path("{id}/updatePass")
	@Transactional
	public Boolean updatePass(@FormParam("senha")String senha,@PathParam("id")Integer idPessoa){
		Pessoa p= em.find(Pessoa.class, idPessoa);
		try{
			Usuario u = em.createNamedQuery("usuario.porPessoa",Usuario.class).setParameter("pessoa", p).getSingleResult();
			u.setSenha(ServerUtil.crypt(senha));
			em.merge(u);
			return true;
		}catch(Exception e){}
		return false;
	}

	
}
