package com.agritap.sisintegracao.server.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class SisIntegracaoServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		Injector inj = Guice.createInjector(new SisIntegracaoGuiceConfig());
		InjectorHelper helper = inj.getInstance(InjectorHelper.class);
		helper.init();
		return inj;
	}
}
