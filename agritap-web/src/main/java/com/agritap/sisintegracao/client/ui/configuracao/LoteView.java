package com.agritap.sisintegracao.client.ui.configuracao;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.FormGroup;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.InputGroup;
import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.html.Span;
import org.gwtbootstrap3.extras.datepicker.client.ui.DatePicker;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.DatePickerLanguage;

import com.agritap.sisintegracao.client.ClientUtil;
import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.client.request.clients.LoteClient;
import com.agritap.sisintegracao.client.request.clients.ModulosClient;
import com.agritap.sisintegracao.client.request.clients.OrigemClient;
import com.agritap.sisintegracao.client.request.clients.PessoaClient;
import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.client.ui.StateHistory;
import com.agritap.sisintegracao.client.ui.component.BigDecimalBox;
import com.agritap.sisintegracao.model.Lote;
import com.agritap.sisintegracao.model.Modulo;
import com.agritap.sisintegracao.model.Origem;
import com.agritap.sisintegracao.model.Pessoa;
import com.agritap.sisintegracao.model.SexoLote;
import com.agritap.sisintegracao.model.TipoAnimal;
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

	/**
	 * Módulo a ser editado
	 */
	private Lote lote;
	
	ModulosClient modulosClient = GWT.create(ModulosClient.class);
	
	OrigemClient origemClient = GWT.create(OrigemClient.class);

	LoteClient loteCliente = GWT.create(LoteClient.class);

	Logger log = Logger.getLogger(LoteView.class.getName());

	List<Origem> origens;
	
	ClientFactory factory;
	PessoaClient pessoaClient = GWT.create(PessoaClient.class);
	@UiField
	Heading loteTitulo;
	@UiField
	Button addOrigem;
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

	//Variaveis de controle da quantidade de origens
	int quantidadeOrigens=0;
	List<DatePicker> datasAlojamentoOrigensFields = new LinkedList<DatePicker>();
	List<IntegerBox> quantidadeAlojadaOrigensFields= new LinkedList<IntegerBox>();
	List<IntegerBox> mortosTransporteOrigensFields= new LinkedList<IntegerBox>();
	List<ListBox> integradoOrigemOrigensFields= new LinkedList<ListBox>();
	List<BigDecimalBox> pesoTotalOrigensFields= new LinkedList<BigDecimalBox>();
	List<IntegerBox> idadeAlojamentoOrigensFields= new LinkedList<IntegerBox>();
	List<ListBox> sexoAlojamentoOrigensFields= new LinkedList<ListBox>();

	
	interface LoteViewUiBinder extends UiBinder<Widget, LoteView> {
	}
	public LoteView(ClientFactory factory,StateHistory st) {
		initWidget(uiBinder.createAndBindUi(this));
		this.factory=factory;	
		initForm();
		if(st.getParameter("id")==null){
			loteTitulo.setText("Novo Lote");
			lote = new Lote();
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
		
		
	
		ClientUtil.populateListBox(sexoField, SexoLote.values());
		ClientUtil.selectListBox(sexoField,SexoLote.MISTO);
		ClientUtil.populateListBox(tipoOrigemField, TipoOrigem.values());
		ClientUtil.prepareIntegerBox(quantidadeAlojadaField);
		ClientUtil.prepareIntegerBox(idadeMediaAlojadaField);
		ClientUtil.prepareIntegerBox(quantidadeOrigensField);
	}
	
	
	
	@UiHandler("addOrigem")
	public void addOrigemClick(ClickEvent evt){
		if(quantidadeOrigens==0){
			addOrigem.setText("Nova Origem");
			addOrigem.setMarginTop(5);
		}
		addOrigem(null);
	}
	
	
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
		
		Column colunaDt = new Column("XS_4,MD_2");
		colunaDt.addStyleName("small-padding");
		DatePicker dp = new DatePicker();
		dp.setPlaceholder("Data do alojamento");
		dp.setFormat("dd-mm-yyyy");
		dp.setLanguage(DatePickerLanguage.PT_BR);
		datasAlojamentoOrigensFields.add(dp);
		
		FormGroup fg = new FormGroup();
		fg.add(dp);
		
		colunaDt.add(fg);
		row.add(colunaDt);
		
		Column colunaQtd = new Column("XS_4,MD_2");
		colunaQtd.addStyleName("small-padding");

		IntegerBox tbQtd = new IntegerBox();
		ClientUtil.prepareIntegerBox(tbQtd);
		tbQtd.setPlaceholder("Quantidade Alojada");
		fg = new FormGroup();
		fg.add(tbQtd);
		colunaQtd.add(fg);
		row.add(colunaQtd);
		quantidadeAlojadaOrigensFields.add(tbQtd);

		Column colunaIdade = new Column("XS_2,MD_1");
		colunaIdade.addStyleName("small-padding");
		IntegerBox tbIdade = new IntegerBox();
		ClientUtil.prepareIntegerBox(tbIdade);
		tbIdade.setPlaceholder("Idade Média");
		fg = new FormGroup();
		fg.add(tbIdade);
		colunaIdade.add(fg);
		row.add(colunaIdade);
		idadeAlojamentoOrigensFields.add(tbIdade);
		
		Column colunaMort = new Column("XS_2,MD_1");
		colunaMort.addStyleName("small-padding");
		IntegerBox tbMort = new IntegerBox();
		tbMort.setPlaceholder("Mortos no transporte");
		ClientUtil.prepareIntegerBox(tbMort);
		fg = new FormGroup();
		fg.add(tbMort);
		colunaMort.add(fg);
		row.add(colunaMort);
		mortosTransporteOrigensFields.add(tbMort);

		Column colunaIntegrado = new Column("XS_4,MD_2");
		colunaIntegrado.addStyleName("small-padding");
		ListBox listIntegrado = new ListBox();
		ClientUtil.populateListBox(listIntegrado, origens);
		fg = new FormGroup();
		fg.add(listIntegrado);
		colunaIntegrado.add(fg);
		row.add(colunaIntegrado);
		integradoOrigemOrigensFields.add(listIntegrado);
		
		Column colunaPeso = new Column("XS_4,MD_2");
		colunaPeso.addStyleName("small-padding");
		BigDecimalBox tbPeso = new BigDecimalBox();
		tbPeso.setPlaceholder("Peso Total");
		fg = new FormGroup();
		fg.add(tbPeso);
		colunaPeso.add(fg);
		row.add(colunaPeso);
		pesoTotalOrigensFields.add(tbPeso);

		
		Column colunaSexo = new Column("XS_4,MD_1");
		colunaSexo.addStyleName("small-padding");
		InputGroup inputG = new InputGroup();
		ListBox listSexo = new ListBox();
		ClientUtil.populateListBox(listSexo, SexoLote.values());
		inputG.add(listSexo);
		
		Span span = new Span();
		span.addStyleName("input-group-btn");
		Button btn = new Button();
		btn.setIcon(IconType.TRASH);
		span.add(btn);
		inputG.add(span);
		colunaSexo.add(inputG);
		row.add(colunaSexo);
		sexoAlojamentoOrigensFields.add(listSexo);

		
		blocoDetalhamentoOrigem.add(row);
		updateDadosAlojamento();
	}

	@UiHandler("produtoresField")
	public void changeProdutor(ChangeEvent evt){
		Integer produtorId = ClientUtil.parseInteger(produtoresField.getSelectedValue());
		if(produtorId!=null){
			modulosClient.porProdutor(produtorId, new RestCallback<List<Modulo>>() {
				@Override
				public void success(List<Modulo> modulos) {
					ClientUtil.populateListBox(moduloField, modulos);
					Set<TipoAnimal> tiposAnimais=new HashSet<>();
					for(Modulo m:modulos){
						tiposAnimais.add(m.getTipoAnimal());
					}
					if(tiposAnimais.size()==1){
						loadOrigens(tiposAnimais.iterator().next());
					}
				}

				
			});
		}
	}
