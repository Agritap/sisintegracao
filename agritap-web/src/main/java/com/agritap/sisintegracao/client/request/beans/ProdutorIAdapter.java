package com.agritap.sisintegracao.client.request.beans;

import com.agritap.sisintegracao.client.request.EntityFactory;

public interface ProdutorIAdapter extends ListAdapterI<ProdutorI> {

	public static String TYPE = EntityFactory.PREFIX + "produtorAdapter+json";

}
