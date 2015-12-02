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
import com.agritap.sisintegracao.client.request.beans.ProdutorI;
import com.agritap.sisintegracao.client.request.beans.ProdutorIAdapter;
import com.agritap.sisintegracao.client.request.clients.ProdutorClient;
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

public class ProdutoresView extends Composite {

	private static ProdutoresUiBinder uiBinder = GWT.create(ProdutoresUiBinder.class);

	ProdutorClient client = new ProdutorClient();
	
	ClientFactory factory;
	@UiField
	CellTable<ProdutorI> tabelaProdutores;
	@UiField
	HTMLPanel addProdutor;

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
	
	ProdutorI produtorEditado;
	
	interface ProdutoresUiBinder extends UiBinder<Widget, ProdutoresView> {
	}

	public ProdutoresView(ClientFactory factory) {
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
		
		addProdutor.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				produtorEditado = factory.getEntityfactory().newProdutor().as();
				loadForm(produtorEditado);
				 formRow.setVisible(true);
			}
		}, ClickEvent.getType());
	}

	private void loadTabela() {
		client.todos(new Callback<ProdutorIAdapter>() {
			
			@Override
			public void ok(ProdutorIAdapter response) {
				tabelaProdutores.setRowData(response.getResultado());
				tabelaProdutores.redraw();
			}
		});
		
	}

	private void preparaTabela() {
		TextColumn<ProdutorI> nomeColumn = new TextColumn<ProdutorI>() {
			@Override
			public String getValue(ProdutorI produtor) {
				return produtor.getNome();
			}
		};
		TextColumn<ProdutorI> emailColumn = new TextColumn<ProdutorI>() {
			@Override
			public String getValue(ProdutorI produtor) {
				return produtor.getEmail();
			}
		};
		TextColumn<ProdutorI> ativo2Column = new TextColumn<ProdutorI>() {
			@Override
			public String getValue(ProdutorI produtor) {
				if(produtor.getAtivo() == null || produtor.getAtivo()){
					return "true";
				}else{
					return "false";
				}
			}
			@Override
			public void render(Context context, ProdutorI produtor, SafeHtmlBuilder sb) {
				if(produtor.getAtivo() == null || produtor.getAtivo()){
					sb.appendHtmlConstant("<i class=\"fa fa-check-square-o\"></i>");
				}else{
					sb.appendHtmlConstant("<i class=\"fa fa-square-o\"></i>");
				}
			}
		};
		 final Column<ProdutorI, String> click = new Column<ProdutorI, String>(new ButtonCell(ButtonType.PRIMARY, IconType.EDIT)) {
	            @Override
	            public String getValue(ProdutorI object) {
	                return "";
	            }
	        };
	        
	        click.setFieldUpdater(new FieldUpdater<ProdutorI, String>() {
            @Override
            public void update(int index, ProdutorI produtor, String value) {
            	loadForm(produtor);
                formRow.setVisible(true);
            }

        });
		ativo2Column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		tabelaProdutores.addColumn(nomeColumn, "Nome");
		tabelaProdutores.addColumn(emailColumn, "Email");
		tabelaProdutores.addColumn(ativo2Column, "Ativo");
		tabelaProdutores.addColumn(click, "Ações");
	}
	
	@UiHandler("salvarBtn")
	public void salvarClick(ClickEvent evt){
		produtorEditado.setNome(nomeField.getValue());
		produtorEditado.setEmail(emailField.getValue());
		produtorEditado.setAtivo(ativoField.getValue());
		produtorEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
		client.update(produtorEditado, new Callback<ProdutorI>() {

			@Override
			public void ok(ProdutorI to) {
				loadTabela();
				produtorEditado=null;
				formRow.setVisible(false);
			}
		});
	}
	@UiHandler("cancelarBtn")
	public void cancelarClick(ClickEvent evt){
		formRow.setVisible(false);
		produtorEditado=null;
	}

	@UiHandler("excluirBtn")
	public void excluirClick(ClickEvent evt){
		produtorEditado.setNome(nomeField.getValue());
		produtorEditado.setEmail(emailField.getValue());
		produtorEditado.setAtivo(ativoField.getValue());
		produtorEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
		client.delete(produtorEditado.getId(), new Callback<Void>() {

			@Override
			public void ok(Void to) {
				loadTabela();
				produtorEditado=null;
				formRow.setVisible(false);
			}
		});
	}
//	

	public void loadForm(ProdutorI produtor) {
		this.produtorEditado=produtor;
		nomeField.setValue(produtor.getNome());
		emailField.setValue(produtor.getEmail());
		codigoIntegradoraField.setValue(produtor.getCodigoIntegradora());
		ativoField.setValue(produtor.getAtivo());;
		telefoneField.setValue(produtor.getTelefone());
		
		
	}

}
