package com.agritap.sisintegracao.client.vo;

import java.util.List;

public class  ListAdapter<T> {

	public List<T> resultado;
	
	public ListAdapter(){
		
	}

	public ListAdapter(List<T> r){
		this.resultado=r;
	}

	public List<T> getResultado() {
		return resultado;
	}

	public void setResultado(List<T> resultado) {
		this.resultado = resultado;
	}
}
