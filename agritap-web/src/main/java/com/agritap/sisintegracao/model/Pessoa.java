package com.agritap.sisintegracao.model;

import java.io.Serializable;

import javax.persistence.Column;
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
		{
			@NamedQuery(name="pessoa.todos",query="select p from Pessoa p")
			}
		
		)
public class Pessoa implements Serializable,Rotulavel{
	
	private static final long serialVersionUID = -3216625491090034548L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private Integradora integradora;
	
	@Column(length=14)
	private String cpf;

	@Column(length=255)
	private String nome;
	
	@Column(length=30)
	private String apelido;
	
	@Column(length=30)
	private String codigoIntegradora;
	
	@Column(length=250)
	private String email;
	
	private Boolean ativo;
	
	private String telefone;
	
	private Boolean granjeiro;
	
	private Boolean tecnico;
	
	private Boolean produtor;

	private Boolean administrador;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Boolean getGranjeiro() {
		return granjeiro;
	}

	public void setGranjeiro(Boolean granjeiro) {
		this.granjeiro = granjeiro;
	}

	public Boolean getProdutor() {
		return produtor;
	}

	public void setProdutor(Boolean produtor) {
		this.produtor = produtor;
	}

	public Boolean getTecnico() {
		return tecnico;
	}

	public void setTecnico(Boolean tecnico) {
		this.tecnico = tecnico;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Boolean getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}

	@Override
	public String getRotulo() {
		return getNome();
	}
	
	public String getIdAsString(){
		return id.toString();
	}
}
