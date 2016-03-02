package com.agritap.sisintegracao.server.config;

import java.util.HashMap;
import java.util.Map;

import com.agritap.sisintegracao.server.rest.CustomJsonObjectFilter;
import com.agritap.sisintegracao.server.rest.PessoaServiceImpl;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import com.googlecode.injectlet.jersey.GuiceContainer;
import com.sun.jersey.api.core.PackagesResourceConfig;

public class SisIntegracaoGuiceConfig extends ServletModule {
	
	@Override
	protected void configureServlets() {
//		install(new InjectedRequestFactoryModule());
		

		bind(InjectorHelper.class).in(Singleton.class);
		
		JpaPersistModule module = new JpaPersistModule("sisIntegracaoUnit");
		install(module);
		filter("/*").through(PersistFilter.class);
		
		
		// hook Jersey into Guice Servlet
        bind(GuiceContainer.class).in(Scopes.SINGLETON);;
 
        // hook Jackson into Jersey as the POJO <-> JSON mapper
//        bind(BigDecimalSerializer.class).toProvider(ObjectMapperProvider)in(Scopes.SINGLETON);
        bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);
        Map<String,String> parameters = new HashMap<String, String>();
        
        
        parameters.put(PackagesResourceConfig.PROPERTY_PACKAGES, PessoaServiceImpl.class.getPackage().getName());
        parameters.put(PackagesResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS, CustomJsonObjectFilter.class.getName());
        
        serve("/api/*").with(GuiceContainer.class,parameters);


	}

}
