package com.agritap.sisintegracao.client.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.agritap.sisintegracao.model.Lote;
import com.agritap.sisintegracao.model.TipoOrigem;

public class LoteVO {

	private Integer id;
	
	private String produtor;

	private String granjeiros;
	
	private TipoOrigem tipoOrigem;
	
	private Integer quantidadeOrigemDistinta;

	private Date quantidadeAlojada;
	
	private Date dataAlojamento;
	
	private Date dataSaida;
	
	private Date vencimento;
	
	private Boolean encerrado;
	
	private Integer diasAlojados;
	
	
	private BigDecimal pesoMedioVivoAlojado;
	
	private BigDecimal valorMedioAnimalPago;
	
	private BigDecimal pesoMedioVivoEntregue;

	private Integer mortalidadeBruta;

	private BigDecimal mortalidadePercentual;

	public LoteVO(){}

	public LoteVO(Lote lote){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProdutor() {
		return produtor;
	}

	public void setProdutor(String produtor) {
		this.produtor = produtor;
	}


	public Date getQuantidadeAlojada() {
		return quantidadeAlojada;
	}

	public void setQuantidadeAlojada(Date quantidadeAlojada) {
		this.quantidadeAlojada = quantidadeAlojada;
	}

	public Date getDataAlojamento() {
		return dataAlojamento;
	}

	public void setDataAlojamento(Date dataAlojamento) {
		this.dataAlojamento = dataAlojamento;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Boolean getEncerrado() {
		return encerrado;
	}

	public void setEncerrado(Boolean encerrado) {
		this.encerrado = encerrado;
	}

	public BigDecimal getPesoMedioVivoAlojado() {
		return pesoMedioVivoAlojado;
	}

	public void setPesoMedioVivoAlojado(BigDecimal pesoMedioVivoAlojado) {
		this.pesoMedioVivoAlojado = pesoMedioVivoAlojado;
	}

	public BigDecimal getValorMedioAnimalPago() {
		return valorMedioAnimalPago;
	}

	public void setValorMedioAnimalPago(BigDecimal valorMedioAnimalPago) {
		this.valorMedioAnimalPago = valorMedioAnimalPago;
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

	public Integer getDiasAlojados() {
		return diasAlojados;
	}

	public void setDiasAlojados(Integer diasAlojados) {
		this.diasAlojados = diasAlojados;
	}

	public BigDecimal getPesoMedioVivoEntregue() {
		return pesoMedioVivoEntregue;
	}

	public void setPesoMedioVivoEntregue(BigDecimal pesoMedioVivoEntregue) {
		this.pesoMedioVivoEntregue = pesoMedioVivoEntregue;
	}

	public Integer getMortalidadeBruta() {
		return mortalidadeBruta;
	}

	public void setMortalidadeBruta(Integer mortalidadeBruta) {
		this.mortalidadeBruta = mortalidadeBruta;
	}

	public BigDecimal getMortalidadePercentual() {
		return mortalidadePercentual;
	}

	public void setMortalidadePercentual(BigDecimal mortalidadePercentual) {
		this.mortalidadePercentual = mortalidadePercentual;
	}

	public String getGranjeiros() {
		return granjeiros;
	}

	public void setGranjeiros(String granjeiros) {
		this.granjeiros = granjeiros;
	}

}
