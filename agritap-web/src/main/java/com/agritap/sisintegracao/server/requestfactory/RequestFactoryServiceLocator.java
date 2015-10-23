package com.agritap.sisintegracao.server.requestfactory;

import com.agritap.sisintegracao.server.config.InjectorHelper;
import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

public class RequestFactoryServiceLocator implements ServiceLocator {

	@Override
	public Object getInstance(Class<?> clazz) {
		Injector injector = InjectorHelper.reference;
		Object o = injector.getInstance(clazz);
		return o;
	}

}
