package com.agritap.sisintegracao.model;

import java.util.List;

public class Modulo {
	
	private Integer id;
	
	TipoAnimal tipoAnimal;
	
	Pessoa produtor;
	
	Integer alojamentoMaximo;

	String rotulo;

	private List<Barracao> barracoes;
}
