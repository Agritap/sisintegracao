package com.agritap.sisintegracao.model;

import java.util.Date;
import java.util.List;

import com.agritap.sisintegracao.server.rest.BigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class BarracaoAlojamento {
	private Integer id;

	private Lote lote;

	private Integer quantidade;
	
	private Date dataAlojamento;
	
	private Integer idadeAlojamento;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private Date pesoAlojado;

	private List<OrigemAlojamento> origens;
	
	private Barracao barracao;
}
