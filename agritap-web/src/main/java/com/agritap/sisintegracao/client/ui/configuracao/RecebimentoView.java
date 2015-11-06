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
import com.agritap.sisintegracao.client.request.beans.RecebimentoI;
import com.agritap.sisintegracao.client.request.beans.RecebimentoIAdapter;
import com.agritap.sisintegracao.client.request.clients.RecebimentosClient;
import com.agritap.sisintegracao.client.ui.ClientFactory;
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

public class RecebimentoView extends Composite {

	private static RecebimentoUiBinder uiBinder = GWT.create(RecebimentoUiBinder.class);

	RecebimentosClient client = new RecebimentosClient();
	
	ClientFactory factory;
	@UiField
	CellTable<RecebimentoI> tabelaRecebimento;
	@UiField
	HTMLPanel addRecebimento;

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
	
	RecebimentoI recebimentoEditado;
	
	interface RecebimentoUiBinder extends UiBinder<Widget, RecebimentoView> {
	}

	public RecebimentoView(ClientFactory factory) {
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
		
		addRecebimento.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				recebimentoEditado = factory.getEntityfactory().newRecebimento1().as();
				loadForm(recebimentoEditado);
				 formRow.setVisible(true);
			}
		}, ClickEvent.getType());
	}

	private void loadTabela() {
		client.todos(new Callback<RecebimentoIAdapter>() {
			
			@Override
			public void ok(RecebimentoIAdapter response) {
				tabelaRecebimento.setRowData(response.getResultado());
				tabelaRecebimento.redraw();
			}
		});
	}

	private void preparaTabela() {
		TextColumn<RecebimentoI> nomeColumn = new TextColumn<RecebimentoI>() {
			@Override
			public String getValue(RecebimentoI produtor) {
				return produtor.getNome();
			}
		};
		TextColumn<RecebimentoI> emailColumn = new TextColumn<RecebimentoI>() {
			@Override
			public String getValue(RecebimentoI recebimento) {
				return recebimento.getEmail();
			}
		};
		
		TextColumn<RecebimentoI> telefoneColumn = new TextColumn<RecebimentoI> (){
			@Override
			public String getValue(RecebimentoI recebimento){
				return recebimento.getTelefone();
				
						
			}
			
		};
		
		TextColumn<RecebimentoI> ativo2Column = new TextColumn<RecebimentoI>() {
			@Override
			public String getValue(RecebimentoI recebimento) {
				if(recebimento.getAtivo() == null || recebimento.getAtivo()){
					return "true";
				}else{
					return "false";
				}
			}
			@Override
			public void render(Context context, RecebimentoI recebimento, SafeHtmlBuilder sb) {
				if(recebimento.getAtivo() == null || recebimento.getAtivo()){
					sb.appendHtmlConstant("<i class=\"fa fa-check-square-o\"></i>");
				}else{
					sb.appendHtmlConstant("<i class=\"fa fa-square-o\"></i>");
				}
			}
		};
		 final Column<RecebimentoI, String> click = new Column<RecebimentoI, String>(new ButtonCell(ButtonType.PRIMARY, IconType.EDIT)) {
	            @Override
	            public String getValue(RecebimentoI object) {
	                return "";
	            }
	        };
	        
	        click.setFieldUpdater(new FieldUpdater<RecebimentoI, String>() {
            @Override
            public void update(int index, RecebimentoI recebimento, String value) {
            	loadForm(recebimento);
                formRow.setVisible(true);
            }

        });
		ativo2Column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		tabelaRecebimento.addColumn(nomeColumn, "Nome");
		tabelaRecebimento.addColumn(emailColumn, "Email");
		tabelaRecebimento.addColumn(telefoneColumn, "Telefone");	
		tabelaRecebimento.addColumn(ativo2Column, "Ativo");
		tabelaRecebimento.addColumn(click, "Ações");
	}
	
	@UiHandler("salvarBtn")
	public void salvarClick(ClickEvent evt){
		recebimentoEditado.setNome(nomeField.getValue());
		recebimentoEditado.setEmail(emailField.getValue());
		recebimentoEditado.setTelefone(telefoneField.getValue());
		recebimentoEditado.setAtivo(ativoField.getValue());
		recebimentoEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
		client.update(recebimentoEditado, new Callback<RecebimentoI>() {

			@Override
			public void ok(RecebimentoI to) {
				loadTabela();
				recebimentoEditado=null;
				formRow.setVisible(false);
			}
		});
	}
	@UiHandler("cancelarBtn")
	public void cancelarClick(ClickEvent evt){
		formRow.setVisible(false);
		recebimentoEditado=null;
	}

	@UiHandler("excluirBtn")
	public void excluirClick(ClickEvent evt){
		recebimentoEditado.setNome(nomeField.getValue());
		recebimentoEditado.setEmail(emailField.getValue());
		recebimentoEditado.setTelefone(telefoneField.getValue());
		recebimentoEditado.setAtivo(ativoField.getValue());
		recebimentoEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
		client.delete(recebimentoEditado.getId(), new Callback<Void>() {

			@Override
			public void ok(Void to) {
				loadTabela();
				recebimentoEditado=null;
				formRow.setVisible(false);
			}
		});
	}
//	

	public void loadForm(RecebimentoI recebimento) {
		this.recebimentoEditado=recebimento;
		nomeField.setValue(recebimento.getNome());
		emailField.setValue(recebimento.getEmail());
		codigoIntegradoraField.setValue(recebimento.getCodigoIntegradora());
		ativoField.setValue(recebimento.getAtivo());;
		telefoneField.setValue(recebimento.getTelefone());
		
		
	}

}
