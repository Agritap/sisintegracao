package com.agritap.sisintegracao.server.config;

import java.util.HashMap;
import java.util.Map;

import com.agritap.sisintegracao.server.rest.CustomJsonObjectFilter;
import com.agritap.sisintegracao.server.rest.ProdutorServiceImpl;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.googlecode.injectlet.jersey.GuiceContainer;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.trycatchsoft.gwt.requestfactory.InjectedRequestFactoryModule;
import com.trycatchsoft.gwt.requestfactory.InjectedRequestFactoryServlet;

public class SisIntegracaoGuiceConfig extends ServletModule {
	
	@Override
	protected void configureServlets() {
		install(new InjectedRequestFactoryModule());
		

		bind(InjectorHelper.class).in(Singleton.class);
		
		bind(RequestFactoryServlet.class).in(Singleton.class);
		Map<String, String> params = new HashMap<String, String>();
		params.put("symbolMapsDirectory", "WEB-INF/classes/symbolMaps/");
		serve("/gwtRequest").with(InjectedRequestFactoryServlet.class);
//		serve("/gwtRequest").with(RequestFactoryServlet.class, params);

		JpaPersistModule module = new JpaPersistModule("sisIntegracaoUnit");
		install(module);
		filter("/*").through(PersistFilter.class);
		
		
		// hook Jersey into Guice Servlet
        bind(GuiceContainer.class).in(Scopes.SINGLETON);;
 
        // hook Jackson into Jersey as the POJO <-> JSON mapper
        bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);
        Map<String,String> parameters = new HashMap<String, String>();
        parameters.put(PackagesResourceConfig.PROPERTY_PACKAGES, ProdutorServiceImpl.class.getPackage().getName());
        parameters.put(PackagesResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS, CustomJsonObjectFilter.class.getName());
        
        serve("/api/*").with(GuiceContainer.class,parameters);


	}

}
