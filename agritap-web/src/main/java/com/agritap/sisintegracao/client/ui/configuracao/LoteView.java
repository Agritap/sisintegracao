package com.agritap.sisintegracao.client.ui.configuracao;

import java.util.List;
import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.FormGroup;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.InputGroup;
import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.html.Span;
import org.gwtbootstrap3.extras.datepicker.client.ui.DatePicker;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.DatePickerLanguage;

import com.agritap.sisintegracao.client.ClientUtil;
import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.client.request.clients.ModulosClient;
import com.agritap.sisintegracao.client.request.clients.PessoaClient;
import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.client.ui.StateHistory;
import com.agritap.sisintegracao.client.ui.component.BigDecimalBox;
import com.agritap.sisintegracao.model.Modulo;
import com.agritap.sisintegracao.model.Pessoa;
import com.agritap.sisintegracao.model.SexoLote;
import com.agritap.sisintegracao.model.TipoOrigem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoteView extends Composite {


	private static LoteViewUiBinder uiBinder = GWT.create(LoteViewUiBinder.class);

	ModulosClient modulosClient = GWT.create(ModulosClient.class);
	
	Logger log = Logger.getLogger(LoteView.class.getName());

	ClientFactory factory;
	PessoaClient pessoaClient = GWT.create(PessoaClient.class);
	@UiField
	Heading loteTitulo;
	@UiField
	ListBox moduloField;
	@UiField
	ListBox produtoresField;
	@UiField
    ListBox tecnicoField;
	@UiField
    ListBox granjeiroField;
	@UiField
	ListBox sexoField;
	@UiField
	HTMLPanel blocoDetalhamentoOrigem;
	@UiField
	ListBox tipoOrigemField;
	@UiField
	HTMLPanel blockQuoteOrigens;
	@UiField
	IntegerBox quantidadeAlojadaField;
	@UiField
	IntegerBox idadeMediaAlojadaField;
	
	@UiField
	IntegerBox quantidadeOrigensField;
	@UiField
	BigDecimalBox pesoMedioAlojadoField;
	
	interface LoteViewUiBinder extends UiBinder<Widget, LoteView> {
	}
	public LoteView(ClientFactory factory,StateHistory st) {
		initWidget(uiBinder.createAndBindUi(this));
		this.factory=factory;	
		initForm();
		if(st.getParameter("id")==null){
			loteTitulo.setText("Novo Lote");
		}else{
			loadLote(Integer.parseInt(st.getParameter("id")));
		}
	}
	
	private void initForm() {
		ClientUtil.populateListBox(produtoresField, factory.getUsuarioAutenticado().getProdutores());
		if(factory.getUsuarioAutenticado().getProdutores().size()==1){
			changeProdutor(null);
		}
		pessoaClient.porTipo("tecnico", new RestCallback<List<Pessoa>>() {
			@Override
			public void success(List<Pessoa> result) {
				ClientUtil.populateListBox(tecnicoField, result);
			}
		});
		pessoaClient.porTipo("granjeiro", new RestCallback<List<Pessoa>>() {
			@Override
			public void success(List<Pessoa> result) {
				ClientUtil.populateListBox(granjeiroField, result);
			}
		});
	
		ClientUtil.populaListBox(sexoField, SexoLote.values());
		ClientUtil.populaListBox(tipoOrigemField, TipoOrigem.values());
		ClientUtil.prepareIntegerBox(quantidadeAlojadaField);
		ClientUtil.prepareIntegerBox(idadeMediaAlojadaField);
		ClientUtil.prepareIntegerBox(quantidadeOrigensField);
	}
	
	@UiHandler("addOrigem")
	public void addOrigemClick(ClickEvent evt){
		addOrigem(null);
	}
	int quantidadeOrigens=0;
	private void addOrigem(String alojamentoOrigem) {
		blockQuoteOrigens.setVisible(false);
		quantidadeOrigens++;
		if(quantidadeOrigens>0){
//			addCabecalho();
		}
		String color = quantidadeOrigens%2==0?"#cccccc":"#eeeeee";
		Row row = new Row();
		row.getElement().getStyle().setBackgroundColor(color);
		row.getElement().getStyle().setPaddingTop(15, Unit.PX);
		
		Column colunaDt = new Column("SM_4,MD_2");
		DatePicker dp = new DatePicker();
		dp.setPlaceholder("Data do alojamento");
		dp.setFormat("dd-mm-yyyy");
		dp.setLanguage(DatePickerLanguage.PT_BR);
		
		FormGroup fg = new FormGroup();
		fg.add(dp);
		
		colunaDt.add(fg);
		row.add(colunaDt);
		
		Column colunaQtd = new Column("SM_4,MD_2");
		TextBox tbQtd = new TextBox();
		tbQtd.setPlaceholder("Quantidade Alojada");
		fg = new FormGroup();
		fg.add(tbQtd);
		colunaQtd.add(fg);
		row.add(colunaQtd);
		
		
		Column colunaMort = new Column("SM_4,MD_2");
		TextBox tbMort = new TextBox();
		tbMort.setPlaceholder("Mortos");
		fg = new FormGroup();
		fg.add(tbMort);
		colunaMort.add(fg);
		row.add(colunaMort);

		Column colunaIntegrado = new Column("SM_8,MD_4");
		ListBox listIntegrado = new ListBox();
		listIntegrado.addItem("Vasconcelos");
		listIntegrado.addItem("Rejane");
		listIntegrado.addItem("Vasconcelos");
		fg = new FormGroup();
		fg.add(listIntegrado);
		colunaIntegrado.add(fg);
		row.add(colunaIntegrado);
		
		
		InputGroup inputG = new InputGroup();
		
		Column colunaPeso = new Column("SM_4,MD_2");
		TextBox tbPeso = new TextBox();
		tbPeso.setPlaceholder("Peso Total");
		inputG.add(tbPeso);
		
		Span span = new Span();
		span.addStyleName("input-group-btn");
		Button btn = new Button();
		btn.setIcon(IconType.TRASH);
		span.add(btn);
		inputG.add(span);
		
		
		colunaPeso.add(inputG);
		row.add(colunaPeso);
		blocoDetalhamentoOrigem.add(row);
	}

	@UiHandler("produtoresField")
	public void changeProdutor(ChangeEvent evt){
		Integer produtorId = ClientUtil.parseInteger(produtoresField.getSelectedValue());
		if(produtorId!=null){
			modulosClient.porProdutor(produtorId, new RestCallback<List<Modulo>>() {
				@Override
				public void success(List<Modulo> modulos) {
					moduloField.clear();
					if(modulos.size()>1){
						moduloField.addItem("");
					}
					for(Modulo modulo: modulos){
						moduloField.addItem(modulo.getNome(),modulo.getId().toString());
					}
				}
			});
		}
	}
	
	private void loadLote(int parseInt) {
		
	}
}
