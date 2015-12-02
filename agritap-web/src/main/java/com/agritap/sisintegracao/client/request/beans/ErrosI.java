package com.agritap.sisintegracao.client.request.beans;

import java.util.List;
import java.util.Map;

import com.agritap.sisintegracao.client.request.EntityFactory;

public interface ErrosI {
	public static String TYPE = EntityFactory.PREFIX + "erros+json";
	
	public Map<String, String> getErrosFields();

	public void setErrosFields(Map<String, String> errosFields) ;

	public List<String> getErrosGenericos() ;

	public void setErrosGenericos(List<String> errosGenericos) ;
}
