package com.agritap.sisintegracao.client.request.clients;

import com.agritap.sisintegracao.client.request.Callback;
import com.agritap.sisintegracao.client.request.ExceptionalRequestBuilder;
import com.agritap.sisintegracao.client.request.RESTClient;
import com.agritap.sisintegracao.client.request.beans.PessoaI;
import com.agritap.sisintegracao.client.request.beans.PessoaIAdapter;
import com.agritap.sisintegracao.client.request.beans.UsuarioI;

public class PessoaClient extends RESTClient {

	public PessoaClient() {
	}

	public void auth(String usuario, String senha, Callback<UsuarioI> cb) {
//		iss_username=ss&iss_password=s&loginFailed=loginInvalido
		String formBody = "login=" + usuario + "&password=" + senha ;
//		String jsonBody = "{ \"login\": \"" + usuario + "\",\"password\": \"" + senha + "\"}";
		ExceptionalRequestBuilder reqB = POST("pessoas", "auth").withCustomReturn(UsuarioI.TYPE);
		reqB.setRequestData(formBody );
		reqB.go(cb);
	}

	public void get(Integer id, Callback<PessoaI> cb) {
		GET("pessoas", id.toString()).withCustomReturn(PessoaI.TYPE).go(cb);
	}

	public void getUsuario(String token, Callback<UsuarioI> cb) {
		ExceptionalRequestBuilder reqB = POST("pessoas", "usuario").withCustomReturn(UsuarioI.TYPE);
		reqB.setRequestData(token);
		reqB.go(cb);
	}

	public void todos(Callback<PessoaIAdapter> cb) {
		GET("pessoas", "todos").withCustomReturn(PessoaIAdapter.TYPE).go(cb);
	}

	public void update(PessoaI produtor, Callback<PessoaI> cb) {
		PUT("pessoas").withCustomReturn(PessoaI.TYPE).withJsonContentType().withEntityBody(produtor).go(cb);
	}

	public void delete(Integer id, Callback<Void> cb) {
		DELETE("pessoas", id.toString()).go(cb);
	}

}
