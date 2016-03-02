package com.agritap.sisintegracao.client.ui;

import java.util.logging.Logger;

import com.agritap.sisintegracao.client.ClientUtil;
import com.agritap.sisintegracao.client.ViewEnum;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MainWindow extends Composite {

	private static MainWindowUiBinder uiBinder = GWT.create(MainWindowUiBinder.class);

	Logger log = Logger.getLogger(MainWindow.class.getName());

	@UiField
	SimplePanel conteudo;

	@UiField
	HTMLPanel sideBarMenu;

	@UiField
	HTMLPanel wrapper;

	ClientFactory factory;

	interface MainWindowUiBinder extends UiBinder<Widget, MainWindow> {
	}

	public void addConteudo(IsWidget w) {
		conteudo.clear();
		conteudo.add(w);
	}

	public void clearConteudo() {
		conteudo.clear();
	}

	public void open() {
		// addConteudo(new LoginWindow());
	}	

	@UiHandler("sair")
	public void onSair(ClickEvent evt) {
		ClientUtil.clearToken();
		factory.setAutenticado(null);
		History.newItem(ViewEnum.LOGIN.getUrl());
	}

	public MainWindow(ClientFactory clientFactory) {
		initWidget(uiBinder.createAndBindUi(this));
		sideBarMenu.add(new SideBarMenu(clientFactory));
		this.factory = clientFactory;

		wrapper.getElement().setId("wrapper");
	}
}