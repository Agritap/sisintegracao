package com.agritap.sisintegracao.client.vo;

import java.util.List;

import com.agritap.sisintegracao.model.Pessoa;

public class UsuarioTO {

	/**
	 * Id da entidade Usuario
	 */
	private Integer id;

	/**
	 * Pessoa que representa esse usuario
	 */
	private Pessoa usuario;
	
	/**
	 * Produtors autorizados pelo usuario
	 */
	private List<Pessoa> produtores;
	
	private String permissoes;
	
	private Boolean alteraSenha;
	
	private transient String token;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getUsuario() {
		return usuario;
	}

	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}

	public List<Pessoa> getProdutores() {
		return produtores;
	}

	public void setProdutores(List<Pessoa> produtores) {
		this.produtores = produtores;
	}

	public String getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(String permissoes) {
		this.permissoes = permissoes;
	}

	public Boolean getAlteraSenha() {
		return alteraSenha;
	}

	public void setAlteraSenha(Boolean alteraSenha) {
		this.alteraSenha = alteraSenha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public boolean isProdutorUnico(){
		return getProdutores()!=null && getProdutores().size()==1;
	}
}
