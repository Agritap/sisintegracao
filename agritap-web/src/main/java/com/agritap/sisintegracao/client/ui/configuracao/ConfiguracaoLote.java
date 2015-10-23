package com.agritap.sisintegracao.client.ui.configuracao;

import com.agritap.sisintegracao.client.requestfactory.SisIntegracaoRequestFactory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class ConfiguracaoLote extends Composite {

	private static ConfiguracaoLoteUiBinder uiBinder = GWT.create(ConfiguracaoLoteUiBinder.class);
	final EventBus eventBus;

	interface ConfiguracaoLoteUiBinder extends UiBinder<Widget, ConfiguracaoLote> {
	}

	public ConfiguracaoLote(EventBus eventBus) {
		initWidget(uiBinder.createAndBindUi(this));
		this.eventBus=eventBus;
	}

	@UiHandler("Send")
	public void clickButton(ClickEvent evt){
		
	}
}
