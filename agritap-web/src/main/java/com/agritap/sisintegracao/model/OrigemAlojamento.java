package com.agritap.sisintegracao.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.agritap.sisintegracao.server.rest.BigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class OrigemAlojamento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Lote lote;
	
	@ManyToOne
	private Origem produtor;
	
	private Integer quantidade;

	private Integer mortosTransporte;

	@Temporal(TemporalType.DATE)
	private Date dataAlojamento;
	
	private Integer idadeAlojamento;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal pesoAlojado;
	
	@Enumerated(EnumType.STRING)
	private SexoLote sexo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Origem getProdutor() {
		return produtor;
	}

	public void setProdutor(Origem produtor) {
		this.produtor = produtor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getIdadeAlojamento() {
		return idadeAlojamento;
	}

	public void setIdadeAlojamento(Integer idadeAlojamento) {
		this.idadeAlojamento = idadeAlojamento;
	}

	public BigDecimal getPesoAlojado() {
		return pesoAlojado;
	}

	public void setPesoAlojado(BigDecimal pesoAlojado) {
		this.pesoAlojado = pesoAlojado;
	}

	public SexoLote getSexo() {
		return sexo;
	}

	public void setSexo(SexoLote sexo) {
		this.sexo = sexo;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public Date getDataAlojamento() {
		return dataAlojamento;
	}

	public void setDataAlojamento(Date dataAlojamento) {
		this.dataAlojamento = dataAlojamento;
	}

	public Integer getMortosTransporte() {
		return mortosTransporte;
	}

	public void setMortosTransporte(Integer mortosTransporte) {
		this.mortosTransporte = mortosTransporte;
	}
}
