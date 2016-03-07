package com.agritap.sisintegracao.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({@NamedQuery(name="origem.porTipoAtivos",query="select o from Origem o where o.animal = :tipoAnimal and  o.ativo= true order by o.nome ")})
public class Origem implements Rotulavel{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	@ManyToOne
	private Pessoa produtor;
	
	@Enumerated(EnumType.STRING)
	private TipoOrigem tipo;
	
	@Enumerated(EnumType.STRING)
	private TipoAnimal animal;
	
	@Enumerated(EnumType.STRING)
	private Integradora integradora;
 
	 private Boolean ativo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Pessoa getProdutor() {
		return produtor;
	}

	public void setProdutor(Pessoa produtor) {
		this.produtor = produtor;
	}

	public TipoOrigem getTipo() {
		return tipo;
	}

	public void setTipo(TipoOrigem tipo) {
		this.tipo = tipo;
	}

	public TipoAnimal getAnimal() {
		return animal;
	}

	public void setAnimal(TipoAnimal animal) {
		this.animal = animal;
	}

	public Integradora getIntegradora() {
		return integradora;
	}

	public void setIntegradora(Integradora integradora) {
		this.integradora = integradora;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String getIdAsString() {
		return id.toString();
	}

	@Override
	public String getRotulo() {
		return nome;
	}
	
}
