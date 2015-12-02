package com.agritap.sisintegracao.server.to;

import java.util.List;
import java.util.Map;

public class ErrosTO {
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

}
