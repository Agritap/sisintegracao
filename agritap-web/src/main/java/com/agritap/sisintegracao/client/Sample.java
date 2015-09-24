package com.agritap.sisintegracao.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class Sample extends Composite implements HasText {

	private static SampleUiBinder uiBinder = GWT.create(SampleUiBinder.class);

	interface SampleUiBinder extends UiBinder<Widget, Sample> {
	}

	public Sample() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	

	public void setText(String text) {
		
	}

	public String getText() {
		return "hwww00";
	}

}
