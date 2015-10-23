package com.agritap.sisintegracao.client.request;

import java.util.List;

public class ProdutorClient extends RESTClient {

	Integer id;
	public ProdutorClient(Integer id){
		this.id=id;
	}
	
	public void get(Callback<ProdutorI> cb) {
		GET("produtores",id.toString()).addHeader("agritap-custom-type", ProdutorI.TYPE).go(cb);
	}

	public void todos(Callback<List<ProdutorI>> cb) {
		GET("produtores","todos").go(cb);
	}

	public void update(ProdutorI course, Callback<ProdutorI> cb) {
		PUT("produtores").addHeader("agritap-custom-type", ProdutorI.TYPE).withContentType("application/json").withEntityBody(course).go(cb);
	}

	public void delete(Callback<ProdutorI> cb) {
		DELETE("produtores",id.toString()).go(cb);
	}
	
}
