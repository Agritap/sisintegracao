package com.agritap.sisintegracao.client.request.clients;

import com.agritap.sisintegracao.client.request.Callback;
import com.agritap.sisintegracao.client.request.RESTClient;
import com.agritap.sisintegracao.client.request.beans.TecnicoI;
import com.agritap.sisintegracao.client.request.beans.TecnicoIAdapter;


public class TecnicoClient extends RESTClient {
	
	public TecnicoClient(){
	}
	
	public void get(Integer id,Callback<TecnicoI> cb) {
		GET("tecnicos",id.toString()).withCustomReturn(TecnicoI.TYPE).go(cb);
	}

	public void todos(Callback<TecnicoIAdapter> cb) {
		GET("tecnicos","todos").withCustomReturn(TecnicoIAdapter.TYPE).go(cb);
	}

	public void update(TecnicoI tecnico, Callback<TecnicoI> cb) {
		PUT("tecnicos").withCustomReturn(TecnicoI.TYPE).withJsonContentType().withEntityBody(tecnico).go(cb);
	}

	public void delete(Integer id,Callback<Void> cb) {
		DELETE("tecnicos",id.toString()).go(cb);
	}
	
}

