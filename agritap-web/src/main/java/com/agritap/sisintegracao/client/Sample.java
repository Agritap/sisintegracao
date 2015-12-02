package com.agritap.sisintegracao.client;

import org.gwtbootstrap3.client.ui.NavbarBrand;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class Sample extends Composite implements HasText {

	private static SampleUiBinder uiBinder = GWT.create(SampleUiBinder.class);

	@UiField
	NavbarBrand rotuloPrincipal;
	interface SampleUiBinder extends UiBinder<Widget, Sample> {
	}

	public Sample() {
		initWidget(uiBinder.createAndBindUi(this));
		rotuloPrincipal.setText("troca no nome depois que abrir");
	}

	

	public void setText(String text) {
		
	}

	public String getText() {
		return "hwww00";
	}

}
