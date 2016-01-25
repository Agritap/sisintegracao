package com.agritap.sisintegracao.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.agritap.sisintegracao.server.rest.BigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Lote {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Modulo modulo;
	
	/**
	 * Mesmo produtor do modulo, colocado aqui para facilitar consultas,
	 * talvez remover
	 */
	@ManyToOne
	private Pessoa produtor;

	@Temporal(TemporalType.DATE)
	private Calendar dataMediaAlojamento;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataMediaSaida;
	
	private Boolean ativo;
	
	@ManyToOne
	private Pessoa tecnico;
	
	@ManyToMany
	private List<Pessoa> granjeirosResponsaveis;
	
	@OneToMany(mappedBy="lote")
	private List<OrigemAlojamento> origens;
	
	/**
	 * Campos calculados para facilitar as buscas ou para suprir quando detalhamentos nao
	 * forem disponiveis.
	 * Quando houver informacao sobre as origens nao informar esses campos na tela
	 */
	private Integer quantidadeAlojada;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal pesoMedioAlojado;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal idadeMediaAlojada;
	
	@Enumerated(EnumType.STRING)
	private SexoLote sexo;

	@Enumerated(EnumType.STRING)
	private TipoOrigem tipoOrigem;

	private Integer quantidadeOrigemDistinta;

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

	public Pessoa getProdutor() {
		return produtor;
	}

	public void setProdutor(Pessoa produtor) {
		this.produtor = produtor;
	}

	public Calendar getDataMediaAlojamento() {
		return dataMediaAlojamento;
	}

	public void setDataMediaAlojamento(Calendar dataMediaAlojamento) {
		this.dataMediaAlojamento = dataMediaAlojamento;
	}

	public Calendar getDataMediaSaida() {
		return dataMediaSaida;
	}

	public void setDataMediaSaida(Calendar dataMediaSaida) {
		this.dataMediaSaida = dataMediaSaida;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Pessoa getTecnico() {
		return tecnico;
	}

	public void setTecnico(Pessoa tecnico) {
		this.tecnico = tecnico;
	}

	public List<Pessoa> getGranjeirosResponsaveis() {
		return granjeirosResponsaveis;
	}

	public void setGranjeirosResponsaveis(List<Pessoa> granjeirosResponsaveis) {
		this.granjeirosResponsaveis = granjeirosResponsaveis;
	}

	public List<OrigemAlojamento> getOrigens() {
		return origens;
	}

	public void setOrigens(List<OrigemAlojamento> origens) {
		this.origens = origens;
	}

	public Integer getQuantidadeAlojada() {
		return quantidadeAlojada;
	}

	public void setQuantidadeAlojada(Integer quantidadeAlojada) {
		this.quantidadeAlojada = quantidadeAlojada;
	}

	public BigDecimal getPesoMedioAlojado() {
		return pesoMedioAlojado;
	}

	public void setPesoMedioAlojado(BigDecimal pesoMedioAlojado) {
		this.pesoMedioAlojado = pesoMedioAlojado;
	}

	public BigDecimal getIdadeMediaAlojada() {
		return idadeMediaAlojada;
	}

	public void setIdadeMediaAlojada(BigDecimal idadeMediaAlojada) {
		this.idadeMediaAlojada = idadeMediaAlojada;
	}

	public SexoLote getSexo() {
		return sexo;
	}

	public void setSexo(SexoLote sexo) {
		this.sexo = sexo;
	}

	public TipoOrigem getTipoOrigem() {
		return tipoOrigem;
	}

	public void setTipoOrigem(TipoOrigem tipoOrigem) {
		this.tipoOrigem = tipoOrigem;
	}

	public Integer getQuantidadeOrigemDistinta() {
		return quantidadeOrigemDistinta;
	}

	public void setQuantidadeOrigemDistinta(Integer quantidadeOrigemDistinta) {
		this.quantidadeOrigemDistinta = quantidadeOrigemDistinta;
	}
}
