package com.agritap.sisintegracao.client.model;

import java.math.BigDecimal;
import java.util.List;

public class Barracao {
	private Integer id;

	private Modulo modulo;
	
	private String rotulo;
	
	/**
	 * QUantidade de racao em kg que cabem nos comedouros + linhas
	 */
	private BigDecimal estoqueRacaoLinha;
	
	/**
	 * Tamanho do barracao em m2;
	 */
	private Integer area;
	
	/**
	 * Silos associados ao barracao
	 */
	private List<Silo> silos;
	
}
