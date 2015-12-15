package com.agritap.sisintegracao.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Silo {
	
	
	@Id
	private Integer id;
	
	@ManyToOne
	private Barracao barracao;
	
	
	
	@Column (length = 50)
	private String nome;
	
	/**
	 * Capacidade maxima do silo em kg
	 */
	@Column (scale = 2, precision = 10)
	private BigDecimal capacidade;

}
