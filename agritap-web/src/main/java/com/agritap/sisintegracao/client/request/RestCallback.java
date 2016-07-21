package com.agritap.sisintegracao.client.request;

import java.util.Map;
import java.util.logging.Logger;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.agritap.sisintegracao.client.vo.ErrosValidacao;
import com.github.nmorel.gwtjackson.client.ObjectReader;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Response;

public abstract class RestCallback<T>  implements MethodCallback<T> {
	
	protected Logger logger = Logger.getLogger(RestCallback.class.getName());
	
	public interface ErrosValidacaoReader extends ObjectReader<ErrosValidacao>{}

//	method.getResponse().getText()
//	ErrosValidacao
	
	@Override
	public void onSuccess(Method method, T response) {
		 success(response);
	}
	
	public abstract void success(T result);

	public void error(int statusCode, Response response) {
		
	}

	public void onValidation(ErrosValidacao erro) {
		if(erro.getErrosGenericos()!=null){
			for(String err: erro.getErrosGenericos()){
				logger.warning(err);
			}
		}
		if(erro.getErrosFields()!=null){
			Map<String,String> errosMaps= erro.getErrosFields();
			for(String chave: errosMaps.keySet()){
				logger.warning("Chave:"+chave+" Valor:"+errosMaps.get(chave));
			}
		}
		
	}
	
	@Override
	public void onFailure(Method method, Throwable exception) {
		if(method.getResponse().getStatusCode()==409){
			try{
				ErrosValidacaoReader read = GWT.create(ErrosValidacaoReader.class);
				ErrosValidacao erros = read.read(method.getResponse().getText());
				onValidation(erros);
			}catch(Exception e){
				logger.warning("Erro 409 não parseavel na requisição");
				error(method.getResponse().getStatusCode(),method.getResponse());
			}
		}else{
			logger.warning("Erro "+method.getResponse().getStatusCode()+ " na requisicao");
			logger.warning(method.getResponse().getText());
			error(method.getResponse().getStatusCode(),method.getResponse());
		}
	}
}
