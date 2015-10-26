package com.agritap.sisintegracao.server.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Produtor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private Integradora integradora;
	
	private String nome;
	
	private String codigoIntegradora;
	
	private String email;
	
	private Boolean ativo;
	
	private String telefone;

	@Version
	private Integer version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integradora getIntegradora() {
		return integradora;
	}

	public void setIntegradora(Integradora integradora) {
		this.integradora = integradora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoIntegradora() {
		return codigoIntegradora;
	}

	public void setCodigoIntegradora(String codigoIntegradora) {
		this.codigoIntegradora = codigoIntegradora;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
