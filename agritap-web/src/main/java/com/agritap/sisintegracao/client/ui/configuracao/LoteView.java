package com.agritap.sisintegracao.client.ui.configuracao;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.BlockQuote;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.FormGroup;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.InputGroup;
import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.ListGroup;
import org.gwtbootstrap3.client.ui.ListGroupItem;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.html.Span;
import org.gwtbootstrap3.extras.datepicker.client.ui.DatePicker;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.DatePickerLanguage;

import com.agritap.sisintegracao.client.ClientUtil;
import com.agritap.sisintegracao.client.ViewEnum;
import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.client.request.clients.LoteClient;
import com.agritap.sisintegracao.client.request.clients.ModulosClient;
import com.agritap.sisintegracao.client.request.clients.OrigemClient;
import com.agritap.sisintegracao.client.request.clients.PessoaClient;
import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.client.ui.StateHistory;
import com.agritap.sisintegracao.client.ui.component.BigDecimalBox;
import com.agritap.sisintegracao.client.ui.component.ListBox;
import com.agritap.sisintegracao.client.vo.ErrosValidacao;
import com.agritap.sisintegracao.model.Lote;
import com.agritap.sisintegracao.model.Modulo;
import com.agritap.sisintegracao.model.Origem;
import com.agritap.sisintegracao.model.OrigemAlojamento;
import com.agritap.sisintegracao.model.Pessoa;
import com.agritap.sisintegracao.model.SexoLote;
import com.agritap.sisintegracao.model.TipoAnimal;
import com.agritap.sisintegracao.model.TipoOrigem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
	DatePicker dataAlojamentoField;
	@UiField
	DatePicker dataSaidaField;
	@UiField
	HTMLPanel salvarBtn;
	@UiField
	ListGroup errosDescricao;
	@UiField
	Form formEntrada;
	@UiField
	IntegerBox quantidadeOrigensField;
	@UiField
	BigDecimalBox pesoMedioAlojadoField;
	@UiField
	BlockQuote erros;
	@UiField
	HTMLPanel blocoDetalhamentoAbate;
	
	//Variaveis de controle da quantidade de origens
	List<OrigemAlojamento> origensAlojamento;
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
		Widget w= uiBinder.createAndBindUi(this);
		initWidget(w);
		this.factory=factory;	
		if(st.getParameter("id")==null){
			initForm(null);
		}else{
			initForm(Integer.parseInt(st.getParameter("id")));
		}
		bindEvents();
		erros.addStyleName("danger");
	}
	
	private void initForm(Integer idLote) {
		sexoField.setItems(SexoLote.values());
		sexoField.setSelectedValue(SexoLote.MISTO);
		tipoOrigemField.setItems(TipoOrigem.values());
		ClientUtil.prepareIntegerBox(quantidadeAlojadaField);
		ClientUtil.prepareIntegerBox(idadeMediaAlojadaField);
		ClientUtil.prepareIntegerBox(quantidadeOrigensField);

		loteCliente.get(idLote, new RestCallback<LoteViewVO>(){

			@Override
			public void success(LoteViewVO result) {
				List<Pessoa> produtores = result.getProdutores();
				List<Pessoa> granjeiros = result.getGranjeiros();
				List<Pessoa> tecnicos = result.getTecnicos();
				if(result.getLote()==null){
					lote = new Lote();
					loteTitulo.setText("Novo Lote");
				}else{
					loteTitulo.setText("Editar");
					lote = result.getLote();
				}
				if(result.getLote()!=null){
					if(!produtores.contains(result.getLote().getProdutor())){
						produtores.add(result.getLote().getProdutor());
					}
					
					if(result.getLote().getGranjeiroResponsavel()!=null){
						if(!granjeiros.contains(result.getLote().getGranjeiroResponsavel())){
							granjeiros.add(result.getLote().getGranjeiroResponsavel());
						}
					}
					
					if(!tecnicos.contains(result.getLote().getTecnico())){
						tecnicos.add(result.getLote().getTecnico());
					}
					
				}
				produtoresField.setItems(produtores);
				tecnicoField.setItems(tecnicos);
				granjeiroField.setItems(granjeiros);
				
				loadLote(result.getLote());
			}

			
		});



	}
	private void loadLote(Lote lote) {
		if(produtoresField.getSize()==1 && lote!=null){
			changeProdutor(lote.getModulo());
		}
		if(lote==null){
			if(produtoresField.getSize()==1){
				changeProdutor((Modulo)null);
			}
			return;
		}
		produtoresField.setSelectedValue(lote.getProdutor());
		changeProdutor(lote.getModulo());
		tecnicoField.setSelectedValue(lote.getTecnico());
		granjeiroField.setSelectedValue(lote.getGranjeiroResponsavel());
		dataAlojamentoField.setValue(lote.getDataMediaAlojamento());
		dataSaidaField.setValue(lote.getDataMediaSaida());
		if(lote.getOrigens()!=null && lote.getOrigens().size()>0){
			for(OrigemAlojamento origem:lote.getOrigens()){
				addOrigem(origem);
			}
		}else{
			quantidadeAlojadaField.setValue(lote.getQuantidadeAlojada());
			pesoMedioAlojadoField.setValue(lote.getPesoMedioVivoAlojado());
			idadeMediaAlojadaField.setValue(lote.getIdadeMediaAlojada());
			sexoField.setSelectedValue(lote.getSexo());
			tipoOrigemField.setSelectedValue(lote.getTipoOrigem());
			quantidadeOrigensField.setValue(lote.getQuantidadeOrigemDistinta());
		}
	}
	
	
	@UiHandler("addOrigem")
	public void addOrigemClick(ClickEvent evt){
		if(origensAlojamento==null || origensAlojamento.size()==0){
			origensAlojamento=new LinkedList<>();
			addOrigem.setText("Nova Origem");
			addOrigem.setMarginTop(5);
		}
		addOrigem(null);
	}
	
	
	public void bindEvents(){
		salvarBtn.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				salvar(true);
			}
		}, ClickEvent.getType());
	}
	
	@UiHandler("addAbate")
	public void addAbateClick(ClickEvent evt){
		salvar(true);
	}


	private void salvar(final boolean informarDadosAbate) {
		lote.setProdutor((Pessoa) produtoresField.getSelectedObject());
		lote.setModulo((Modulo) moduloField.getSelectedObject());
		lote.setTecnico((Pessoa) tecnicoField.getSelectedObject());
		lote.setGranjeiroResponsavel((Pessoa) granjeiroField.getSelectedObject());
		lote.setDataMediaAlojamento(dataAlojamentoField.getValue());
		lote.setDataMediaSaida(dataSaidaField.getValue());
		lote.setQuantidadeAlojada(quantidadeAlojadaField.getValue());
		lote.setPesoMedioVivoAlojado(pesoMedioAlojadoField.getValue());
		lote.setIdadeMediaAlojada(idadeMediaAlojadaField.getValue());
		lote.setSexo((SexoLote) sexoField.getSelectedObject());
		lote.setTipoOrigem((TipoOrigem) tipoOrigemField.getSelectedObject());
		lote.setQuantidadeOrigemDistinta(quantidadeOrigensField.getValue());
		if(origensAlojamento!=null && origensAlojamento.size()>0){
			lote.setOrigens(getOrigensAlojamento());
		}
		loteCliente.salvar(lote, new RestCallback<Lote>(){

			@Override
			public void success(Lote result) {
				if(informarDadosAbate){
					factory.getAppController().goTo(ViewEnum.LOTES);
				}else{
					factory.getAppController().goTo(ViewEnum.LOTES);
				}
			}
			@Override
			public void onValidation(ErrosValidacao erro) {
				erros.setVisible(true);
				for(String er: erro.getTodosErros()){
					ListGroupItem it =new ListGroupItem();
					it.setText(er);
					errosDescricao.add(it);
				}
//				formEntrada.adde
//				quantidadeAlojadaField.addValidator();
			}
			
		});
	}

	private List<OrigemAlojamento> getOrigensAlojamento() {
		if(origensAlojamento==null){
			return null;
		}
		int posicao =0;
		for(OrigemAlojamento origem:origensAlojamento){
			origem.setSexo((SexoLote) sexoAlojamentoOrigensFields.get(posicao).getSelectedObject());
			origem.setDataAlojamento(datasAlojamentoOrigensFields.get(posicao).getValue());
			origem.setQuantidade(quantidadeAlojadaOrigensFields.get(posicao).getValue());
			origem.setMortosTransporte(mortosTransporteOrigensFields.get(posicao).getValue());
			origem.setProdutor((Origem) integradoOrigemOrigensFields.get(posicao).getSelectedObject());
			origem.setPesoAlojado(pesoTotalOrigensFields.get(posicao).getValue());
			origem.setIdadeAlojamento(idadeAlojamentoOrigensFields.get(posicao).getValue());
		}
		return origensAlojamento;
	}

	@UiHandler("moduloField")
	public void addModuloChangeEvent(ChangeEvent evt){
		if(moduloField.getSelectedObject()!=null){
			TipoAnimal tp = ((Modulo)moduloField.getSelectedObject()).getTipoAnimal();
			for(ListBox listIntegrado:integradoOrigemOrigensFields){
				loadOrigens(tp,listIntegrado);
			}
		}

	}
	
	private void addOrigem(OrigemAlojamento alojamentoOrigem) {
		blockQuoteOrigens.setVisible(false);
		if(origensAlojamento==null || origensAlojamento.size()==0){
			origensAlojamento=new LinkedList<>();
		}
		if(alojamentoOrigem==null){
			origensAlojamento.add(new OrigemAlojamento());
		}else{
			origensAlojamento.add(alojamentoOrigem);
		}
		final int posicao = origensAlojamento.size()-1;
		String color = origensAlojamento.size()%2==0?"#cccccc":"#eeeeee";
		final Row row = new Row();
		row.getElement().getStyle().setBackgroundColor(color);
		row.getElement().getStyle().setPaddingTop(15, Unit.PX);
		
		Column colunaDt = new Column("XS_4,MD_2");
		colunaDt.addStyleName("small-padding");
		final DatePicker dp = new DatePicker();
		dp.setPlaceholder("Data do alojamento");
		dp.setFormat("dd-mm-yyyy");
		dp.setLanguage(DatePickerLanguage.PT_BR);
		datasAlojamentoOrigensFields.add(dp);
		
		FormGroup fg = new FormGroup();
		fg.add(dp);
		
		colunaDt.add(fg);
		row.add(colunaDt);
		
		Column colunaQtd = new Column("XS_4,MD_1");
		colunaQtd.addStyleName("small-padding");

		final IntegerBox tbQtd = new IntegerBox();
		ClientUtil.prepareIntegerBox(tbQtd);
		tbQtd.setPlaceholder("Quantidade Alojada");
		tbQtd.addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				updateDadosAlojamento();	
			}
		});
		fg = new FormGroup();
		fg.add(tbQtd);
		colunaQtd.add(fg);
		row.add(colunaQtd);
		quantidadeAlojadaOrigensFields.add(tbQtd);

		Column colunaIdade = new Column("XS_2,MD_1");
		colunaIdade.addStyleName("small-padding");
		final IntegerBox tbIdade = new IntegerBox();
		tbIdade.addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				updateDadosAlojamento();	
			}
		});

		ClientUtil.prepareIntegerBox(tbIdade);
		tbIdade.setPlaceholder("Idade Média");
		fg = new FormGroup();
		fg.add(tbIdade);
		colunaIdade.add(fg);
		row.add(colunaIdade);
		idadeAlojamentoOrigensFields.add(tbIdade);
		
		Column colunaMort = new Column("XS_2,MD_1");
		colunaMort.addStyleName("small-padding");

		final IntegerBox tbMort = new IntegerBox();
		tbMort.addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				updateDadosAlojamento();	
			}
		});
		tbMort.setPlaceholder("Mortos no transporte");
		ClientUtil.prepareIntegerBox(tbMort);

		fg = new FormGroup();
		fg.add(tbMort);
		colunaMort.add(fg);
		row.add(colunaMort);
		mortosTransporteOrigensFields.add(tbMort);

		Column colunaIntegrado = new Column("XS_4,MD_3");
		colunaIntegrado.addStyleName("small-padding");
		final ListBox listIntegrado = new ListBox();
		if(moduloField.getSelectedObject()!=null){
			TipoAnimal tp = ((Modulo)moduloField.getSelectedObject()).getTipoAnimal();
			loadOrigens(tp,listIntegrado);
		}

		fg = new FormGroup();
		fg.add(listIntegrado);
		colunaIntegrado.add(fg);
		row.add(colunaIntegrado);
		integradoOrigemOrigensFields.add(listIntegrado);
		
		Column colunaPeso = new Column("XS_4,MD_2");
		colunaPeso.addStyleName("small-padding");
		final BigDecimalBox tbPeso = new BigDecimalBox();
		tbPeso.setPlaceholder("Peso Total");
		tbPeso.addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				updateDadosAlojamento();	
			}
		});

		fg = new FormGroup();
		fg.add(tbPeso);
		colunaPeso.add(fg);
		row.add(colunaPeso);
		pesoTotalOrigensFields.add(tbPeso);

		
		Column colunaSexo = new Column("XS_4,MD_2");
		colunaSexo.addStyleName("small-padding");
		InputGroup inputG = new InputGroup();
		final ListBox listSexo = new ListBox();
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
		btn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				row.removeFromParent();
				row.clear();
				sexoAlojamentoOrigensFields.remove(posicao);
				datasAlojamentoOrigensFields.remove(posicao);
				quantidadeAlojadaOrigensFields.remove(posicao);
				mortosTransporteOrigensFields.remove(posicao);
				integradoOrigemOrigensFields.remove(posicao);
				pesoTotalOrigensFields.remove(posicao);
				idadeAlojamentoOrigensFields.remove(posicao);

				origensAlojamento.remove(posicao);
				updateDadosAlojamento();
			}
		});
		
		
		blocoDetalhamentoOrigem.add(row);
		if(alojamentoOrigem!=null){
			dp.setValue(alojamentoOrigem.getDataAlojamento());
			tbQtd.setValue(alojamentoOrigem.getQuantidade());
			tbIdade.setValue(alojamentoOrigem.getIdadeAlojamento());
			tbMort.setValue(alojamentoOrigem.getMortosTransporte());
			listIntegrado.setSelectedValue(alojamentoOrigem.getProdutor());
			tbPeso.setValue(alojamentoOrigem.getPesoAlojado());
			listSexo.setSelectedValue(alojamentoOrigem.getSexo());
		}
		updateDadosAlojamento();
	}

	public void changeProdutor(final Modulo modulo){
		Integer produtorId = ClientUtil.parseInteger(produtoresField.getSelectedValue());
		if(produtorId!=null){
			modulosClient.porProdutor(produtorId, new RestCallback<List<Modulo>>() {
				@Override
				public void success(List<Modulo> modulos) {
					moduloField.setItems(modulos);
					if(modulo!=null){
						moduloField.setSelectedValue(modulo);
					}
					Set<TipoAnimal> tiposAnimais=new HashSet<>();
					for(Modulo m:modulos){
						tiposAnimais.add(m.getTipoAnimal());
					}
					if(modulo!=null){
						loadOrigens(modulo.getTipoAnimal(), null);
					}else if(tiposAnimais.size()==1){
						loadOrigens(tiposAnimais.iterator().next(),null);
					}
				}

				
			});
		}
	}

	@UiHandler("produtoresField")
	public void changeProdutor(ChangeEvent evt){
		changeProdutor((Modulo)null);
	}
