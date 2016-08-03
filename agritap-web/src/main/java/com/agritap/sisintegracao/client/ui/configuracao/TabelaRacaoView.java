package com.agritap.sisintegracao.client.ui.configuracao;

import java.util.List;
import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;
import org.gwtbootstrap3.extras.datepicker.client.ui.DatePicker;

import com.agritap.sisintegracao.client.ClientUtil;
import com.agritap.sisintegracao.client.ViewEnum;
import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.client.request.clients.RacoesClient;
import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.client.ui.StateHistory;
import com.agritap.sisintegracao.client.ui.component.BigDecimalBox;
import com.agritap.sisintegracao.model.Pessoa;
import com.agritap.sisintegracao.model.TabelaRacao;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TabelaRacaoView extends Composite {

	Logger logger = Logger.getLogger(TabelaRacaoView.class.getName());

	private static TabelaRacaoUiBinder uiBinder = GWT.create(TabelaRacaoUiBinder.class);

	ClientFactory factory;

	TabelaRacao tabelaEditada;

	RacoesClient client = GWT.create(RacoesClient.class);
	
	@UiField
	DatePicker dataInicio;
	
	@UiField
	DatePicker dataFim;
	
	@UiField
	BigDecimalBox pesoMinimo;

	interface TabelaRacaoUiBinder extends UiBinder<Widget, TabelaRacaoView> {
	}

	public TabelaRacaoView(ClientFactory factory) {
		Widget w= uiBinder.createAndBindUi(this);
		initWidget(w);
		init();
		this.factory = factory;
	}

	private void init(/*StateHistory st*/) {
	/*	String idBuscado = st.getParameter(StateHistory.ID);
		if (idBuscado != null) {
			// Editando um registro
			client.getTabela(Integer.parseInt(idBuscado), new RestCallback<TabelaRacao>() {
				@Override
				public void success(TabelaRacao tabela) {
					tabelaEditada = tabela;
					//exibeValoresAtuais();
				}
			});
		} else {
			// Registro novo
			tabelaEditada = new TabelaRacao();
		}*/
	}
	
	/*private void loadTabela() {
		client.todasTabelas(new RestCallback<List<TabelaRacao>>() {
		
			@Override
			public void success(List<TabelaRacao> result) {
				
			}
		});
	}*/
	
	@UiHandler("salvarBtn")
	public void saveClick(ClickEvent evt){
		tabelaEditada.setInicio(dataInicio.getValue());
		tabelaEditada.setFim(dataFim.getValue());
		tabelaEditada.setPesoMinimo(pesoMinimo.getValue());
		
		client.update(tabelaEditada, new RestCallback<TabelaRacao>(){
			
			@Override
			public void success(TabelaRacao tab){
				factory.getAppController().goTo(ViewEnum.TABELAS_RACAO);
			}
		});
	}

}

// List<TipoRacaoI> tiposRacao;
