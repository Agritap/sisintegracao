package com.agritap.sisintegracao.client;

import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class MainApp implements EntryPoint {
//    Logger log=Logger.getLogger(MainApp.class.getName());
	@Override
	public void onModuleLoad() {
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		AppController appViewer = new AppController(clientFactory);
	    appViewer.go(RootPanel.get());
	}
}
