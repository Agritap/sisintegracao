package com.agritap.sisintegracao.client.request.beans;

import com.agritap.sisintegracao.client.request.EntityFactory;
import com.agritap.sisintegracao.model.Barracao;
import com.agritap.sisintegracao.model.TipoAnimal;

public interface ModulosI {

	public static String TYPE = EntityFactory.PREFIX + "modulos +json";
	

	public Integer getId();

	public void setId(Integer id);

	public String getNome();

	public void setNome(String nome);

	public TipoAnimal getTipoAnimal();

	public void setTipoAnimal(TipoAnimal tipoAnimal);

	public String getBarracao();

	public void setBarracao(Barracao barracao);

}
