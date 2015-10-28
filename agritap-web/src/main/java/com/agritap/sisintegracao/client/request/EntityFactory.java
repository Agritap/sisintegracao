package com.agritap.sisintegracao.client.request;

import com.agritap.sisintegracao.client.request.beans.ProdutorI;
import com.agritap.sisintegracao.client.request.beans.ProdutorIAdapter;
import com.agritap.sisintegracao.client.request.beans.TabelaRacaoI;
import com.agritap.sisintegracao.client.request.beans.TabelaRacaoIAdapter;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface EntityFactory extends AutoBeanFactory {
	public static String PREFIX = "application/vnd.agritap.v1.entity.";

	AutoBean<ProdutorI> newProdutor();
	AutoBean<ProdutorIAdapter> newProdutores();
	AutoBean<TabelaRacaoI> newTabelaRacao();
	AutoBean<TabelaRacaoIAdapter> newTabelaRacaoAdapter();
}
