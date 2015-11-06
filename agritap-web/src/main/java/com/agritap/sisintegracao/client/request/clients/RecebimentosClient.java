package com.agritap.sisintegracao.client.request.clients;

import com.agritap.sisintegracao.client.request.Callback;
import com.agritap.sisintegracao.client.request.RESTClient;
import com.agritap.sisintegracao.client.request.beans.RecebimentoI;
import com.agritap.sisintegracao.client.request.beans.RecebimentoIAdapter;

public class RecebimentosClient extends RESTClient {

	
	public RecebimentosClient(){
	}
	
	public void get(Integer id,Callback<RecebimentoI> cb) {
		GET("recebimentos",id.toString()).withCustomReturn(RecebimentoI.TYPE).go(cb);
	}

	public void todos(Callback<RecebimentoIAdapter> cb) {
		GET("recebimentos","todos").withCustomReturn(RecebimentoIAdapter.TYPE).go(cb);
	}

	public void update(RecebimentoI recebimento, Callback<RecebimentoI> cb) {
		PUT("recebimentos").withCustomReturn(RecebimentoI.TYPE).withJsonContentType().withEntityBody(recebimento).go(cb);
	}

	public void delete(Integer id,Callback<Void> cb) {
		DELETE("recebimentos",id.toString()).go(cb);
	}
	
}
