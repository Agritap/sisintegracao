package com.agritap.sisintegracao.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
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
	
	interface MainWindowUiBinder extends UiBinder<Widget, MainWindow> {
	}
	
	public void addConteudo(IsWidget w){
		conteudo.clear();
		conteudo.add(w);
	}

	public MainWindow() {
		initWidget(uiBinder.createAndBindUi(this));
		conteudo.add(new LoginWindow());
		sideBarMenu.add(new SideBarMenu());
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				System.out.println(event.getValue());
			}
		});
//		ScriptInjector.fromUrl("http://cdnjs.cloudflare.com/ajax/libs/metisMenu/2.2.0/metisMenu.min.js")
//		.setWindow(ScriptInjector.TOP_WINDOW)
//		.inject();
//		ScriptInjector.fromString(SisIntegracaoClientBundle.INSTANCE.sbAdmin2().getText())
//        .setWindow(ScriptInjector.TOP_WINDOW)
//        .inject();

//		navBarTopo.addStyleName("navbar-static-top");
//		sidebar.addStyleName("sidebar");
	}
	

}
