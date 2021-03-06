package com.agritap.sisintegracao.client;

import java.util.logging.Logger;

import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class MainApp implements EntryPoint {

	@Override
	public void onModuleLoad() {
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		AppController appViewer = new AppController(clientFactory);
	    appViewer.go(RootPanel.get());
	    Logger log=Logger.getLogger(MainApp.class.getName());
	    log.info("Teste info");
	    log.warning("Teste warn");
	}
}
