package com.agritap.sisintegracao.client.request.beans;

import java.util.List;

public interface ListAdapterI<T> {
	
	
	public List<T> getResultado();
	public void setResultado(List<T> p);

}
