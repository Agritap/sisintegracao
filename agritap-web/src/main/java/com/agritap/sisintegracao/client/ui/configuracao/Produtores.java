package com.agritap.sisintegracao.client.ui.configuracao;

import org.gwtbootstrap3.client.ui.InlineCheckBox;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.agritap.sisintegracao.client.proxy.ProdutorProxy;
import com.agritap.sisintegracao.client.request.EntityFactory;
import com.agritap.sisintegracao.client.request.ProdutorClient;
import com.agritap.sisintegracao.client.request.ProdutorI;
import com.agritap.sisintegracao.client.requestfactory.ProdutorRequest;
import com.agritap.sisintegracao.client.requestfactory.SisIntegracaoRequestFactory;
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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

public class Produtores extends Composite {

	private static ProdutoresUiBinder uiBinder = GWT.create(ProdutoresUiBinder.class);

	@UiField
	CellTable<ProdutorProxy> tabelaProdutores;

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
	
	private ProdutorProxy produtorEditado;
	
	SisIntegracaoRequestFactory requestFactory ;
	

	
	EventBus eventBus;

	interface ProdutoresUiBinder extends UiBinder<Widget, Produtores> {
	}

	public Produtores(EventBus eventBus) {
		this.eventBus = eventBus;
		initWidget(uiBinder.createAndBindUi(this));
		init();

	}

	private void init() {
		preparaTabela();

		ProdutorClient client = new ProdutorClient(1);
		client.get(new com.agritap.sisintegracao.client.request.Callback<ProdutorI>() {
			
			@Override
			public void ok(ProdutorI to) {
				GWT.log("Retorno do get"+to.getNome());
			}
		});
		EntityFactory entityFactory = GWT.create(EntityFactory.class);
		
		ProdutorI produtor = entityFactory.newProdutor().as();
		produtor.setNome("Contingencia");
		client = new ProdutorClient(null);
		client.update(produtor, new com.agritap.sisintegracao.client.request.Callback<ProdutorI>() {
			@Override
			public void ok(ProdutorI to) {
				GWT.log("Retorno do post"+to.getNome());
			}
		});
		
//		requestFactory = GWT.create(SisIntegracaoRequestFactory.class);
//		requestFactory.initialize(eventBus);
//		ProdutorRequest produtorRequest=requestFactory.produtorRequest();
//		produtorRequest.produtores().fire(new Receiver<List<ProdutorProxy>>() {
//			@Override
//			public void onSuccess(List<ProdutorProxy> response) {
//				tabelaProdutores.setRowData(response);
//				tabelaProdutores.redraw();
//			}
//		});
//		formRow.setVisible(false);
//		for(Integradora integ:Integradora.values()){
//			integradoraField.addItem(integ.name());
//		}
	}

	private void preparaTabela() {
		TextColumn<ProdutorProxy> nomeColumn = new TextColumn<ProdutorProxy>() {
			@Override
			public String getValue(ProdutorProxy produtor) {
				return produtor.getNome();
			}
		};
		TextColumn<ProdutorProxy> emailColumn = new TextColumn<ProdutorProxy>() {
			@Override
			public String getValue(ProdutorProxy produtor) {
				return produtor.getEmail();
			}
		};
		TextColumn<ProdutorProxy> ativo2Column = new TextColumn<ProdutorProxy>() {
			@Override
			public String getValue(ProdutorProxy produtor) {
				if(produtor.getAtivo() == null || produtor.getAtivo()){
					return "true";
				}else{
					return "false";
				}
			}
			@Override
			public void render(Context context, ProdutorProxy produtor, SafeHtmlBuilder sb) {
				if(produtor.getAtivo() == null || produtor.getAtivo()){
					sb.appendHtmlConstant("<i class=\"fa fa-check-square-o\"></i>");
				}else{
					sb.appendHtmlConstant("<i class=\"fa fa-square-o\"></i>");
				}
			}
		};
		 final Column<ProdutorProxy, String> click = new Column<ProdutorProxy, String>(new ButtonCell(ButtonType.PRIMARY, IconType.EDIT)) {
	            @Override
	            public String getValue(ProdutorProxy object) {
	                return "";
	            }
	        };
	        
	        click.setFieldUpdater(new FieldUpdater<ProdutorProxy, String>() {
            @Override
            public void update(int index, ProdutorProxy produtor, String value) {
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
		try{
		ProdutorRequest produtorRequest=requestFactory.produtorRequest();

		produtorEditado = produtorRequest.edit(produtorEditado);
		produtorEditado.setNome(nomeField.getValue());
		produtorEditado.setEmail(emailField.getValue());
		produtorEditado.setAtivo(ativoField.getValue());
		produtorEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
		produtorRequest.persist(produtorEditado).using(produtorEditado).fire();
//		ProdutorRequest produtorRequest=requestFactory.produtorRequest();
//		produtorRequest.produtores().fire(new Receiver<List<ProdutorProxy>>() {
//			@Override
//			public void onSuccess(List<ProdutorProxy> response) {
//				tabelaProdutores.setRowData(response);
//				tabelaProdutores.redraw();
//			}
//		});
		formRow.setVisible(false);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void loadForm(ProdutorProxy produtor) {
		this.produtorEditado=produtor;
//		integradoraField.setSelectedIndex(index);
		nomeField.setValue(produtor.getNome());
		emailField.setValue(produtor.getEmail());
		codigoIntegradoraField.setValue(produtor.getCodigoIntegradora());
		ativoField.setValue(produtor.getAtivo());;
		telefoneField.setValue(produtor.getTelefone());
		
		
	}

}
