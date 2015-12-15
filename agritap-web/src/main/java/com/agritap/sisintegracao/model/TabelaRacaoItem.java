package com.agritap.sisintegracao.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.agritap.sisintegracao.server.rest.BigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@Entity
public class TabelaRacaoItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private TabelaRacao tabela; 
	@ManyToOne
	private TipoRacao tipo; 
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal consumoEsperado;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TabelaRacao getTabela() {
		return tabela;
	}
	public void setTabela(TabelaRacao tabela) {
		this.tabela = tabela;
	}
	public TipoRacao getTipo() {
		return tipo;
	}
	public void setTipo(TipoRacao tipo) {
		this.tipo = tipo;
	}
	public BigDecimal getConsumoEsperado() {
		return consumoEsperado;
	}
	public void setConsumoEsperado(BigDecimal consumoEsperado) {
		this.consumoEsperado = consumoEsperado;
	} 

}
