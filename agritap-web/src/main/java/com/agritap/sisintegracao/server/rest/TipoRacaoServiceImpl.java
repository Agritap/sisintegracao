package com.agritap.sisintegracao.server.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.agritap.sisintegracao.client.ClientUtil;
import com.agritap.sisintegracao.client.ValidacaoException;
import com.agritap.sisintegracao.client.vo.ListAdapter;
import com.agritap.sisintegracao.model.TipoRacao;
import com.agritap.sisintegracao.server.ServerUtil;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/tipoRacao")
@Produces(MediaType.APPLICATION_JSON)
public class TipoRacaoServiceImpl {

	@Inject
	transient EntityManager em;

	@GET
	@Path("{id}")
	// application/vnd.agritap.v1.entity.TipoRacao+json
	public TipoRacao get(@PathParam("id") Integer id) {
		TipoRacao t = new TipoRacao();
		t.setNome("");
		t.setId(id);
		return t;
	}

	@GET
	@Path("/todos")
	public List<TipoRacao> todos() {
		return em.createNamedQuery("tipoRacao.todos", TipoRacao.class).getResultList();
	}

	@PUT
	@Transactional
	public TipoRacao save(TipoRacao tipoRacao) {
		
		if(ServerUtil.isEmpty(tipoRacao.getNome())){
			throw new ValidacaoException("Informe um nome para esta ração");
		}
		List<TipoRacao> racoes = em.createNamedQuery("tipoRacao.porNome",TipoRacao.class).setParameter("nome", tipoRacao.getNome()).getResultList();
		for(TipoRacao r:racoes){
			if(tipoRacao.getId()!=null && tipoRacao.getId().equals(r.getId())){
//			tudo bem, eh a mesma
			}else{
				if(ServerUtil.isPeriodosIntercecao(tipoRacao.getDataInicio(),tipoRacao.getDataFim(),r.getDataInicio(),r.getDataFim())){
					throw new ValidacaoException("O nome deve ser único dentro do período de validade");
				}
			}
		}
		em.merge(tipoRacao);
		//Nao posso ter nomes repetidos no mesmo p[eriodo
		return tipoRacao;
	}
	
	

	@DELETE
	@Transactional
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		TipoRacao t = em.find(TipoRacao.class, id);
		em.remove(t);
	}
}