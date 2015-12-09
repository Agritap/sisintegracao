package com.agritap.sisintegracao.model;

import java.util.List;

public class Modulo {
	
	private Integer id;
	
	TipoAnimal tipoAnimal;
	
	Pessoa produtor;
	
	Integer alojamentoMaximo;

	String rotulo;

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
}
