package com.agritap.sisintegracao.client.request.beans;

import java.util.List;

import com.agritap.sisintegracao.client.request.EntityFactory;

public interface UsuarioI {
	
	public static String TYPE = EntityFactory.PREFIX + "usuario+json";
	
	public Integer getId() ;

	public void setId(Integer id);

	public PessoaI getUsuario() ;

	public void setUsuario(PessoaI usuario);

	public String getPermissoes() ;

	public void setPermissoes(String permissoes);

	public Boolean getAlteraSenha();

	public void setAlteraSenha(Boolean alteraSenha) ;

	public String getToken() ;

	public void setToken(String permissoes);

	public List<PessoaI> getProdutores() ;

	public void setProdutores(List<PessoaI> prod) ;

}
