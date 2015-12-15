package com.agritap.sisintegracao.model;

import java.math.BigDecimal;
import java.util.Calendar;

import com.agritap.sisintegracao.server.rest.BigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class OrigemAlojamento {

	private Integer id;
	
	private Pessoa produtor;
	
	private Integer quantidade;
	
	private Calendar dataAlojamento;
	
	private Integer idadeAlojamento;
	
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal pesoAlojado;
	
	private SexoLote sexo;
}
