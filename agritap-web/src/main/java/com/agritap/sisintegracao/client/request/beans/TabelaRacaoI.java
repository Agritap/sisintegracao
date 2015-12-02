package com.agritap.sisintegracao.client.request.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.agritap.sisintegracao.client.request.EntityFactory;
import com.agritap.sisintegracao.model.TabelaRacaoItem;

public interface TabelaRacaoI {
	public static String TYPE = EntityFactory.PREFIX + "TabelaRacao+json";
	
	public Integer getId();
	
	public void setId(Integer id);
	
	public Date getInicio();
	
	public void setInicio(Date inicio);
	
	public Date getFim();
	
	public void setFim(Date fim);
	
	public BigDecimal getPesoMinimo() ;
	
	public void setPesoMinimo(BigDecimal pesoMinimo) ;
	
	
}
