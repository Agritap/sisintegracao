package com.agritap.sisintegracao.client.request;

import com.agritap.sisintegracao.client.request.beans.TipoRacaoI;
import com.agritap.sisintegracao.client.request.beans.TipoRacaoIAdapter;
import com.agritap.sisintegracao.client.request.beans.ProdutorI;
import com.agritap.sisintegracao.client.request.beans.ProdutorIAdapter;
import com.agritap.sisintegracao.client.request.beans.RecebimentoI;
import com.agritap.sisintegracao.client.request.beans.RecebimentoIAdapter;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface EntityFactory extends AutoBeanFactory {
	public static String PREFIX = "application/vnd.agritap.v1.entity.";

	AutoBean<ProdutorI> newProdutor();

	AutoBean<ProdutorIAdapter> newProdutores();

	AutoBean<TipoRacaoI> newTipoRacao();

	AutoBean<TipoRacaoIAdapter> newTipoRacoes();

	AutoBean<RecebimentoI> newRecebimento1();

	AutoBean<RecebimentoIAdapter> newRecebimentos();

}
