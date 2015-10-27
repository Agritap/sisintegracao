package com.agritap.sisintegracao.client;

import com.agritap.sisintegracao.client.request.EntityFactory;
import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.client.ui.MainWindow;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class ClientFactoryImpl implements ClientFactory {

	public static final EntityFactory entityFactory = GWT.create(EntityFactory.class);

	public static final EventBus eventBus = GWT.create(SimpleEventBus.class);

	public AppController appController;
	private MainWindow mainWindow;

	public EventBus getEventbus() {
		return eventBus;
	}

	public EntityFactory getEntityfactory() {
		return entityFactory;
	}

//	@Override
//	public PlaceController getPlaceController() {
//		return placeController;
//	}

	@Override
	public MainWindow getMainWindow(HasWidgets panel) {
		if (mainWindow == null) {
			mainWindow = new MainWindow(this);
			panel.add(mainWindow);
		}
		return mainWindow;
	}

	@Override
	public AppController getAppController() {
		return appController;
	}

	@Override
	public void setAppController(AppController app) {
		this.appController=app;
	}

	// @Override
	// public MainWindow getMainWindow(AcceptsOneWidget panel) {
	// if(mainWindow==null){
	// mainWindow= new MainWindow(this);
	// panel.setWidget(mainWindow);
	// }
	// return mainWindow;
	// }

}
