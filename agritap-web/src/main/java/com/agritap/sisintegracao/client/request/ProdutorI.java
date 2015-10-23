package com.agritap.sisintegracao.client.request;

import com.agritap.sisintegracao.client.model.Produtor;
import com.agritap.sisintegracao.server.requestfactory.ProdutorRequestService;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value=Produtor.class,locator=ProdutorRequestService.class)
public interface ProdutorI {
	
	public static String TYPE = EntityFactory.PREFIX + "produtor+json";
	
	public Integer getId();

	public String getNome();

	public void setNome(String nome);

	public String getEmail() ;

	public void setEmail(String email) ;

	public Boolean getAtivo() ;

	public void setAtivo(Boolean ativo);

	public String getTelefone() ;

	public void setTelefone(String telefone) ;
	
	public String getCodigoIntegradora();

	public void setCodigoIntegradora(String codigoIntegradora);


}
