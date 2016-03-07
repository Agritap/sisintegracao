package com.agritap.sisintegracao.client.ui.configuracao;

import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.TextBox;

import com.agritap.sisintegracao.client.ClientUtil;
import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.model.TipoAnimal;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ModuloView extends Composite {

	private static ModulosUiBinder uiBinder = GWT.create(ModulosUiBinder.class);
	Logger log = Logger.getLogger(ModuloView.class.getName());
	
	ClientFactory factory;

	@UiField
	Row formRow;
	
	@UiField
	Form form;
	
	@UiField
	ListBox produtorField;

	@UiField
	ListBox tipoAnimalField;

	@UiField
	TextBox alojamentoMaximoField;
	
	@UiField
	TextBox nomeBarracaoField;
	
	
	interface ModulosUiBinder extends UiBinder<Widget, ModuloView> {
	}

	public ModuloView(ClientFactory factory) {
		initWidget(uiBinder.createAndBindUi(this));
		init();
		this.factory=factory;
	}

	private void init() {
		ClientUtil.populateListBox(tipoAnimalField, TipoAnimal.values(), true);
		
		
	}
	
	@UiHandler("salvarBtn")
	public void salvarClick(ClickEvent evt){
	}
		
	@UiHandler("cancelarBtn")
	public void cancelarClick(ClickEvent evt){
	}

	@UiHandler("excluirBtn")
	public void excluirClick(ClickEvent evt){
	}

}
