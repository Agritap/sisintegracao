package com.agritap.sisintegracao.server.rest;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.agritap.sisintegracao.client.vo.UsuarioTO;
import com.agritap.sisintegracao.model.Pessoa;
import com.agritap.sisintegracao.model.Usuario;
import com.agritap.sisintegracao.model.UsuarioProdutor;
import com.agritap.sisintegracao.server.ServerUtil;
import com.google.inject.Inject;

public class AuthRestServiceImpl {
	@Inject
	transient EntityManager em;
	
	public UsuarioTO getUsuario(HttpServletRequest request) {
		Cookie[] cks = request.getCookies();
		if(cks!=null){
			for(Cookie c:cks){
				if(c.getName().equals("agritapweb_token")){
					//eh o cookie de autenticacao
					String token = c.getValue();
					token = ServerUtil.decrypt(token);
					StringTokenizer buf = new StringTokenizer(token,ServerUtil.TOKEN_SEP);
					Integer id = Integer.parseInt(buf.nextToken());
					Usuario user = em.find(Usuario.class, id);
					return geraUsuarioTO(user);
				}
			}
		}
		return null;
	}
	public UsuarioTO geraUsuarioTO(Usuario user) {
		Integer id = user.getId();
	
		String token = ServerUtil.generateToken(id);
		UsuarioTO to = new UsuarioTO();
		to.setToken(token);
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
