package com.agritap.sisintegracao.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	private Date dataMediaAlojamento;
	
	@Temporal(TemporalType.DATE)
	private Date dataMediaSaida;
	
	private Boolean encerrado;
	
	@ManyToOne
	private Pessoa tecnico;
	
	@ManyToOne
	private Pessoa granjeiroResponsavel;
	
	@OneToMany(mappedBy="lote")
	private List<OrigemAlojamento> origens;
	
	/**
	 * Campos calculados para facilitar as buscas ou para suprir quando detalhamentos nao
	 * forem disponiveis.
	 * Quando houver informacao sobre as origens nao informar esses campos na tela
	 */
	private Integer quantidadeAlojada;
	

	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal pesoTotalVivoAlojado;

	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal pesoTotalCarcacaAlojado;

	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal pesoMedioVivoAlojado;

	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal pesoMedioCarcacaAlojado;

	private Integer idadeMediaAlojada;
	
	@Enumerated(EnumType.STRING)
	private SexoLote sexo;

	@Enumerated(EnumType.STRING)
	private TipoOrigem tipoOrigem;

	private Integer quantidadeOrigemDistinta;
	
	@OneToOne(mappedBy="lote")
	private DadosEncerramentoLote encerramento;

	@OneToMany
	private List<LoteAnomalia> anomalias;
	
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

	public Pessoa getTecnico() {
		return tecnico;
	}

	public void setTecnico(Pessoa tecnico) {
		this.tecnico = tecnico;
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

	public Boolean getEncerrado() {
		return encerrado;
	}

	public void setEncerrado(Boolean encerrado) {
		this.encerrado = encerrado;
	}

	public Date getDataMediaAlojamento() {
		return dataMediaAlojamento;
	}

	public void setDataMediaAlojamento(Date dataMediaAlojamento) {
		this.dataMediaAlojamento = dataMediaAlojamento;
	}

	public Date getDataMediaSaida() {
		return dataMediaSaida;
	}

	public void setDataMediaSaida(Date dataMediaSaida) {
		this.dataMediaSaida = dataMediaSaida;
	}

	public BigDecimal getPesoMedioVivoAlojado() {
		return pesoMedioVivoAlojado;
	}

	public void setPesoMedioVivoAlojado(BigDecimal pesoMedioVivoAlojado) {
		this.pesoMedioVivoAlojado = pesoMedioVivoAlojado;
	}

	public BigDecimal getPesoMedioCarcacaAlojado() {
		return pesoMedioCarcacaAlojado;
	}

	public void setPesoMedioCarcacaAlojado(BigDecimal pesoMedioCarcacaAlojado) {
		this.pesoMedioCarcacaAlojado = pesoMedioCarcacaAlojado;
	}

	public DadosEncerramentoLote getEncerramento() {
		return encerramento;
	}

	public void setEncerramento(DadosEncerramentoLote encerramento) {
		this.encerramento = encerramento;
	}

	public BigDecimal getPesoTotalVivoAlojado() {
		return pesoTotalVivoAlojado;
	}

	public void setPesoTotalVivoAlojado(BigDecimal pesoTotalVivoAlojado) {
		this.pesoTotalVivoAlojado = pesoTotalVivoAlojado;
	}

	public BigDecimal getPesoTotalCarcacaAlojado() {
		return pesoTotalCarcacaAlojado;
	}

	public void setPesoTotalCarcacaAlojado(BigDecimal pesoTotalCarcacaAlojado) {
		this.pesoTotalCarcacaAlojado = pesoTotalCarcacaAlojado;
	}

	public List<LoteAnomalia> getAnomalias() {
		return anomalias;
	}

	public void setAnomalias(List<LoteAnomalia> anomalias) {
		this.anomalias = anomalias;
	}

	public Pessoa getGranjeiroResponsavel() {
		return granjeiroResponsavel;
	}

	public void setGranjeiroResponsavel(Pessoa granjeiroResponsavel) {
		this.granjeiroResponsavel = granjeiroResponsavel;
	}

	public Integer getIdadeMediaAlojada() {
		return idadeMediaAlojada;
	}

	public void setIdadeMediaAlojada(Integer idadeMediaAlojada) {
		this.idadeMediaAlojada = idadeMediaAlojada;
	}
}
