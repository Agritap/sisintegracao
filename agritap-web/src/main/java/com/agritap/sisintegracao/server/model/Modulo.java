package com.agritap.sisintegracao.server.model;

import java.util.List;

public class Modulo {
	
	private Integer id;
	
	Animal tipoAnimal;
	
	Produtor produtor;
	
	Integer alojamentoMaximo;

	String rotulo;

	private List<Barracao> barracoes;
}
