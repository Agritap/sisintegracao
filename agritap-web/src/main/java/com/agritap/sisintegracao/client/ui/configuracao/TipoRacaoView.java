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
import com.agritap.sisintegracao.client.request.beans.TipoRacaoI;
import com.agritap.sisintegracao.client.request.beans.TipoRacaoIAdapter;

import com.agritap.sisintegracao.client.request.clients.TipoRacaoClient;

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

public class TipoRacaoView extends Composite {

	private static TipoRacaoUiBinder uiBinder = GWT.create(TipoRacaoUiBinder.class);

	TipoRacaoClient client = new TipoRacaoClient();
	
	ClientFactory factory;
	@UiField
	CellTable<TipoRacaoI> tabelaTipoRacao;
	@UiField
	HTMLPanel addTipoRacao;

	@UiField
	Row formRow;

	@UiField
	ListBox integradoraField;
	
	@UiField
	TextBox carenciaField;
	
	@UiField
	TextBox dataFimField;
	
	@UiField
	TextBox dataInicioField;
	
	@UiField
	TextBox medicadaField;
	
	@UiField
	TextBox nomeField;
	
	@UiField
	TextBox tipoAnimalField;
	

	@UiField
	TextBox codigoIntegradoraField;
	
	@UiField
	InlineCheckBox ativoField;

	@UiField
	TextBox telefoneField;
	
	TipoRacaoI TipoRacaoEditado;
	
	interface TipoRacaoUiBinder extends UiBinder<Widget, TipoRacaoView> {
	}

