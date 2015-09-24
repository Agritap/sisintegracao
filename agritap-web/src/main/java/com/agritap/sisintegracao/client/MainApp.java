package com.agritap.sisintegracao.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class MainApp implements EntryPoint {

	@Override
	public void onModuleLoad() {
		System.out.println("oi mundo");
		Sample sample = new Sample();
		
		RootPanel.get().add(sample);
//		RootPanel.getBodyElement().setInnerHTML("<p>Oi mundo</p>");
//		AppController appController = injector.getAppController();
//		appController.setRootPanel(RootPanel.get());
//		appController.startApp();
	}

}
