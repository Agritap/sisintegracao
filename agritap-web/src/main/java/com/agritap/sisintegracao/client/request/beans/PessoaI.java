package com.agritap.sisintegracao.client.request.beans;

import com.agritap.sisintegracao.client.request.EntityFactory;
import com.agritap.sisintegracao.model.Integradora;

public interface PessoaI {
	
	public static String TYPE = EntityFactory.PREFIX + "pessoa+json";

	public Integer getId();

	public void setId(Integer id) ;

	public Integradora getIntegradora() ;

	public void setIntegradora(Integradora integradora) ;

	public String getNome() ;

	public void setNome(String nome);

	public String getCodigoIntegradora() ;

	public void setCodigoIntegradora(String codigoIntegradora) ;

	public String getEmail() ;

	public void setEmail(String email) ;

	public Boolean getAtivo() ;

	public void setAtivo(Boolean ativo) ;

	public String getTelefone() ;

	public void setTelefone(String telefone) ;

	public Integer getVersion() ;

	public void setVersion(Integer version) ;

	public String getCpf() ;

	public void setCpf(String cpf) ;

	public Boolean getGranjeiro() ;

	public void setGranjeiro(Boolean granjeiro) ;

	public Boolean getProdutor() ;

	public void setProdutor(Boolean produtor);

	public Boolean getTecnico() ;

	public void setTecnico(Boolean tecnico) ;

	public String getApelido() ;

	public void setApelido(String apelido) ;
}
