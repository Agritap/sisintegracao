package com.agritap.sisintegracao.client.request.beans;

import com.agritap.sisintegracao.client.request.EntityFactory;

public interface RecebimentoIAdapter extends ListAdapterI<RecebimentoI> {

	public static String TYPE = EntityFactory.PREFIX + "recebimentoAdapter+json";

}
