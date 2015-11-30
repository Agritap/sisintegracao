package com.agritap.sisintegracao.client;

import com.agritap.sisintegracao.client.request.EntityFactory;
import com.agritap.sisintegracao.client.request.beans.UsuarioI;
import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.client.ui.LoginWindow;
import com.agritap.sisintegracao.client.ui.MainWindow;
import com.agritap.sisintegracao.client.ui.StateHistory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class ClientFactoryImpl implements ClientFactory {

	public static final EntityFactory entityFactory = GWT.create(EntityFactory.class);

	public static final EventBus eventBus = GWT.create(SimpleEventBus.class);

	public AppController appController;
	
	private MainWindow mainWindow;

	private LoginWindow loginWindow;
	
	private UsuarioI usuario;
	

	public EventBus getEventbus() {
		return eventBus;
	}

	public EntityFactory getEntityfactory() {
		return entityFactory;
	}

	@Override
	public MainWindow getMainWindow(HasWidgets panel) {
		if (mainWindow == null) {
			panel.clear();
			mainWindow = new MainWindow(this);
			panel.add(mainWindow);
		}
		return mainWindow;
	}

	
	@Override
	public LoginWindow openLoginWindow(HasWidgets panel,StateHistory st) {
		if (loginWindow == null) {
			loginWindow = new LoginWindow(this,st);
			panel.clear();
			panel.add(loginWindow);
		}else{
			panel.clear();
			panel.add(loginWindow);
		}
		mainWindow=null;
		return loginWindow;
	}

	@Override
	public AppController getAppController() {
		return appController;
	}

	@Override
	public void setAppController(AppController app) {
		this.appController=app;
	}

	@Override
	public boolean isAutenticado() {
		return usuario!=null;
	}

	@Override
	public void setAutenticado(UsuarioI user) {
		this.usuario=user;
	}

}
