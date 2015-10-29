package com.agritap.sisintegracao.client.request.beans;

import com.agritap.sisintegracao.client.request.EntityFactory;

public interface PedidosIAdapter extends ListAdapterI<PedidosI>{
	
	public static String TYPE = EntityFactory.PREFIX + "pedidosAdapter+json";

}