//	
	private void loadOrigens(TipoAnimal tipo) {
		if(tipo==null){
			return;
		}
		if(origens!=null && origens.size()>0){
			if(origens.get(0).getAnimal().equals(tipo)){
				//ja buscou e ta no cache
				return;
			}
		}
		origemClient.porTipo(tipo, new RestCallback<List<Origem>>() {
			@Override
			public void success(List<Origem> result) {
				origens = result;
			}
		});
	}
	
	public void updateDadosAlojamento(){
		if(quantidadeOrigens>0){
			//Desabilita dados de alojamento ao mesmo tempo que sumariza os mesmos
			Integer quantidadeTotal=0;
			Integer idadeConsolidada=0;
			SexoLote sexoFinal = null;
			BigDecimal pesoTotal = BigDecimal.ZERO;
			for(int i=0;i<quantidadeOrigens;i++){
				IntegerBox quantidadeOrigemBox = quantidadeAlojadaOrigensFields.get(i);
				IntegerBox mortalidadeOrigemBox = mortosTransporteOrigensFields.get(i);
				IntegerBox idadeOrigemBox =idadeAlojamentoOrigensFields.get(i);
				ListBox origemBox = integradoOrigemOrigensFields.get(i);
				BigDecimalBox pesoTotalBox = pesoTotalOrigensFields.get(i);
				ListBox sexoBox = sexoAlojamentoOrigensFields.get(i);
				if(quantidadeOrigemBox.getValue()!=null){
					quantidadeTotal+=quantidadeOrigemBox.getValue();
					if(idadeOrigemBox.getValue()!=null){
						idadeConsolidada += quantidadeTotal*idadeOrigemBox.getValue();
					}
				}
				if(pesoTotalBox.getValue()!=null){
					pesoTotal = pesoTotal.add(pesoTotalBox.getValue());
				}
				if(mortalidadeOrigemBox.getValue()!=null){
					quantidadeTotal =quantidadeTotal - mortalidadeOrigemBox.getValue();
				}

				String s = sexoBox.getSelectedValue();
				if(!ClientUtil.isEmpty(s)){
					SexoLote sf = SexoLote.valueOf(s);
					if(sexoFinal==null){
						sexoFinal=sf;
					}else{
						if(!sexoFinal.equals(sf)){
							sexoFinal = SexoLote.MISTO;
						}
					}
				}

			}
			if(quantidadeTotal>0){
				quantidadeAlojadaField.setValue(quantidadeTotal);
			}
			if(sexoFinal!=null){
				ClientUtil.selectListBox(sexoField, sexoFinal);
			}
			quantidadeAlojadaField.setEnabled(false);
			pesoMedioAlojadoField.setEnabled(false);
			idadeMediaAlojadaField.setEnabled(false);
			sexoField.setEnabled(false);
			tipoOrigemField.setEnabled(false);
			quantidadeOrigensField.setEnabled(false);
		}else{
			quantidadeAlojadaField.setEnabled(true);
			pesoMedioAlojadoField.setEnabled(true);
			idadeMediaAlojadaField.setEnabled(true);
			sexoField.setEnabled(true);
			tipoOrigemField.setEnabled(true);
			quantidadeOrigensField.setEnabled(true);
			//Habilita os dados de alojamento para edicao
		}
	}
	
	private void loadLote(Integer loteId) {
		loteCliente.get(loteId, new RestCallback<Lote>() {
			@Override
			public void success(Lote result) {
				lote=result;
				
			}
		});
	}

}
