package com.agritap.sisintegracao.client.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class Lote {

	private Integer id;
	
	private Modulo modulo;
	
	/**
	 * Mesmo produtor do modulo, colocado aqui para facilitar consultas,
	 * talvez remover
	 */
	private Produtor produtor;
	
	private Calendar dataMediaAlojamento;
	
	private Calendar dataMediaSaida;
	
	private Boolean ativo;
	
	private AssistenteTecnico tecnico;
	
	private List<Granjeiro> granjeirosResponsaveis;
	
	private List<OrigemAlojamento> origens;
	
	/**
	 * Campos calculados para facilitar as buscas ou para suprir quando detalhamentos nao
	 * forem disponiveis.
	 * Quando houver informacao sobre as origens nao informar esses campos na tela
	 */
	private Integer quantidadeAlojada;
	
	private BigDecimal pesoMedioAlojado;
	
	private BigDecimal idadeMediaAlojada;
	
	private SexoLote sexo;
}
