package com.agritap.sisintegracao.client.request.clients;

import com.agritap.sisintegracao.client.request.Callback;
import com.agritap.sisintegracao.client.request.RESTClient;
import com.agritap.sisintegracao.client.request.beans.ModulosI;
import com.agritap.sisintegracao.client.request.beans.ModulosIAdapter;

public class ModulosClient extends RESTClient {

	public ModulosClient() {
	}

	public void get(Integer id, Callback<ModulosI> cb) {
		GET("modulos", id.toString()).withCustomReturn(ModulosI.TYPE).go(cb);
	}

	public void todos(Callback<ModulosIAdapter> cb) {
		GET("modulos", "todos").withCustomReturn(ModulosIAdapter.TYPE).go(cb);
	}

	public void porProdutor(Integer produtorId,Callback<ModulosIAdapter> cb) {
		GET("modulos", "produtor",produtorId.toString()).withCustomReturn(ModulosIAdapter.TYPE).go(cb);
	}

	public void update(ModulosI modulos, Callback<ModulosI> cb) {
		PUT("modulos").withCustomReturn(ModulosI.TYPE).withJsonContentType().withEntityBody(modulos).go(cb);
	}

	public void delete(Integer id, Callback<Void> cb) {
		DELETE("modulos", id.toString()).go(cb);
	}

}
