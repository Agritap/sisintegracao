package com.agritap.sisintegracao.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DadosEncerramentoLote {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	private Lote lote;
	
	private Integer mortalidade;
	
	private Integer quantidadeAbatida;
	
	private Integer racaoConsumida;
	
	private BigDecimal pesoTotalVivoAbatido;
	
	private BigDecimal pesoTotalCarcacaAbatido;

	private BigDecimal pesoMedioVivoAbatido;
	
	private BigDecimal pesoMedioCarcacaAbatido;

	private Integer idadeMediaFinal;
	
	private BigDecimal conversaoAlimentarCarcacaAjustadaPrevista;
	
	private BigDecimal mortalidadeAjustadaPrevista;
	
	private BigDecimal conversaoAlimentarCarcacaAjustadaRealizada;
	
	private BigDecimal mortalidadeAjustadaRealizada;

	private BigDecimal percentualBaseIntegrado;
	
	private BigDecimal percentualAjusteMortalidade;
	
	private BigDecimal percentualAjusteConversaoAlimentar;

	private BigDecimal percentualIntegradoFinal;
	
	private BigDecimal totalCarneProduzida;
	
	private BigDecimal valorBaseKg;
	
	private BigDecimal rendaBrutaLote;
	
	private BigDecimal rendaBrutaSuino;
	
	private BigDecimal outrosCreditos;

	private BigDecimal outrosDebitos;

	private BigDecimal descontoFalta;

	private BigDecimal descontoFunrural;

	private BigDecimal consideracaoTecnica;
	
	private BigDecimal consideracaoSanitaria;

	private BigDecimal rendaLiquidaTotal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public Integer getMortalidade() {
		return mortalidade;
	}

	public void setMortalidade(Integer mortalidade) {
		this.mortalidade = mortalidade;
	}

	public Integer getQuantidadeAbatida() {
		return quantidadeAbatida;
	}

	public void setQuantidadeAbatida(Integer quantidadeAbatida) {
		this.quantidadeAbatida = quantidadeAbatida;
	}

	public Integer getRacaoConsumida() {
		return racaoConsumida;
	}

	public void setRacaoConsumida(Integer racaoConsumida) {
		this.racaoConsumida = racaoConsumida;
	}

	public BigDecimal getPesoTotalVivoAbatido() {
		return pesoTotalVivoAbatido;
	}

	public void setPesoTotalVivoAbatido(BigDecimal pesoTotalVivoAbatido) {
		this.pesoTotalVivoAbatido = pesoTotalVivoAbatido;
	}

	public BigDecimal getPesoTotalCarcacaAbatido() {
		return pesoTotalCarcacaAbatido;
	}

	public void setPesoTotalCarcacaAbatido(BigDecimal pesoTotalCarcacaAbatido) {
		this.pesoTotalCarcacaAbatido = pesoTotalCarcacaAbatido;
	}

	public BigDecimal getPesoMedioVivoAbatido() {
		return pesoMedioVivoAbatido;
	}

	public void setPesoMedioVivoAbatido(BigDecimal pesoMedioVivoAbatido) {
		this.pesoMedioVivoAbatido = pesoMedioVivoAbatido;
	}

	public BigDecimal getPesoMedioCarcacaAbatido() {
		return pesoMedioCarcacaAbatido;
	}

	public void setPesoMedioCarcacaAbatido(BigDecimal pesoMedioCarcacaAbatido) {
		this.pesoMedioCarcacaAbatido = pesoMedioCarcacaAbatido;
	}

	public Integer getIdadeMediaFinal() {
		return idadeMediaFinal;
	}

	public void setIdadeMediaFinal(Integer idadeMediaFinal) {
		this.idadeMediaFinal = idadeMediaFinal;
	}

	public BigDecimal getConversaoAlimentarCarcacaAjustadaPrevista() {
		return conversaoAlimentarCarcacaAjustadaPrevista;
	}

	public void setConversaoAlimentarCarcacaAjustadaPrevista(BigDecimal conversaoAlimentarCarcacaAjustadaPrevista) {
		this.conversaoAlimentarCarcacaAjustadaPrevista = conversaoAlimentarCarcacaAjustadaPrevista;
	}

	public BigDecimal getMortalidadeAjustadaPrevista() {
		return mortalidadeAjustadaPrevista;
	}

	public void setMortalidadeAjustadaPrevista(BigDecimal mortalidadeAjustadaPrevista) {
		this.mortalidadeAjustadaPrevista = mortalidadeAjustadaPrevista;
	}

	public BigDecimal getConversaoAlimentarCarcacaAjustadaRealizada() {
		return conversaoAlimentarCarcacaAjustadaRealizada;
	}

	public void setConversaoAlimentarCarcacaAjustadaRealizada(BigDecimal conversaoAlimentarCarcacaAjustadaRealizada) {
		this.conversaoAlimentarCarcacaAjustadaRealizada = conversaoAlimentarCarcacaAjustadaRealizada;
	}

	public BigDecimal getMortalidadeAjustadaRealizada() {
		return mortalidadeAjustadaRealizada;
	}

	public void setMortalidadeAjustadaRealizada(BigDecimal mortalidadeAjustadaRealizada) {
		this.mortalidadeAjustadaRealizada = mortalidadeAjustadaRealizada;
	}

	public BigDecimal getPercentualBaseIntegrado() {
		return percentualBaseIntegrado;
	}

	public void setPercentualBaseIntegrado(BigDecimal percentualBaseIntegrado) {
		this.percentualBaseIntegrado = percentualBaseIntegrado;
	}

	public BigDecimal getPercentualAjusteMortalidade() {
		return percentualAjusteMortalidade;
	}

	public void setPercentualAjusteMortalidade(BigDecimal percentualAjusteMortalidade) {
		this.percentualAjusteMortalidade = percentualAjusteMortalidade;
	}

	public BigDecimal getPercentualAjusteConversaoAlimentar() {
		return percentualAjusteConversaoAlimentar;
	}

	public void setPercentualAjusteConversaoAlimentar(BigDecimal percentualAjusteConversaoAlimentar) {
		this.percentualAjusteConversaoAlimentar = percentualAjusteConversaoAlimentar;
	}

	public BigDecimal getPercentualIntegradoFinal() {
		return percentualIntegradoFinal;
	}

	public void setPercentualIntegradoFinal(BigDecimal percentualIntegradoFinal) {
		this.percentualIntegradoFinal = percentualIntegradoFinal;
	}

	public BigDecimal getTotalCarneProduzida() {
		return totalCarneProduzida;
	}

	public void setTotalCarneProduzida(BigDecimal totalCarneProduzida) {
		this.totalCarneProduzida = totalCarneProduzida;
	}

	public BigDecimal getValorBaseKg() {
		return valorBaseKg;
	}

	public void setValorBaseKg(BigDecimal valorBaseKg) {
		this.valorBaseKg = valorBaseKg;
	}

	public BigDecimal getRendaBrutaLote() {
		return rendaBrutaLote;
	}

	public void setRendaBrutaLote(BigDecimal rendaBrutaLote) {
		this.rendaBrutaLote = rendaBrutaLote;
	}

	public BigDecimal getRendaBrutaSuino() {
		return rendaBrutaSuino;
	}

	public void setRendaBrutaSuino(BigDecimal rendaBrutaSuino) {
		this.rendaBrutaSuino = rendaBrutaSuino;
	}

	public BigDecimal getOutrosCreditos() {
		return outrosCreditos;
	}

	public void setOutrosCreditos(BigDecimal outrosCreditos) {
		this.outrosCreditos = outrosCreditos;
	}

	public BigDecimal getOutrosDebitos() {
		return outrosDebitos;
	}

	public void setOutrosDebitos(BigDecimal outrosDebitos) {
		this.outrosDebitos = outrosDebitos;
	}

	public BigDecimal getDescontoFalta() {
		return descontoFalta;
	}

	public void setDescontoFalta(BigDecimal descontoFalta) {
		this.descontoFalta = descontoFalta;
	}

	public BigDecimal getDescontoFunrural() {
		return descontoFunrural;
	}

	public void setDescontoFunrural(BigDecimal descontoFunrural) {
		this.descontoFunrural = descontoFunrural;
	}

	public BigDecimal getConsideracaoTecnica() {
		return consideracaoTecnica;
	}

	public void setConsideracaoTecnica(BigDecimal consideracaoTecnica) {
		this.consideracaoTecnica = consideracaoTecnica;
	}

	public BigDecimal getConsideracaoSanitaria() {
		return consideracaoSanitaria;
	}

	public void setConsideracaoSanitaria(BigDecimal consideracaoSanitaria) {
		this.consideracaoSanitaria = consideracaoSanitaria;
	}

	public BigDecimal getRendaLiquidaTotal() {
		return rendaLiquidaTotal;
	}

	public void setRendaLiquidaTotal(BigDecimal rendaLiquidaTotal) {
		this.rendaLiquidaTotal = rendaLiquidaTotal;
	}

}
