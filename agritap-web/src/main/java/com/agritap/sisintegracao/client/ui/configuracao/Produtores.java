package com.agritap.sisintegracao.client.ui.configuracao;

import org.gwtbootstrap3.client.ui.InlineCheckBox;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.agritap.sisintegracao.client.request.beans.ProdutorI;
import com.agritap.sisintegracao.client.request.beans.ProdutorIAdapter;
import com.agritap.sisintegracao.client.request.clients.ProdutorClient;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;

public class Produtores extends Composite {

	private static ProdutoresUiBinder uiBinder = GWT.create(ProdutoresUiBinder.class);

	@UiField
	CellTable<ProdutorI> tabelaProdutores;

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
	
	interface ProdutoresUiBinder extends UiBinder<Widget, Produtores> {
	}

	public Produtores() {
		initWidget(uiBinder.createAndBindUi(this));
		init();

	}

	private void init() {
		preparaTabela();

		ProdutorClient client = new ProdutorClient(1);
		client.todos(new com.agritap.sisintegracao.client.request.Callback<ProdutorIAdapter>() {
			
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
//		try{
//		ProdutorRequest produtorRequest=requestFactory.produtorRequest();
//
//		produtorEditado = produtorRequest.edit(produtorEditado);
//		produtorEditado.setNome(nomeField.getValue());
//		produtorEditado.setEmail(emailField.getValue());
//		produtorEditado.setAtivo(ativoField.getValue());
//		produtorEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
//		produtorRequest.persist(produtorEditado).using(produtorEditado).fire();
//		formRow.setVisible(false);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
	}

	public void loadForm(ProdutorI produtor) {
		this.produtorEditado=produtor;
//		integradoraField.setSelectedIndex(index);
		nomeField.setValue(produtor.getNome());
		emailField.setValue(produtor.getEmail());
		codigoIntegradoraField.setValue(produtor.getCodigoIntegradora());
		ativoField.setValue(produtor.getAtivo());;
		telefoneField.setValue(produtor.getTelefone());
		
		
	}

}