	public TipoRacaoView(ClientFactory factory) {
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
		
		addTipoRacao.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				TipoRacaoEditado = factory.getEntityfactory().newTipoRacao1().as();
				loadForm(TipoRacaoEditado);
				 formRow.setVisible(true);
			}
		}, ClickEvent.getType());
	}

	private void loadTabela() {
		client.todos(new Callback<TipoRacaoIAdapter>() {
			
			@Override
			public void ok(TipoRacaoIAdapter response) {
				tabelaTipoRacao.setRowData(response.getResultado());
				tabelaTipoRacao.redraw();
			}
		});
	}

	private void preparaTabela() {
		TextColumn<TipoRacaoI> carenciaColumn = new TextColumn<TipoRacaoI>() {
			@Override
			public String getValue(TipoRacaoI tipoRacao) {
				return tipoRacao.getCarencia();
			}
		};
		TextColumn<TipoRacaoI> dataFimColumn = new TextColumn<TipoRacaoI>() {
			@Override
			public String getValue(TipoRacaoI tipoRacao) {
				return tipoRacao.getDataFim();
			}
		};
		
		TextColumn<TipoRacaoI> dataInicioColumn = new TextColumn<TipoRacaoI> (){
			@Override
			public String getValue(TipoRacaoI tipoRacao){
				return tipoRacao.getDataInicio();
				
						
			}
			
		};
		
		
		TextColumn<TipoRacaoI> medicadaColumn = new TextColumn<TipoRacaoI> (){
			@Override
			public String getValue(TipoRacaoI tipoRacao){
				return tipoRacao.getMedicada();
				
						
			}
			
		};
		
		TextColumn<TipoRacaoI> nomeColumn = new TextColumn<TipoRacaoI> (){
			@Override
			public String getValue(TipoRacaoI tipoRacao){
				return tipoRacao.getNome();
				
						
			}
			
		};
		
		TextColumn<TipoRacaoI> tipoAnimalColumn = new TextColumn<TipoRacaoI> (){
			@Override
			public String getValue(TipoRacaoI tipoRacao){
				return tipoRacao.getPorco();
				
						
			}
			
		};
		
		TextColumn<TipoRacaoI> ativo2Column = new TextColumn<TipoRacaoI>() {
			@Override
			public String getValue(TipoRacaoI tipoRacao) {
				if(tipoRacao.getAtivo() == null || tipoRacao.getAtivo()){
					return "true";
				}else{
					return "false";
				}
			}
			@Override
			public void render(Context context, TipoRacaoI tipoRacao, SafeHtmlBuilder sb) {
				if(tipoRacao.getAtivo() == null || tipoRacao.getAtivo()){
					sb.appendHtmlConstant("<i class=\"fa fa-check-square-o\"></i>");
				}else{
					sb.appendHtmlConstant("<i class=\"fa fa-square-o\"></i>");
				}
			}
		};
		 final Column<TipoRacaoI, String> click = new Column<TipoRacaoI, String>(new ButtonCell(ButtonType.PRIMARY, IconType.EDIT)) {
	            @Override
	            public String getValue(TipoRacaoI object) {
	                return "";
	            }
	        };
	        
	        click.setFieldUpdater(new FieldUpdater<TipoRacaoI, String>() {
            @Override
            public void update(int index, TipoRacaoI tipoRacao, String value) {
            	loadForm(tipoRacao);
                formRow.setVisible(true);
            }

        });
		ativo2Column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		tabelaTipoRacao.addColumn(carenciaColumn, "Carencia");
		tabelaTipoRacao.addColumn(dataFimColumn, "Data Fim");
		tabelaTipoRacao.addColumn(dataInicioColumn, "Data Inicio");	
		tabelaTipoRacao.addColumn(medicadaColumn, "Medicada");	
		tabelaTipoRacao.addColumn(nomeColumn, "Nome");
		tabelaTipoRacao.addColumn(tipoAnimalColumn, "Tipp Animal");
		tabelaTipoRacao.addColumn(ativo2Column, "Ativo");
		tabelaTipoRacao.addColumn(click, "Ações");
	}
	
	public static TipoRacaoUiBinder getUiBinder() {
		return uiBinder;
	}

	public static void setUiBinder(TipoRacaoUiBinder uiBinder) {
		TipoRacaoView.uiBinder = uiBinder;
	}

	public TipoRacaoClient getClient() {
		return client;
	}

	public void setClient(TipoRacaoClient client) {
		this.client = client;
	}

	public ClientFactory getFactory() {
		return factory;
	}

	public void setFactory(ClientFactory factory) {
		this.factory = factory;
	}

	public CellTable<TipoRacaoI> getTabelaTipoRacao() {
		return tabelaTipoRacao;
	}

	public void setTabelaTipoRacao(CellTable<TipoRacaoI> tabelaTipoRacao) {
		this.tabelaTipoRacao = tabelaTipoRacao;
	}

	public HTMLPanel getAddTipoRacao() {
		return addTipoRacao;
	}

	public void setAddTipoRacao(HTMLPanel addTipoRacao) {
		this.addTipoRacao = addTipoRacao;
	}

	public Row getFormRow() {
		return formRow;
	}

	public void setFormRow(Row formRow) {
		this.formRow = formRow;
	}

	public ListBox getIntegradoraField() {
		return integradoraField;
	}

	public void setIntegradoraField(ListBox integradoraField) {
		this.integradoraField = integradoraField;
	}

	public TextBox getCarenciaField() {
		return carenciaField;
	}

	public void setCarenciaField(TextBox carenciaField) {
		this.carenciaField = carenciaField;
	}

	public TextBox getDataFimField() {
		return dataFimField;
	}

	public void setDataFimField(TextBox dataFimField) {
		this.dataFimField = dataFimField;
	}

	public TextBox getDataInicioField() {
		return dataInicioField;
	}

	public void setDataInicioField(TextBox dataInicioField) {
		this.dataInicioField = dataInicioField;
	}

	public TextBox getMedicadaField() {
		return medicadaField;
	}

	public void setMedicadaField(TextBox medicadaField) {
		this.medicadaField = medicadaField;
	}

	public TextBox getNomeField() {
		return nomeField;
	}

	public void setNomeField(TextBox nomeField) {
		this.nomeField = nomeField;
	}

	public TextBox getTipoAnimalField() {
		return tipoAnimalField;
	}

	public void setTipoAnimalField(TextBox tipoAnimalField) {
		this.tipoAnimalField = tipoAnimalField;
	}

	public TextBox getCodigoIntegradoraField() {
		return codigoIntegradoraField;
	}

	public void setCodigoIntegradoraField(TextBox codigoIntegradoraField) {
		this.codigoIntegradoraField = codigoIntegradoraField;
	}

	public InlineCheckBox getAtivoField() {
		return ativoField;
	}

	public void setAtivoField(InlineCheckBox ativoField) {
		this.ativoField = ativoField;
	}

	public TextBox getTelefoneField() {
		return telefoneField;
	}

	public void setTelefoneField(TextBox telefoneField) {
		this.telefoneField = telefoneField;
	}

	public TipoRacaoI getTipoRacaoEditado() {
		return TipoRacaoEditado;
	}

	public void setTipoRacaoEditado(TipoRacaoI tipoRacaoEditado) {
		TipoRacaoEditado = tipoRacaoEditado;
	}

	@UiHandler("salvarBtn")
	public void salvarClick(ClickEvent evt){
		TipoRacaoEditado.setCarencia(carenciaField.getValue());
		TipoRacaoEditado.setDataFim(dataFimField.getValue());
		TipoRacaoEditado.setDataInicio(dataInicioField.getValue());
		TipoRacaoEditado.setMedicada(medicadaField.getValue());
		TipoRacaoEditado.setNome(nomeField.getValue());
		TipoRacaoEditado.setTipoAnimal(tipoAnimalField.getValue());
		TipoRacaoEditado.setAtivo(ativoField.getValue());
		TipoRacaoEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
		client.update(TipoRacaoEditado, new Callback<TipoRacaoI>() {

			@Override
			public void ok(TipoRacaoI to) {
				loadTabela();
				TipoRacaoEditado=null;
				formRow.setVisible(false);
			}
		});
	}
	

	@UiHandler("cancelarBtn")
	public void cancelarClick(ClickEvent evt){
		formRow.setVisible(false);
		TipoRacaoEditado=null;
	}

	@UiHandler("excluirBtn")
	public void excluirClick(ClickEvent evt){
		TipoRacaoEditado.setCarencia(carenciaField.getValue());
		TipoRacaoEditado.setDataFim(dataFimField.getValue());
		TipoRacaoEditado.setDataInicio(dataInicioField.getValue());
		TipoRacaoEditado.setMedicada(medicadaField.getValue());
		TipoRacaoEditado.setNome(nomeField.getValue());
		TipoRacaoEditado.setTipoAnimal(tipoAnimalField.getValue());
		TipoRacaoEditado.setAtivo(ativoField.getValue());
		TipoRacaoEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
		client.delete(TipoRacaoEditado.getId(), new Callback<Void>() {

			@Override
			public void ok(Void to) {
				loadTabela();
				TipoRacaoEditado=null;
				formRow.setVisible(false);
			}
		});
	}
//	

	public void loadForm(TipoRacaoI tipoRacao) {
		this.TipoRacaoEditado=tipoRacao;
		carenciaField.setValue(tipoRacao.getCarencia());
		dataFimField.setValue(tipoRacao.getDataFim());
		dataInicioField.setValue(tipoRacao.getDataInicio());
		medicadaField.setValue(tipoRacao.getMedicada());
		nomeField.setValue(tipoRacao.getNome());
		tipoAnimalField.setValue(tipoRacao.getTipoAnimal());
		codigoIntegradoraField.setValue(tipoRacao.getCodigoIntegradora());
		ativoField.setValue(tipoRacao.getAtivo());;
		
		
	}

}
