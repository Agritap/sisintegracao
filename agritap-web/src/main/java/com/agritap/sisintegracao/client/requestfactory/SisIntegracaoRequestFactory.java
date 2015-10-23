package com.agritap.sisintegracao.client.requestfactory;

import com.agritap.sisintegracao.client.model.Produtor;
import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface SisIntegracaoRequestFactory extends RequestFactory {	

	public ProdutorRequest produtorRequest();
}
