package com.agritap.sisintegracao.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;

import com.agritap.sisintegracao.server.rest.BigDecimalSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@JsonIgnoreProperties({"barracao"})
public class Silo {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@XmlTransient
	@ManyToOne
	private Barracao barracao;
	
	@Column (length = 50)
	private String nome;
	
	/**
	 * Capacidade maxima do silo em kg
	 */
	@Column (scale = 2, precision = 10)
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal capacidade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Barracao getBarracao() {
		return barracao;
	}

	public void setBarracao(Barracao barracao) {
		this.barracao = barracao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(BigDecimal capacidade) {
		this.capacidade = capacidade;
	}

}
