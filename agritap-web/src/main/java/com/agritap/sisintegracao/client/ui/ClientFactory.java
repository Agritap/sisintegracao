package com.agritap.sisintegracao.client.ui;

import com.agritap.sisintegracao.client.AppController;
import com.agritap.sisintegracao.client.request.EntityFactory;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.web.bindery.event.shared.EventBus;

public interface ClientFactory {

	public EventBus getEventbus() ;

	public EntityFactory getEntityfactory() ;
	
//	public PlaceController getPlaceController();
	
	public MainWindow getMainWindow(HasWidgets panel);

	public AppController getAppController();
	
	public void setAppController(AppController app);
}
