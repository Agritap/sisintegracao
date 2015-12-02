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

	public void todos(Callback<PessoaIAdapter> cb) {
		GET("pessoas", "todos").withCustomReturn(PessoaIAdapter.TYPE).go(cb);
	}

	public void update(PessoaI produtor, Callback<PessoaI> cb) {
		PUT("pessoas").withCustomReturn(PessoaI.TYPE).withJsonContentType().withEntityBody(produtor).go(cb);
	}

	public void delete(Integer id, Callback<Void> cb) {
		DELETE("pessoas", id.toString()).go(cb);
	}

	public void auth(String cookie, Callback<UsuarioI> callback) {
		String formBody = "token=" + cookie;
		ExceptionalRequestBuilder reqB = POST("pessoas", "authToken").withCustomReturn(UsuarioI.TYPE);
		reqB.setRequestData(formBody );
		reqB.go(callback);
	}

	/**
	 * Verifica se o usuario possui senha definida
	 * @param id
	 * @param callback
	 */
	public void possuiSenha(Integer id, Callback<Boolean> callback) {
		if(id==null){
			callback.ok(false);
		}else{
			GET("pessoas", id.toString(),"possuiSenha").go(callback);
		}
	}

	/**
	 * Remove as senhas cadastradas para o usuario removendo o acesso.
	 * 
	 * @param id
	 * @param callback
	 */
	public void removeSenhas(Integer id, Callback<Boolean> callback) {
		if(id==null){
			callback.ok(true);
		}else{
			GET("pessoas", id.toString(),"removeSenha").go(callback);
		}
	}

	public void updatePassword(Integer id, String pass, Callback<Boolean> callback) {
		String formBody = "senha=" + pass;
		ExceptionalRequestBuilder reqB = POST("pessoas", id.toString(),"updatePass");
		reqB.setRequestData(formBody );
		reqB.go(callback);
	}

}
