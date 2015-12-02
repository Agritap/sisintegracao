package com.agritap.sisintegracao.client.ui.configuracao;

import org.gwtbootstrap3.client.ui.InlineCheckBox;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.agritap.sisintegracao.client.request.Callback;
import com.agritap.sisintegracao.client.request.beans.TecnicoI;
import com.agritap.sisintegracao.client.request.beans.TecnicoIAdapter;
import com.agritap.sisintegracao.client.request.clients.TecnicoClient;
import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.client.request.beans.ProdutorI;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.Cell.Context;

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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasText;

import com.google.gwt.user.client.Window;
;

public class TecnicosView extends Composite {

	private static TecnicosUiBinder uiBinder = GWT
			.create(TecnicosUiBinder.class);

	TecnicoClient client = new TecnicoClient();
	
	ClientFactory factory;
	@UiField
	CellTable<TecnicoI> tabelaTecnicos;
	@UiField
	HTMLPanel addTecnico;

	@UiField
	Row formRow;

	@UiField
	ListBox integradoraField;
	
	@UiField
	TextBox nomeField;
	
	@UiField
	TextBox emailField;

	@UiField
	TextBox codigoIntegradoraField;
	
	@UiField
	InlineCheckBox ativoField;

	@UiField
	TextBox telefoneField;
	
	TecnicoI tecnicoEditado;
	
	interface TecnicosUiBinder extends UiBinder<Widget, TecnicosView> {
	}

	public TecnicosView(ClientFactory factory) {
		initWidget(uiBinder.createAndBindUi(this));
		init();
		this.factory=factory;
	}

	private void init() {
		preparaTabela();
		loadTabela();
		bindAddEvent();
	}

	private void bindAddEvent() {
		
		addTecnico.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				tecnicoEditado = factory.getEntityfactory().newTecnico().as();
				loadForm(tecnicoEditado);
				 formRow.setVisible(true);
			}
		}, ClickEvent.getType());
	}

	private void loadTabela() {
		client.todos(new Callback<TecnicoIAdapter>() {
			
			@Override
			public void ok(TecnicoIAdapter response) {
				tabelaTecnicos.setRowData(response.getResultado());
				tabelaTecnicos.redraw();
			}
		});
		
	}

	private void preparaTabela() {
		TextColumn<TecnicoI> nomeColumn = new TextColumn<TecnicoI>() {
			@Override
			public String getValue(TecnicoI tecnico) {
				return tecnico.getNome();
			}
		};
		TextColumn<TecnicoI> emailColumn = new TextColumn<TecnicoI>() {
			@Override
			public String getValue(TecnicoI tecnico) {
				return tecnico.getEmail();
			}
		};
		TextColumn<TecnicoI> ativo2Column = new TextColumn<TecnicoI>() {
			@Override
			public String getValue(TecnicoI tecnico) {
				if(tecnico.getAtivo() == null || tecnico.getAtivo()){
					return "true";
				}else{
					return "false";
				}
			}
			@Override
			public void render(Context context, TecnicoI tecnico, SafeHtmlBuilder sb) {
				if(tecnico.getAtivo() == null || tecnico.getAtivo()){
					sb.appendHtmlConstant("<i class=\"fa fa-check-square-o\"></i>");
				}else{
					sb.appendHtmlConstant("<i class=\"fa fa-square-o\"></i>");
				}
			}
		};
		 final Column<TecnicoI, String> click = new Column<TecnicoI, String>(new ButtonCell(ButtonType.PRIMARY, IconType.EDIT)) {
	            @Override
	            public String getValue(TecnicoI object) {
	                return "";
	            }
	        };
	        
	        click.setFieldUpdater(new FieldUpdater<TecnicoI, String>() {
            @Override
            public void update(int index, TecnicoI tecnico, String value) {
            	loadForm(tecnico);
                formRow.setVisible(true);
            }

        });
		ativo2Column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		tabelaTecnicos.addColumn(nomeColumn, "Nome");
		tabelaTecnicos.addColumn(emailColumn, "Email");
		tabelaTecnicos.addColumn(ativo2Column, "Ativo");
		tabelaTecnicos.addColumn(click, "Ações");
	}
	
	@UiHandler("salvarBtn")
	public void salvarClick(ClickEvent evt){
		tecnicoEditado.setNome(nomeField.getValue());
		tecnicoEditado.setEmail(emailField.getValue());
		tecnicoEditado.setAtivo(ativoField.getValue());
		tecnicoEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
		client.update(tecnicoEditado, new Callback<TecnicoI>() {

			@Override
			public void ok(TecnicoI to) {
				loadTabela();
				tecnicoEditado=null;
				formRow.setVisible(false);
			}
		});
	}
	@UiHandler("cancelarBtn")
	public void cancelarClick(ClickEvent evt){
		formRow.setVisible(false);
		tecnicoEditado=null;
	}

	@UiHandler("excluirBtn")
	public void excluirClick(ClickEvent evt){
		tecnicoEditado.setNome(nomeField.getValue());
		tecnicoEditado.setEmail(emailField.getValue());
		tecnicoEditado.setAtivo(ativoField.getValue());
		tecnicoEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
		client.delete(tecnicoEditado.getId(), new Callback<Void>() {

			@Override
			public void ok(Void to) {
				loadTabela();
				tecnicoEditado=null;
				formRow.setVisible(false);
			}
		});
	}
//	

	public void loadForm(TecnicoI tecnico) {
		this.tecnicoEditado=tecnico;
		nomeField.setValue(tecnico.getNome());
		emailField.setValue(tecnico.getEmail());
		codigoIntegradoraField.setValue(tecnico.getCodigoIntegradora());
		ativoField.setValue(tecnico.getAtivo());;
		telefoneField.setValue(tecnico.getTelefone());
		
		
	}

}
