package com.agritap.sisintegracao.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.agritap.sisintegracao.server.rest.BigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class TabelaRacao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 
	
	@Temporal(TemporalType.DATE)
	private Date inicio; 
	
	@Temporal (TemporalType.DATE)
	private Date fim;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal pesoMinimo;
	
	@OneToMany(mappedBy="tabela")
	private List<TabelaRacaoItem> itens; 
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	public BigDecimal getPesoMinimo() {
		return pesoMinimo;
	}
	public void setPesoMinimo(BigDecimal pesoMinimo) {
		this.pesoMinimo = pesoMinimo;
	}
	public List<TabelaRacaoItem> getItens() {
		return itens;
	}
	public void setItens(List<TabelaRacaoItem> itens) {
		this.itens = itens;
	}
	

}
