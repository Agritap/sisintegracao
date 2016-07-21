package com.agritap.sisintegracao.client.ui.configuracao;

import java.util.List;

import com.agritap.sisintegracao.model.Lote;
import com.agritap.sisintegracao.model.Pessoa;

public class LoteViewVO {

	Lote lote;
	
	List<Pessoa> produtores;
	
	List<Pessoa> tecnicos;

	List<Pessoa> granjeiros;

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public List<Pessoa> getProdutores() {
		return produtores;
	}

	public void setProdutores(List<Pessoa> produtores) {
		this.produtores = produtores;
	}

	public List<Pessoa> getTecnicos() {
		return tecnicos;
	}

	public void setTecnicos(List<Pessoa> tecnicos) {
		this.tecnicos = tecnicos;
	}

	public List<Pessoa> getGranjeiros() {
		return granjeiros;
	}

	public void setGranjeiros(List<Pessoa> granjeiros) {
		this.granjeiros = granjeiros;
	}
	
}
