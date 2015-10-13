package com.agritap.sisintegracao.client.ui.configuracao;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ControleMortalidade extends Composite {

	private static ControleMortalidadeUiBinder uiBinder = GWT.create(ControleMortalidadeUiBinder.class);

	interface ControleMortalidadeUiBinder extends UiBinder<Widget, ControleMortalidade> {
	}

	public ControleMortalidade() {
		initWidget(uiBinder.createAndBindUi(this));
	}


}
