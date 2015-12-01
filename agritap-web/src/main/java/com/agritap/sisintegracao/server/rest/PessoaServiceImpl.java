package com.agritap.sisintegracao.server.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import com.agritap.sisintegracao.model.UsuarioProdutor;
import com.agritap.sisintegracao.server.ServerUtil;
import com.agritap.sisintegracao.server.to.ListAdapter;
import com.agritap.sisintegracao.server.to.UsuarioTO;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/pessoas")
@Produces(MediaType.APPLICATION_JSON)
public class PessoaServiceImpl {
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
	@Path("/authToken")
	public UsuarioTO getUsuario(@FormParam("token")String authToken){
		try{
		String token = ServerUtil.decrypt(authToken);
		StringTokenizer buf = new StringTokenizer(token,"|");
		Integer id = Integer.parseInt(buf.nextToken());
		long expires = Long.parseLong(buf.nextToken());
		Date exp = new Date(expires);
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

	private UsuarioTO geraUsuarioTO(Usuario user) {
		Integer id = user.getId();
		Calendar agora= Calendar.getInstance();
		agora.add(Calendar.DAY_OF_MONTH, 30);
		String token = id+"|"+agora.getTimeInMillis();
		UsuarioTO to = new UsuarioTO();
		to.setToken(ServerUtil.crypt(token));
		to.setPermissoes(user.getPermissoes());
		to.setUsuario(user.getUsuario());
		to.setAlteraSenha(user.getAlteraSenha());
		to.setId(user.getId());
		
		List<Pessoa> produtoresPermitidos = new LinkedList<>();
		to.setProdutores(produtoresPermitidos);
		if(user.getUsuario().getProdutor()!=null && user.getUsuario().getProdutor()){
			produtoresPermitidos.add(user.getUsuario());
		}
		if(user.getProdutores()!=null){
			for(UsuarioProdutor u:user.getProdutores()){
				if(ServerUtil.isPeriodosIntercecao(u.getDataInicio(), u.getDataFim(), new Date(), new Date())){
					produtoresPermitidos.add(u.getProdutor());
				}
			}
		}
		
		return to;
	}
}
