package com.agritap.sisintegracao.client;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gwt.dev.util.collect.HashMap;

public class ValidacaoException extends RuntimeException {

	private static final long serialVersionUID = 2170875887720924852L;

	private Map<String/*Nome do Campo*/,String/*Mensagem de Erro*/> errosFields=new HashMap<>();

	private List<String> errosGenericos=new LinkedList<>();

	public void addErro(String campo,String erro){
		errosFields.put(campo, erro);
	}
	
	public void addErro(String erro){
		errosGenericos.add(erro);
	}
	
	public List<String> getAllErros(){
		List<String> erros = new LinkedList<>();
		erros.addAll(errosFields.values());
		erros.addAll(errosGenericos);
		return erros;
	}

	public List<String> getErrosGenericos() {
		return errosGenericos;
	}

	public void setErrosGenericos(List<String> errosGenericos) {
		this.errosGenericos = errosGenericos;
	}

	public Map<String, String> getErrosFields() {
		return errosFields;
	}

	public void setErrosFields(Map<String, String> errosFields) {
		this.errosFields = errosFields;
	}
	
}
