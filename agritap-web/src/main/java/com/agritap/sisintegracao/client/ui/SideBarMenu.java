package com.agritap.sisintegracao.client.ui;

import com.agritap.sisintegracao.client.ViewEnum;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SideBarMenu extends Composite  {

	private static SideBarUiBinder uiBinder = GWT.create(SideBarUiBinder.class);

	@UiField
	Anchor configuracaoLote;
	
	interface SideBarUiBinder extends UiBinder<Widget, SideBarMenu> {
	}

	public SideBarMenu() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	@UiHandler("configuracaoLote")
	public void configuracaoLoteClick(ClickEvent evt){
		ViewEnum v = ViewEnum.CONFIGURACAO_LOTE;
		StateHistory state = new StateHistory(v);
		ViewFactory vf = ViewFactory.getInstance();
		vf.openView(state);
	}
}
