package com.agritap.sisintegracao.client.ui.configuracao;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

public class ConfiguracaoLote extends Composite {

	private static ConfiguracaoLoteUiBinder uiBinder = GWT.create(ConfiguracaoLoteUiBinder.class);

	interface ConfiguracaoLoteUiBinder extends UiBinder<Widget, ConfiguracaoLote> {
	}

	public ConfiguracaoLote() {
		initWidget(uiBinder.createAndBindUi(this));
	}

//	@UiHandler("Send")
	public void clickButton(ClickEvent evt){
		
	}
}
