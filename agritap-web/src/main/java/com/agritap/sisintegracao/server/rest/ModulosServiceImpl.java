package com.agritap.sisintegracao.server.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.agritap.sisintegracao.client.ValidacaoException;
import com.agritap.sisintegracao.client.vo.ListAdapter;
import com.agritap.sisintegracao.client.vo.UsuarioTO;
import com.agritap.sisintegracao.model.Modulo;
import com.agritap.sisintegracao.model.Pessoa;
import com.agritap.sisintegracao.server.ServerUtil;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/modulos")
@Produces(MediaType.APPLICATION_JSON)
public class ModulosServiceImpl extends AuthRestServiceImpl{

	@Inject
	transient EntityManager em;

	@GET
	@Path("{id}")
	public Modulo get(@PathParam("id") Integer id) {
		Modulo m = new Modulo();
		m.setNome("");
		m.setId(id);
		return m;
	}

	@GET
	@Path("/todos")
	public List<Modulo> todos( @Context HttpServletRequest request) {
		UsuarioTO usuario = getUsuario(request);
		List<Pessoa> produtoresAutorizados =usuario.getProdutores();
		return em.createNamedQuery("modulos.porProdutores", Modulo.class).setParameter("produtores", produtoresAutorizados).getResultList();
	}


	@GET
	@Path("/produtor/{id}")
	public List<Modulo> porProdutor( @Context HttpServletRequest request,@PathParam("id") Integer id) {
		return em.createNamedQuery("modulos.porProdutorId", Modulo.class).setParameter("produtorId", id).getResultList();
	}


	@PUT
	@Transactional
	public Modulo save(Modulo modulos) {
		
		if(ServerUtil.isEmpty(modulos.getNome())){
			throw new ValidacaoException("Informe um nome para este modulo");
		}
		List<Modulo> modulo = em.createNamedQuery("modulos.porNome",Modulo.class).setParameter("nome", modulos.getNome()).getResultList();
		for(Modulo r:modulo){
			if(modulos.getId()!=null && modulos.getId().equals(r.getId())){
//			tudo bem, eh a mesma
			}
		}
		em.merge(modulos);
		//Nao posso ter nomes repetidos no mesmo periodo
		return modulos;
	}
	
	

	@DELETE
	@Transactional
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		Modulo t = em.find(Modulo.class, id);
		em.remove(t);
	}
}