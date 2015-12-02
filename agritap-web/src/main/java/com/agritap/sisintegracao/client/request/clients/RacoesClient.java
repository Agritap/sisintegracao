package com.agritap.sisintegracao.client.request.clients;

import com.agritap.sisintegracao.client.request.Callback;
import com.agritap.sisintegracao.client.request.RESTClient;
import com.agritap.sisintegracao.client.request.beans.TabelaRacaoI;
import com.agritap.sisintegracao.client.request.beans.TabelaRacaoIAdapter;

public class RacoesClient extends RESTClient {

	
	public RacoesClient(){
	}

	public void todos(Callback<TabelaRacaoIAdapter> cb) {
		GET("racoes", "tabela", "todos").withCustomReturn(TabelaRacaoIAdapter.TYPE).go(cb);
	}

	public void update(TabelaRacaoI tabelaRacao, Callback<TabelaRacaoI> cb) {
		PUT("tabelaRacao").withCustomReturn(TabelaRacaoI.TYPE).withJsonContentType().withEntityBody(tabelaRacao).go(cb);
	}

	public void delete(Integer id,Callback<Void> cb) {
		DELETE("tabelaRacao",id.toString()).go(cb);
	}
	
}

