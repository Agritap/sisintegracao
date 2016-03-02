package com.agritap.sisintegracao.client.ui.configuracao;

import java.util.List;
import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;
import org.gwtbootstrap3.extras.datepicker.client.ui.DatePicker;

import com.agritap.sisintegracao.client.ClientUtil;
import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.client.request.clients.RacoesClient;
import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.model.TabelaRacao;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class TabelaRacaoView extends Composite {

	Logger logger = Logger.getLogger(TabelaRacaoView.class.getName());
	
	private static TabelaRacaoUiBinder uiBinder = GWT
			.create(TabelaRacaoUiBinder.class);



	ClientFactory factory;
	
	@UiField
	CellTable<TabelaRacao> TabelaRacao;
	
	@UiField
	HTMLPanel addTipoRacao;

	@UiField
	Row formRow;

	@UiField
	DatePicker DataInicioField;

	@UiField
	DatePicker DataFimField;

	@UiField
	TextBox PesoMinimoField;

	@UiField
	HTMLPanel painelItens;


	TabelaRacao tabelaRacaoEditado;
	RacoesClient client = GWT.create(RacoesClient.class);

	interface TabelaRacaoUiBinder extends UiBinder<Widget, TabelaRacaoView> {
	}

	public TabelaRacaoView(ClientFactory factory) {
		initWidget(uiBinder.createAndBindUi(this));
		init();
		this.factory = factory;
	}

	private void init() {
		preparaTabelaRacao();
		loadTabela();
		bindAddEvent();
	}

//	List<TipoRacaoI> tiposRacao;
	@UiHandler("addItem")
	public void adicionarItemTabela(ClickEvent evt){
//		if(tiposRacao==null){
//			/busca no sevidor
//		}
		//adiciona elemento visual
		ListBox tiposRacao=new ListBox();
		//Os itens do listbox sao objetos do tipo TipoRacao
		tiposRacao.addItem("Tipo 1");
		tiposRacao.addItem("Tipo 2");
		painelItens.add(tiposRacao);
//		painelItens.add(child);
	}
	private void bindAddEvent() {

		addTipoRacao.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				tabelaRacaoEditado = new TabelaRacao();
				loadForm(tabelaRacaoEditado);
				formRow.setVisible(true);

			}
		}, ClickEvent.getType());
	}

	private void loadTabela() {
		logger.info("Carregando a tabela");
		client.todasTabelas(new RestCallback<List<TabelaRacao>>() {
			
			@Override
			public void success(List<TabelaRacao> result) {
				logger.info("retornou todos os elementos"+result.size());
				TabelaRacao.setRowData(result);
				TabelaRacao.redraw();
			}
		});
	}

	private void preparaTabelaRacao() {
		
		TextColumn<TabelaRacao> dataInicioColumn = new TextColumn<TabelaRacao>() {
			@Override
			public String getValue(TabelaRacao TabelaRacao) {
				logger.info("coluna datainicio");
				if (TabelaRacao.getInicio() == null) {
					logger.info("Inicio nulo");
					return null;
				}
				logger.info("Valor da data de incio"+TabelaRacao.getInicio());
				
				String resultado = DateTimeFormat.getFormat("dd/MM/yyyy").format(
						TabelaRacao.getInicio());
				logger.info("retornara coluna datainicio");
				return resultado;
			}
		};

		TextColumn<TabelaRacao> dataFimColumn = new TextColumn<TabelaRacao>() {
			@Override
			public String getValue(TabelaRacao TabelaRacao) {
				logger.info("coluna datafim");
				if (TabelaRacao.getFim() == null) {
					return null;
				}
				return DateTimeFormat.getFormat("dd/MM/yyyy").format(
						TabelaRacao.getFim());
			}
		};
		
		TextColumn<TabelaRacao> pesoMinimoColumn = new TextColumn<TabelaRacao>() {
			@Override
			public String getValue(TabelaRacao TabelaRacao) {
			logger.info("coluna peso minimo");
				if(TabelaRacao.getPesoMinimo()==null){
					return null;
				}
		    	NumberFormat numberFormat = NumberFormat.getFormat("#.##");
				return numberFormat.format(TabelaRacao.getPesoMinimo()); 
				
			}
		};

		final Column<TabelaRacao, String> click = new Column<TabelaRacao, String>(
				new ButtonCell(ButtonType.PRIMARY, IconType.EDIT)) {
			@Override
			public String getValue(TabelaRacao object) {
				return "";
			}
		};

		click.setFieldUpdater(new FieldUpdater<TabelaRacao, String>() {
			@Override
			public void update(int index, TabelaRacao tabelaRacao, String value) {
				loadForm(tabelaRacao);
				formRow.setVisible(true);
			}
		});
		
		TabelaRacao.addColumn(dataInicioColumn, "Data Inicio");
		TabelaRacao.addColumn(dataFimColumn, "Data Fim");
		TabelaRacao.addColumn(pesoMinimoColumn, "Peso Minimo");
		TabelaRacao.addColumn(click, "Ação");


	}

	@UiHandler("salvarBtn")
	public void salvarClick(ClickEvent evt) {
		tabelaRacaoEditado.setInicio(DataInicioField.getValue());
		tabelaRacaoEditado.setFim(DataFimField.getValue());
    	tabelaRacaoEditado.setPesoMinimo(ClientUtil.parseBigDecimal(PesoMinimoField.getValue()));
		

		client.update(tabelaRacaoEditado, new RestCallback<TabelaRacao>() {

			@Override
			public void success(com.agritap.sisintegracao.model.TabelaRacao result) {
				loadTabela();
				tabelaRacaoEditado = null;
				formRow.setVisible(false);				
			}
		});
	}

	@UiHandler("cancelarBtn")
	public void cancelarClick(ClickEvent evt) {
		formRow.setVisible(false);
		tabelaRacaoEditado = null;
	}

	@UiHandler("excluirBtn")
	public void excluirClick(ClickEvent evt) {
//		tabelaRacaoEditado.setInicio(DataInicioField.getValue());
//		tabelaRacaoEditado.setFim(DataFimField.getValue());
//
//		client.delete(tabelaRacaoEditado.getId(), new Callback<Void>() {
//
//			@Override
//			public void ok(Void to) {
//				loadTabela();
//				tabelaRacaoEditado = null;
//				formRow.setVisible(false);
//			}
//		});
	}

	public void loadForm(TabelaRacao tabelaRacao) {
		//Se tiver itens tem q carregar os itens aqui embaixo
		this.tabelaRacaoEditado = tabelaRacao;
		DataInicioField.setValue(tabelaRacao.getInicio());
		DataFimField.setValue(tabelaRacao.getFim());
		PesoMinimoField.setValue(ClientUtil.formatBigDecimal(tabelaRacao.getPesoMinimo()));
	}
}
