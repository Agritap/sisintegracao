package com.agritap.sisintegracao.client.request.beans;

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
	
}