//	
	private void loadOrigens(TipoAnimal tipo,final ListBox list) {
		if(tipo==null){
			return;
		}
		if(origens!=null && origens.size()>0){
			if(origens.get(0).getAnimal().equals(tipo)){
				//ja buscou e ta no cache
				if(list!=null){
					list.setItems(origens,true);
				}
				return;
			}
		}
		origemClient.porTipo(tipo, new RestCallback<List<Origem>>() {
			@Override
			public void success(List<Origem> result) 
			{
				origens = result;
				if(list!=null){
					list.setItems(result,true);
				}
			}
		});
	}
	
	public void updateDadosAlojamento(){
		if(origensAlojamento!=null && origensAlojamento.size()>0){
			//Desabilita dados de alojamento ao mesmo tempo que sumariza os mesmos
			Integer quantidadeTotal=0;
			Integer idadeConsolidada=0;
			Integer quantidadeComIdade=0;
			SexoLote sexoFinal = null;
			BigDecimal pesoTotal = BigDecimal.ZERO;
			Set<Origem> origensDistintas = new HashSet<>();
			for(int i=0;i<origensAlojamento.size();i++){
				IntegerBox quantidadeOrigemBox = quantidadeAlojadaOrigensFields.get(i);
				IntegerBox mortalidadeOrigemBox = mortosTransporteOrigensFields.get(i);
				IntegerBox idadeOrigemBox =idadeAlojamentoOrigensFields.get(i);
				ListBox origemBox = integradoOrigemOrigensFields.get(i);
				if(origemBox.getSelectedObject()!=null){
					origensDistintas.add((Origem) origemBox.getSelectedObject());
					tipoOrigemField.setSelectedValue(((Origem)origemBox.getSelectedObject()).getTipo());
				}
				BigDecimalBox pesoTotalBox = pesoTotalOrigensFields.get(i);
				ListBox sexoBox = sexoAlojamentoOrigensFields.get(i);
				if(quantidadeOrigemBox.getValue()!=null){
					quantidadeTotal+=quantidadeOrigemBox.getValue();
					if(idadeOrigemBox.getValue()!=null){
						idadeConsolidada += quantidadeTotal*idadeOrigemBox.getValue();
						quantidadeComIdade+=quantidadeTotal;
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
			if(idadeConsolidada>0){
				idadeMediaAlojadaField.setValue(idadeConsolidada/quantidadeComIdade);
			}
			if(pesoTotal.compareTo(BigDecimal.ZERO)>0){
				BigDecimal pesoMedio = pesoTotal.divide(new BigDecimal(quantidadeTotal),2,BigDecimal.ROUND_HALF_DOWN);
				pesoMedioAlojadoField.setValue(pesoMedio);
			}
			quantidadeOrigensField.setValue(origensDistintas.size());
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
	

}
