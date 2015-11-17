package com.agritap.sisintegracao.client.request.beans;

import java.util.Date;

import com.agritap.sisintegracao.client.request.EntityFactory;
import com.agritap.sisintegracao.model.Fase;
import com.agritap.sisintegracao.model.TipoAnimal;

public interface TipoRacaoI {

	public static String TYPE = EntityFactory.PREFIX + "tipoRacao+json";

	public Integer getId() ;

	public void setId(Integer id) ;

	public String getNome() ;

	public void setNome(String nome);

	public Boolean getMedicada() ;

	public void setMedicada(Boolean medicada);

	public Integer getCarencia() ;

	public void setCarencia(Integer carencia);

	public Date getDataInicio();

	public void setDataInicio(Date dataInicio);

	public Date getDataFim();

	public void setDataFim(Date dataFim) ;

	public String getTipoAnimal();
	
	public void setTipoAnimal(String tipoAnimal);
	
	public Fase getFase();

	public void setFase(Fase fase) ;

	

	

}
