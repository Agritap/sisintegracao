package com.agritap.sisintegracao.client.ui.configuracao;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class VacinacaoAnimais extends Composite {

	private static VacinacaoAnimaisUiBinder uiBinder = GWT.create(VacinacaoAnimaisUiBinder.class);

	interface VacinacaoAnimaisUiBinder extends UiBinder<Widget, VacinacaoAnimais> {
	}

	public VacinacaoAnimais() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}