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
import com.agritap.sisintegracao.client.request.beans.PedidosI;
import com.agritap.sisintegracao.client.request.beans.PedidosIAdapter;

import com.agritap.sisintegracao.client.request.clients.PedidosClient;

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

public class PedidosView extends Composite {

	private static PedidosUiBinder uiBinder = GWT.create(PedidosUiBinder.class);

	PedidosClient client = new PedidosClient();
	
	ClientFactory factory;
	@UiField
	CellTable<PedidosI> tabelaPedidos;
	@UiField
	HTMLPanel addPedidos;

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
	
	PedidosI pedidosEditado;
	
	interface PedidosUiBinder extends UiBinder<Widget, PedidosView> {
	}

	public PedidosView(ClientFactory factory) {
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
		
		addPedidos.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				pedidosEditado = factory.getEntityfactory().newPedidos1().as();
				loadForm(pedidosEditado);
				 formRow.setVisible(true);
			}
		}, ClickEvent.getType());
	}

	private void loadTabela() {
		client.todos(new Callback<PedidosIAdapter>() {
			
			@Override
			public void ok(PedidosIAdapter response) {
				tabelaPedidos.setRowData(response.getResultado());
				tabelaPedidos.redraw();
			}
		});
	}

	private void preparaTabela() {
		TextColumn<PedidosI> nomeColumn = new TextColumn<PedidosI>() {
			@Override
			public String getValue(PedidosI pedidos) {
				return pedidos.getNome();
			}
		};
		TextColumn<PedidosI> emailColumn = new TextColumn<PedidosI>() {
			@Override
			public String getValue(PedidosI pedidos) {
				return pedidos.getEmail();
			}
		};
		
		TextColumn<PedidosI> telefoneColumn = new TextColumn<PedidosI> (){
			@Override
			public String getValue(PedidosI pedidos){
				return pedidos.getTelefone();
				
						
			}
			
		};
		
		TextColumn<PedidosI> ativo2Column = new TextColumn<PedidosI>() {
			@Override
			public String getValue(PedidosI pedidos) {
				if(pedidos.getAtivo() == null || pedidos.getAtivo()){
					return "true";
				}else{
					return "false";
				}
			}
			@Override
			public void render(Context context, PedidosI pedidos, SafeHtmlBuilder sb) {
				if(pedidos.getAtivo() == null || pedidos.getAtivo()){
					sb.appendHtmlConstant("<i class=\"fa fa-check-square-o\"></i>");
				}else{
					sb.appendHtmlConstant("<i class=\"fa fa-square-o\"></i>");
				}
			}
		};
		 final Column<PedidosI, String> click = new Column<PedidosI, String>(new ButtonCell(ButtonType.PRIMARY, IconType.EDIT)) {
	            @Override
	            public String getValue(PedidosI object) {
	                return "";
	            }
	        };
	        
	        click.setFieldUpdater(new FieldUpdater<PedidosI, String>() {
            @Override
            public void update(int index, PedidosI pedidos, String value) {
            	loadForm(pedidos);
                formRow.setVisible(true);
            }

        });
		ativo2Column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		tabelaPedidos.addColumn(nomeColumn, "Nome");
		tabelaPedidos.addColumn(emailColumn, "Email");
		tabelaPedidos.addColumn(telefoneColumn, "Telefone");	
		tabelaPedidos.addColumn(ativo2Column, "Ativo");
		tabelaPedidos.addColumn(click, "Ações");
	}
	
	@UiHandler("salvarBtn")
	public void salvarClick(ClickEvent evt){
		pedidosEditado.setNome(nomeField.getValue());
		pedidosEditado.setEmail(emailField.getValue());
		pedidosEditado.setTelefone(telefoneField.getValue());
		pedidosEditado.setAtivo(ativoField.getValue());
		pedidosEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
		client.update(pedidosEditado, new Callback<PedidosI>() {

			@Override
			public void ok(PedidosI to) {
				loadTabela();
				pedidosEditado=null;
				formRow.setVisible(false);
			}
		});
	}
	@UiHandler("cancelarBtn")
	public void cancelarClick(ClickEvent evt){
		formRow.setVisible(false);
		pedidosEditado=null;
	}

	@UiHandler("excluirBtn")
	public void excluirClick(ClickEvent evt){
		pedidosEditado.setNome(nomeField.getValue());
		pedidosEditado.setEmail(emailField.getValue());
		pedidosEditado.setTelefone(telefoneField.getValue());
		pedidosEditado.setAtivo(ativoField.getValue());
		pedidosEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
		client.delete(pedidosEditado.getId(), new Callback<Void>() {

			@Override
			public void ok(Void to) {
				loadTabela();
				pedidosEditado=null;
				formRow.setVisible(false);
			}
		});
	}
//	

	public void loadForm(PedidosI pedidos) {
		this.pedidosEditado=pedidos;
		nomeField.setValue(pedidos.getNome());
		emailField.setValue(pedidos.getEmail());
		codigoIntegradoraField.setValue(pedidos.getCodigoIntegradora());
		ativoField.setValue(pedidos.getAtivo());;
		telefoneField.setValue(pedidos.getTelefone());
		
		
	}

}
