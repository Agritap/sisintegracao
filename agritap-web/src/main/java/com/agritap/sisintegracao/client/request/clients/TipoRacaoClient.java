package com.agritap.sisintegracao.client.request.clients;

import com.agritap.sisintegracao.client.request.Callback;
import com.agritap.sisintegracao.client.request.RESTClient;
import com.agritap.sisintegracao.client.request.beans.TipoRacaoI;
import com.agritap.sisintegracao.client.request.beans.TipoRacaoIAdapter;

public class TipoRacaoClient extends RESTClient {

	public TipoRacaoClient() {
	}

	public void get(Integer id, Callback<TipoRacaoI> cb) {
		GET("tipoRacao", id.toString()).withCustomReturn(TipoRacaoI.TYPE).go(cb);
	}

	public void todos(Callback<TipoRacaoIAdapter> cb) {
		GET("tipoRacao", "todos").withCustomReturn(TipoRacaoIAdapter.TYPE).go(cb);
	}

	public void update(TipoRacaoI TipoRacao, Callback<TipoRacaoI> cb) {
		PUT("tipoRacao").withCustomReturn(TipoRacaoI.TYPE).withJsonContentType().withEntityBody(TipoRacao).go(cb);
	}

	public void delete(Integer id, Callback<Void> cb) {
		DELETE("tipoRacao", id.toString()).go(cb);
	}

}
