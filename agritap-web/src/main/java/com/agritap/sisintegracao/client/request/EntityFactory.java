package com.agritap.sisintegracao.client.request;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface EntityFactory extends AutoBeanFactory {
	public static String PREFIX = "application/vnd.agritap.v1.entity.";

	AutoBean<ProdutorI> newProdutor();
}
