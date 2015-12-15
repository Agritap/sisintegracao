package com.agritap.sisintegracao.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.agritap.sisintegracao.server.rest.BigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity

public class Barracao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Modulo modulo;

	@Column(length = 50)
	private String nome;

	/**
	 * QUantidade de racao em kg que cabem nos comedouros + linhas
	 */
	@Column(scale = 2, precision = 10)
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal estoqueRacaoLinha;

	/**
	 * Tamanho do barracao em m2;
	 */
	private Integer area;

	/**
	 * Silos associados ao barracao
	 */
	@OneToMany(mappedBy = "barracao")
	private List<Silo> silos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getEstoqueRacaoLinha() {
		return estoqueRacaoLinha;
	}

	public void setEstoqueRacaoLinha(BigDecimal estoqueRacaoLinha) {
		this.estoqueRacaoLinha = estoqueRacaoLinha;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public List<Silo> getSilos() {
		return silos;
	}

	public void setSilos(List<Silo> silos) {
		this.silos = silos;
	}

}
