package com.agritap.sisintegracao.client.ui.configuracao;

import java.util.List;
import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.agritap.sisintegracao.client.ClientUtil;
import com.agritap.sisintegracao.client.ViewEnum;
import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.client.request.clients.RacoesClient;
import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.client.ui.StateHistory;
import com.agritap.sisintegracao.model.TabelaRacao;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class TabelasRacaoView extends Composite {
	
	@UiField
	CellTable resultTable;
	
	@UiField
	HTMLPanel addTabelaRacao;

	Logger logger = Logger.getLogger(TabelasRacaoView.class.getName());
	
	private static TabelaRacaoUiBinder uiBinder = GWT
			.create(TabelaRacaoUiBinder.class);

	ClientFactory factory;
	
	RacoesClient client = GWT.create(RacoesClient.class);

	interface TabelaRacaoUiBinder extends UiBinder<Widget, TabelasRacaoView> {
	}

	public TabelasRacaoView(ClientFactory factory) {
		initWidget(uiBinder.createAndBindUi(this));
		bindAddEvent();
		this.factory = factory;
		init();
	}
	
	private void init() {
		prepareResultTable();
		loadResultTable();
	}
	
	private void loadResultTable() {
		//pedir para o servidor as tabelas de racao existente
		client.todasTabelas(new RestCallback<List<TabelaRacao>>(){

			@Override
			public void success(List<TabelaRacao> result) {
				//aqui tem todas as tabelas
				resultTable.setRowData(result);
			}});
	}

	private void prepareResultTable(){
		TextColumn<TabelaRacao> pesoColumn = new TextColumn<TabelaRacao>() {
			@Override
			public String getValue(TabelaRacao tabelaRacao) {
				if(tabelaRacao.getPesoMinimo() != null){
					return tabelaRacao.getPesoMinimo().toString();
				}else{
					return null;
				}
			}
		};
		resultTable.addColumn(pesoColumn,"Peso de Entrada");
		
		TextColumn<TabelaRacao> inicioColumn = new TextColumn<TabelaRacao>() {
			@Override
			public String getValue(TabelaRacao tabelaRacao) {
				return ClientUtil.formatDate(tabelaRacao.getInicio());
			}
		};
		resultTable.addColumn(inicioColumn,"Entrada em Vigencia da Tabela");
		
		TextColumn<TabelaRacao> fimColumn = new TextColumn<TabelaRacao>() {
			@Override
			public String getValue(TabelaRacao tabelaRacao) {
				return ClientUtil.formatDate(tabelaRacao.getFim());
			}
		};
		resultTable.addColumn(fimColumn,"Entrada do fim da vigencia da Tabela");

		
		final Column<TabelaRacao, String> click = new Column<TabelaRacao, String>(
				new ButtonCell(ButtonType.PRIMARY, IconType.EDIT)) {
			@Override
			public String getValue(TabelaRacao object) {
				return "";
			}
		};

		click.setFieldUpdater(new FieldUpdater<TabelaRacao,String>() {
			@Override
			public void update(int index, TabelaRacao tipoRacao, String value) {
				
				StateHistory st = new StateHistory(ViewEnum.TABELA_RACAO);
				st.addParam(StateHistory.ID, tipoRacao.getId().toString());
				factory.getAppController().goTo(st);
			}

		});
		resultTable.addColumn(click);
		
	}
	
	private void bindAddEvent() {
		
		addTabelaRacao.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				factory.getAppController().goTo(ViewEnum.TABELA_RACAO);
			}
		}, ClickEvent.getType());
	}	
	
	
	
}

//	List<TipoRacaoI> tiposRacao;




	