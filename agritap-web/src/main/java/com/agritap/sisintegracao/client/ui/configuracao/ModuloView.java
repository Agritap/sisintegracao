package com.agritap.sisintegracao.client.ui.configuracao;

import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.InlineCheckBox;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.ListGroup;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.form.validator.BlankValidator;
import org.gwtbootstrap3.client.ui.form.validator.FieldMatchValidator;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.agritap.sisintegracao.client.ClientUtil;
import com.agritap.sisintegracao.client.request.Callback;
import com.agritap.sisintegracao.client.request.beans.ErrosI;
import com.agritap.sisintegracao.client.request.beans.PessoaI;
import com.agritap.sisintegracao.client.request.beans.PessoaIAdapter;
import com.agritap.sisintegracao.client.request.clients.PessoaClient;
import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.client.ui.configuracao.ModulosView.ModulosUiBinder;
import com.agritap.sisintegracao.model.Integradora;
import com.agritap.sisintegracao.model.TipoAnimal;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;

public class ModuloView extends Composite {

	private static ModulosUiBinder uiBinder = GWT.create(ModulosUiBinder.class);
	Logger log = Logger.getLogger(ModuloView.class.getName());

	ModuloClient client = new ModuloClient();
	
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
	
	ModuloI ModuloEditado;
	
	interface ModulosUiBinder extends UiBinder<Widget, ModuloView> {
	}

	public ModuloView(ClientFactory factory) {
		initWidget(uiBinder.createAndBindUi(this));
		init();
		this.factory=factory;
	}

	private void init() {
		ClientUtil.populaListBox(tipoAnimalField, TipoAnimal.values(), true);
		
		
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
