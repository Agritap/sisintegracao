package com.agritap.sisintegracao.client.ui.configuracao;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ReposicaoLote extends Composite {

	private static ReposicaoLoteUiBinder uiBinder = GWT.create(ReposicaoLoteUiBinder.class);

	interface ReposicaoLoteUiBinder extends UiBinder<Widget, ReposicaoLote> {
	}

	public ReposicaoLote() {
		initWidget(uiBinder.createAndBindUi(this));
	}


}