package com.agritap.sisintegracao.client.request.clients;

import com.agritap.sisintegracao.client.request.Callback;
import com.agritap.sisintegracao.client.request.RESTClient;
import com.agritap.sisintegracao.client.request.beans.ProdutorI;
import com.agritap.sisintegracao.client.request.beans.ProdutorIAdapter;

public class ProdutorClient extends RESTClient {

	Integer id;
	
	public ProdutorClient(Integer id){
		this.id=id;
	}
	
	public void get(Callback<ProdutorI> cb) {
		GET("produtores",id.toString()).withCustomReturn(ProdutorI.TYPE).go(cb);
	}

	public void todos(Callback<ProdutorIAdapter> cb) {
		GET("produtores","todos").withCustomReturn(ProdutorIAdapter.TYPE).go(cb);
	}

	public void update(ProdutorI course, Callback<ProdutorI> cb) {
		PUT("produtores").withCustomReturn(ProdutorI.TYPE).withJsonContentType().withEntityBody(course).go(cb);
	}

	public void delete(Callback<ProdutorI> cb) {
		DELETE("produtores",id.toString()).go(cb);
	}
	
}
