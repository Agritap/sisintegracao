package com.agritap.sisintegracao.client.request;

import java.util.logging.Logger;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.http.client.Response;

public abstract class RestCallback<T>  implements MethodCallback<T> {
	protected Logger logger = Logger.getLogger(RestCallback.class.getName());
	
	@Override
	public void onSuccess(Method method, T response) {
		 success(response);
	}
	
	public abstract void success(T result);

	public void error(int statusCode, Response response) {
		
	}
	
	@Override
	public void onFailure(Method method, Throwable exception) {
		logger.warning("Erro "+method.getResponse().getStatusCode()+ " na requisicao");
		logger.warning(method.getResponse().getText());
		error(method.getResponse().getStatusCode(),method.getResponse());
	}
}
