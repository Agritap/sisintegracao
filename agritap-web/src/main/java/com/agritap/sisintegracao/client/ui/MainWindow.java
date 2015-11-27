package com.agritap.sisintegracao.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MainWindow extends Composite  {

	private static MainWindowUiBinder uiBinder = GWT.create(MainWindowUiBinder.class);

	@UiField
	SimplePanel conteudo;
	
	@UiField
	HTMLPanel sideBarMenu;
	
	ClientFactory factory;
	
	interface MainWindowUiBinder extends UiBinder<Widget, MainWindow> {
	}
	
	public void addConteudo(IsWidget w){
		conteudo.clear();
		conteudo.add(w);
	}

	public void clearConteudo(){
		conteudo.clear();
	}
	
	public void open(){
//		addConteudo(new LoginWindow());
	}
	
	public MainWindow(ClientFactory clientFactory) {
		initWidget(uiBinder.createAndBindUi(this));
		sideBarMenu.add(new SideBarMenu(clientFactory));
		this.factory=clientFactory;
		
	}
	

}
