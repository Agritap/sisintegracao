package com.agritap.sisintegracao.server.rest;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.agritap.sisintegracao.client.ValidacaoException;
import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.client.ui.configuracao.LoteViewVO;
import com.agritap.sisintegracao.client.vo.ErrosValidacao;
import com.agritap.sisintegracao.client.vo.LoteVO;
import com.agritap.sisintegracao.client.vo.UsuarioTO;
import com.agritap.sisintegracao.model.Lote;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/lotes")
@Produces(MediaType.APPLICATION_JSON)
public class LoteServiceImpl extends AuthRestServiceImpl{

	@Inject
	transient EntityManager em;

	@GET
	@Path("/view")
	public LoteViewVO get(@QueryParam("id") Integer id,@Context HttpServletRequest request) {
		
		LoteViewVO result = new LoteViewVO();
		if(id!=null){
			result.setLote(em.find(Lote.class, id));
		}
		UsuarioTO usuario = getUsuario(request);
		result.setProdutores(usuario.getProdutores());
		result.setTecnicos(em.createNamedQuery("pessoa.tecnicos").getResultList());
		result.setGranjeiros(em.createNamedQuery("pessoa.granjeiros").getResultList());
		
		return result;
	}

	@PUT
	@Path("/salvar")
	@Transactional
	public Lote salvar(Lote lote) {
		validaGravar(lote);
		return em.merge(lote);
	}

	private void validaGravar(Lote lote) {
		ValidacaoException ex = new ValidacaoException();
		if(lote.getProdutor()==null){
			ex.addErro("produtor", "Informe o produtor");
		}
		if(lote.getDataMediaAlojamento()==null){
			ex.addErro("dataAlojamento", "Informe a data de alojamento");
		}
		if(lote.getModulo()==null){
			ex.addErro("modulo", "Informe o módulo");
		}
		if(lote.getPesoTotalVivoAlojado()==null || lote.getPesoMedioVivoAlojado().compareTo(BigDecimal.ZERO)<0){
			ex.addErro("pesoTotal", "Informe o peso total alojado");
		}
		if(lote.getPesoMedioVivoAlojado()==null || lote.getPesoMedioVivoAlojado().compareTo(BigDecimal.TEN)<0){
			ex.addErro("pesoMedio", "Informe o peso medio alojado deve ser informado e maior que 10 Kg");
		}
		if(lote.getQuantidadeAlojada()==null || lote.getQuantidadeAlojada().compareTo(0)<=0){
			ex.addErro("quantidadeAlojada", "Informe a quantidade alojada");
		}
		if(lote.getQuantidadeOrigemDistinta()==null || lote.getQuantidadeOrigemDistinta().compareTo(0)<=0){
			ex.addErro("quantidadeOrigem", "Informe a quantidade de origens");
		}
		if(lote.getSexo()==null ){
			ex.addErro("sexo", "Informe o sexo dos animais");
		}
		if(lote.getTipoOrigem()==null ){
			ex.addErro("tipoOrigem", "Informe o tipo de Origem");
		}
		if(ex.getAllErros().size()>0){
			throw ex;
		}
	}

	@GET
	@Path("/buscar")
	public List<LoteVO> buscar(@QueryParam("pesquisa") String pesquisa,@Context HttpServletRequest request){
		UsuarioTO usuario = getUsuario(request);
		String jpaQuery = "select new com.agritap.sisintegracao.client.vo.LoteVO(l) from Lote l where l.produtor in (:produtores) ";
		if(pesquisa!=null){
			//
		}
		TypedQuery<LoteVO> query = em.createQuery(jpaQuery,LoteVO.class).setParameter("produtores", usuario.getProdutores());
		query.setMaxResults(100);
		return query.getResultList();
	}

}