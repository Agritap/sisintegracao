package com.agritap.sisintegracao.client.request.beans;

import com.agritap.sisintegracao.client.request.EntityFactory;

public interface TecnicoIAdapter extends ListAdapterI<TecnicoI> {

	public static String TYPE = EntityFactory.PREFIX + "tecnicoAdapter+json";

}