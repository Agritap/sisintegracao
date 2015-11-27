package com.agritap.sisintegracao.client.request.beans;

import com.agritap.sisintegracao.client.request.EntityFactory;

public interface PessoaIAdapter extends ListAdapterI<PessoaI> {

	public static String TYPE = EntityFactory.PREFIX + "pessoaAdapter+json";

}
