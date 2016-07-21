package com.agritap.sisintegracao.client.vo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("todosErros")
public class ErrosValidacao implements Serializable{
	
	private static final long serialVersionUID = 98579593821316705L;

	private Map<String/*Nome do Campo*/,String/*Mensagem de Erro*/> errosFields;

	private List<String> errosGenericos;

	public Map<String, String> getErrosFields() {
		return errosFields;
	}

	public void setErrosFields(Map<String, String> errosFields) {
		this.errosFields = errosFields;
	}

	public List<String> getErrosGenericos() {
		return errosGenericos;
	}

	public void setErrosGenericos(List<String> errosGenericos) {
		this.errosGenericos = errosGenericos;
	}
	public List<String> getTodosErros() {
		List<String> resultado = new LinkedList<>();
		if(errosGenericos!=null){
			resultado.addAll(errosGenericos);
		}
		if(errosFields!=null){
			for(String chave:errosFields.keySet()){
				resultado.add(errosFields.get(chave));
			}
		}
		return resultado;
	}

}
