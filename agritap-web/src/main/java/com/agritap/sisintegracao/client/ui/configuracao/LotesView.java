package com.agritap.sisintegracao.client.ui.configuracao;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class LotesView extends Composite {

	private static LotesViewUiBinder uiBinder = GWT.create(LotesViewUiBinder.class);

	interface LotesViewUiBinder extends UiBinder<Widget, LotesView> {
	}

	public LotesView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

//	@UiHandler("Send")
	public void clickButton(ClickEvent evt){
		
	}
}
