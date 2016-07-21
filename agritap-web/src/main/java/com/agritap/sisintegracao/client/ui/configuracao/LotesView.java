package com.agritap.sisintegracao.client.ui.configuracao;

import java.util.Date;
import java.util.List;

import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.agritap.sisintegracao.client.ViewEnum;
import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.client.request.clients.LoteClient;
import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.client.vo.LoteVO;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class LotesView extends Composite {

	private static LotesViewUiBinder uiBinder = GWT.create(LotesViewUiBinder.class);

	interface LotesViewUiBinder extends UiBinder<Widget, LotesView> {
	}

	@UiField
	CellTable resultados;
	
	ClientFactory factory;
	
	LoteClient loteCliente = GWT.create(LoteClient.class);
	@UiField
	HTMLPanel addLote;

	public LotesView(ClientFactory factory) {
		initWidget(uiBinder.createAndBindUi(this));
		bindAddEvent();
		this.factory=factory;
		init();
	}

	private void init() {
		Column<LoteVO, String> produtorColumn = new Column<LoteVO, String>(new TextCell()) {
			@Override
			public String getValue(LoteVO lote) {
				return lote.getProdutor();
			}
		};
		Column<LoteVO, Date> alojamentoColumn = new Column<LoteVO, Date>(new DateCell()) {
			@Override
			public Date getValue(LoteVO lote) {
				return lote.getDataAlojamento();
			}
		};

		Column<LoteVO, Date> saidaColumn = new Column<LoteVO, Date>(new DateCell()) {
			@Override
			public Date getValue(LoteVO lote) {
				return lote.getDataSaida();
			}
		};
		Column<LoteVO, String> granjeirosColumn = new Column<LoteVO, String>(new TextCell()) {
			@Override
			public String getValue(LoteVO lote) {
				return lote.getGranjeiros();
			}
		};
		Column<LoteVO, Number> resultadoAnimal = new Column<LoteVO, Number>(new NumberCell()) {
			@Override
			public Number getValue(LoteVO lote) {
				return lote.getValorMedioAnimalPago();
			}
		};
		
		if(!factory.getUsuarioAutenticado().isProdutorUnico()){
			resultados.addColumn(produtorColumn);
		}
		resultados.addColumn(alojamentoColumn);
		resultados.addColumn(granjeirosColumn);
		resultados.addColumn(saidaColumn);
		resultados.addColumn(resultadoAnimal);
		
		
		loteCliente.buscar(null, new RestCallback<List<LoteVO>>(){

			@Override
			public void success(List<LoteVO> result) {
					if(result==null || result.size()==0){
						resultados.setVisible(false);
					}
			}
			
		});
	}

	private void bindAddEvent() {
		
		addLote.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				factory.getAppController().goTo(ViewEnum.LOTE);
			}
		}, ClickEvent.getType());
	}	
	
}
