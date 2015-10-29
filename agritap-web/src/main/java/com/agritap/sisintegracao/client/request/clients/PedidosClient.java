package com.agritap.sisintegracao.client.request.clients;

import com.agritap.sisintegracao.client.request.Callback;
import com.agritap.sisintegracao.client.request.RESTClient;
import com.agritap.sisintegracao.client.request.beans.PedidosI;
import com.agritap.sisintegracao.client.request.beans.PedidosIAdapter;

public class PedidosClient extends RESTClient {

	public PedidosClient() {
	}

	public void get(Integer id, Callback<PedidosI> cb) {
		GET("pedidos", id.toString()).withCustomReturn(PedidosI.TYPE).go(cb);
	}

	public void todos(Callback<PedidosIAdapter> cb) {
		GET("pedidos", "todos").withCustomReturn(PedidosIAdapter.TYPE).go(cb);
	}

	public void update(PedidosI pedidos, Callback<PedidosI> cb) {
		PUT("pedidos").withCustomReturn(PedidosI.TYPE).withJsonContentType().withEntityBody(pedidos).go(cb);
	}

	public void delete(Integer id, Callback<Void> cb) {
		DELETE("pedidos", id.toString()).go(cb);
	}

}
