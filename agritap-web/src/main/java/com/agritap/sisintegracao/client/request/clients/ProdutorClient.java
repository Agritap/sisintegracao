package com.agritap.sisintegracao.client.request.clients;

import com.agritap.sisintegracao.client.request.Callback;
import com.agritap.sisintegracao.client.request.RESTClient;
import com.agritap.sisintegracao.client.request.beans.ProdutorI;
import com.agritap.sisintegracao.client.request.beans.ProdutorIAdapter;

public class ProdutorClient extends RESTClient {

	
	public ProdutorClient(){
	}
	
	public void get(Integer id,Callback<ProdutorI> cb) {
		GET("produtores",id.toString()).withCustomReturn(ProdutorI.TYPE).go(cb);
	}

	public void todos(Callback<ProdutorIAdapter> cb) {
		GET("produtores","todos").withCustomReturn(ProdutorIAdapter.TYPE).go(cb);
	}

	public void update(ProdutorI produtor, Callback<ProdutorI> cb) {
		PUT("produtores").withCustomReturn(ProdutorI.TYPE).withJsonContentType().withEntityBody(produtor).go(cb);
	}

	public void delete(Integer id,Callback<Void> cb) {
		DELETE("produtores",id.toString()).go(cb);
	}
	
}
