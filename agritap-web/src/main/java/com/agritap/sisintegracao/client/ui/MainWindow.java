package com.agritap.sisintegracao.client.ui;

import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Container;
import org.gwtbootstrap3.client.ui.Navbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MainWindow extends Composite  {

	private static MainWindowUiBinder uiBinder = GWT.create(MainWindowUiBinder.class);

	@UiField
	AnchorListItem botao1;
	@UiField
	Navbar navBarLateral;
	@UiField
	Navbar navBarTopo;
	@UiField
	SimplePanel corpo;
	@UiField
	Container containerGeral;
	interface MainWindowUiBinder extends UiBinder<Widget, MainWindow> {
	}

	public MainWindow() {
		initWidget(uiBinder.createAndBindUi(this));
		navBarTopo.addStyleName("nav-bar-topo");
		navBarLateral.addStyleName("nav-bar-lateral");
		containerGeral.addStyleName("container-geral");
	}
	
	@UiHandler("botao1")
	public void onBota1Click(ClickEvent evt){
		GWT.log("botao111");
		corpo.add(new LoginWindow());
	}

}
