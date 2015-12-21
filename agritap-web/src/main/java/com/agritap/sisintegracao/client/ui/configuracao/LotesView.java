package com.agritap.sisintegracao.client.ui.configuracao;

import com.agritap.sisintegracao.client.ViewEnum;
import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class LotesView extends Composite {

	private static LotesViewUiBinder uiBinder = GWT.create(LotesViewUiBinder.class);

	interface LotesViewUiBinder extends UiBinder<Widget, LotesView> {
	}

	ClientFactory factory;
	
	@UiField
	HTMLPanel addLote;

	public LotesView(ClientFactory factory) {
		initWidget(uiBinder.createAndBindUi(this));
		bindAddEvent();
		this.factory=factory;
	}

	private void bindAddEvent() {
		
		addLote.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				factory.getAppController().goTo(ViewEnum.LOTE);
			}
		}, ClickEvent.getType());
	}	
	
}
