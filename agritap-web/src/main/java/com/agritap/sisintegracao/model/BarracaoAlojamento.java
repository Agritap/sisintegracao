package com.agritap.sisintegracao.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class BarracaoAlojamento {
	private Integer id;

	private Lote lote;

	private Integer quantidade;
	
	private Calendar dataAlojamento;
	
	private Integer idadeAlojamento;
	
	private BigDecimal pesoAlojado;

	private List<OrigemAlojamento> origens;
	
	private Barracao barracao;
}
