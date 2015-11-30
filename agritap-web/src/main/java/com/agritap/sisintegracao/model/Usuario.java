package com.agritap.sisintegracao.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@NamedQueries(@NamedQuery(name="usuario.porEmail",query="select u from Usuario u where u.usuario.email=:email "))
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Pessoa usuario;
	
	@Column(length=254)
	private String senha;

	@OneToMany(mappedBy="usuario")
	private List<UsuarioProdutor> produtores;
	
	private String permissoes;
	
	private Boolean alteraSenha;
	
	@Transient
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

	public List<UsuarioProdutor> getProdutores() {
		return produtores;
	}

	public void setProdutores(List<UsuarioProdutor> produtores) {
		this.produtores = produtores;
	}

	public String getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(String permissoes) {
		this.permissoes = permissoes;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
	
	
}
