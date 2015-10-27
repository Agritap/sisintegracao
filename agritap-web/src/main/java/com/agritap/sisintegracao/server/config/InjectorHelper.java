package com.agritap.sisintegracao.server.config;

import com.google.inject.Inject;
import com.google.inject.Injector;

public class InjectorHelper {

	@Inject
	Injector injector;

	public static Injector reference;
	
	public void init(){
		reference=injector;
	}

}
