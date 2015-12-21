package com.agritap.sisintegracao.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
 @Entity 
 @NamedQueries(
		 { @NamedQuery (name="modulos.porProdutores", query = "select x from Modulo x where x.produtor in (:produtores) "),
			 @NamedQuery (name="modulos.porProdutorId", query = "select x from Modulo x where x.produtor.id = :produtorId order by nome")
		 })
public class Modulo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated (EnumType.STRING)
	TipoAnimal tipoAnimal;
	
	@ManyToOne
	Pessoa produtor;
	
	Integer alojamentoMaximo;


	@OneToMany (mappedBy = "modulo")
	private List<Barracao> barracoes;

	private String nome;
	
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
	
	public TipoAnimal getTipoAnimal() {
		return tipoAnimal;
	}
	
	public void setTipoAnimal(TipoAnimal tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}

	public List<Barracao> getBarracoes() {
		return barracoes;
	}

	public void setBarracoes(List<Barracao> barracoes) {
		this.barracoes = barracoes;
	}

	public Pessoa getProdutor() {
		return produtor;
	}

	public void setProdutor(Pessoa produtor) {
		this.produtor = produtor;
	}

	public Integer getAlojamentoMaximo() {
		return alojamentoMaximo;
	}

	public void setAlojamentoMaximo(Integer alojamentoMaximo) {
		this.alojamentoMaximo = alojamentoMaximo;
	}
}
