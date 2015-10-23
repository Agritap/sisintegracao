package com.agritap.sisintegracao.client.requestfactory;

import java.util.List;

import com.agritap.sisintegracao.client.proxy.ProdutorProxy;
import com.agritap.sisintegracao.server.requestfactory.ProdutorRequestService;
import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.trycatchsoft.gwt.requestfactory.InjectingServiceLocator;

@Service(value=ProdutorRequestService.class,locator=InjectingServiceLocator.class)
public interface ProdutorRequest extends RequestContext {
	
	  public Request<List<ProdutorProxy>> produtores();
	  
	  public InstanceRequest<ProdutorProxy, Void> persist(ProdutorProxy employee);

}
