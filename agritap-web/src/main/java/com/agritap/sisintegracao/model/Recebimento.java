package com.agritap.sisintegracao.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

@Entity
@NamedQueries(
		{@NamedQuery(name="recebimento.todos",query="select p from Recebimento p")})
public class Recebimento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private Integradora integradora;
	
	private int numero;
	
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

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
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

	public void setNumero(String string) {
		// TODO Auto-generated method stub
		
	}
}
