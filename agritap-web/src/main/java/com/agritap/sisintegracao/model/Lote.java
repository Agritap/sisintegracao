package com.agritap.sisintegracao.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import com.agritap.sisintegracao.server.rest.BigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Lote {

	private Integer id;
	
	private Modulo modulo;
	
	/**
	 * Mesmo produtor do modulo, colocado aqui para facilitar consultas,
	 * talvez remover
	 */
	private Pessoa produtor;
	
	private Calendar dataMediaAlojamento;
	
	private Calendar dataMediaSaida;
	
	private Boolean ativo;
	
	private AssistenteTecnico tecnico;
	
	private List<Pessoa> granjeirosResponsaveis;
	
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
	
	private SexoLote sexo;
}
