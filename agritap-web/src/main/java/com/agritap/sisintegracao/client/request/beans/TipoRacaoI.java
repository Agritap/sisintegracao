package com.agritap.sisintegracao.client.request.beans;

import org.gwtbootstrap3.client.ui.TextBox;

import com.agritap.sisintegracao.client.request.EntityFactory;

public interface TipoRacaoI {

	public static String TYPE = EntityFactory.PREFIX + "tipoRacao+json";

	public Integer getId();

	public String getNome();

	public void setNome(String nome);

	public String getEmail();

	public void setEmail(String email);

	public Boolean getAtivo();

	public void setAtivo(Boolean ativo);

	public String getTelefone();

	public void setTelefone(String telefone);

	public String getCodigoIntegradora();

	public void setCodigoIntegradora(String codigoIntegradora);

	public Integer getVersion();

	public void setVersion(Integer version);

	public String getDataInicio();

	public String getDataFim();

	public String getCarencia();

	public String getPorco();

	public String getMedicada();

	public String getTipoAnimal();

	public void setCarencia(String value);

	public void setDataFim(String value);

	public void setDataInicio(String value);

	public void setMedicada(String value);

	public void setTipoAnimal(String value);

}
