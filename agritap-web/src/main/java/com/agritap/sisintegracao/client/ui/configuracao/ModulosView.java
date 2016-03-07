package com.agritap.sisintegracao.client.ui.configuracao;

import java.util.List;

import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.agritap.sisintegracao.client.ViewEnum;
import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.client.request.clients.ModulosClient;
import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.model.Modulo;
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

public class ModulosView extends Composite {

	private static ModulosUiBinder uiBinder = GWT.create(ModulosUiBinder.class);

	ModulosClient client = GWT.create(ModulosClient.class);

	ClientFactory factory;

	@UiField
	CellTable<Modulo> tabelaModulos;
	@UiField
	HTMLPanel addModulo;

	
//	@UiField
//	Row formRow;
//
//	@UiField
//	TextBox nomeField;
//
//	@UiField
//	ListBox tipoAnimalField;
//
//	@UiField
//	TextBox alojamentoMaximoField;

	

	interface ModulosUiBinder extends UiBinder<Widget, ModulosView> {
	}

	public ModulosView(ClientFactory factory) {
		initWidget(uiBinder.createAndBindUi(this));
		init();
		this.factory = factory;
	}

	private void init() {
//		for (TipoAnimal t : TipoAnimal.values()) {
//			tipoAnimalField.addItem(t.name());
//		}
		preparaTabela();
		loadTabela();
		bindEvents();

	}

	private void bindEvents() {
		addModulo.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				factory.getAppController().goTo(ViewEnum.MODULO); 	
			}
		}, ClickEvent.getType());
		
	}

	private void loadTabela() {
		client.todos(new RestCallback<List<Modulo>>() {

			@Override
			public void success(List<Modulo> response) {
				tabelaModulos.setRowData(response);
				tabelaModulos.redraw();
			}

		});
	}

	private void preparaTabela() {

		TextColumn<Modulo> nomeColumn = new TextColumn<Modulo>() {
			@Override
			public String getValue(Modulo modulos) {
				return modulos.getNome();

			}

		};

		TextColumn<Modulo> tipoAnimalColumn = new TextColumn<Modulo>() {
			@Override
			public String getValue(Modulo modulos) {
				if (modulos.getTipoAnimal() == null) {
					return null;
				}
				return modulos.getTipoAnimal().name();

			}

		};

		TextColumn<Modulo> barracoesColumn = new TextColumn<Modulo>() {
			@Override
			public String getValue(Modulo barracao) {
				return "";
//				return barracao.getBarracoes();
			}
		};

		TextColumn<Modulo> produtorColumn = new TextColumn<Modulo>() {
			@Override
			public String getValue(Modulo produtor) {
				return produtor.getProdutor().getRotulo();

			}
		};

		TextColumn<Modulo> alojamentoMaximo = new TextColumn<Modulo>() {
			@Override
			public String getValue(Modulo modulo) {
				if(modulo.getAlojamentoMaximo()==null){
					return null;
				}else{
					return modulo.getAlojamentoMaximo().toString();
				}
//				return modulo.getAlojamentoMaximo()!=null?modulo.getAlojamentoMaximo().toString():null;

			}
		};

		final Column<Modulo, String> click = new Column<Modulo, String>(
				new ButtonCell(ButtonType.PRIMARY, IconType.EDIT)) {
			@Override
			public String getValue(Modulo object) {
				return "";
			}
		};

		click.setFieldUpdater(new FieldUpdater<Modulo, String>() {
			@Override
			public void update(int index, Modulo modulos, String value) {
//				loadForm(modulos);
//				formRow.setVisible(true);
			}

		});

		tabelaModulos.addColumn(produtorColumn, "Modulos");
		tabelaModulos.addColumn(alojamentoMaximo, "Alojamento Maximo");
		tabelaModulos.addColumn(click, "Ações");
	}

	public static ModulosUiBinder getUiBinder() {
		return uiBinder;
	}

	public static void setUiBinder(ModulosUiBinder uiBinder) {
		ModulosView.uiBinder = uiBinder;
	}

	public ModulosClient getClient() {
		return client;
	}

	public void setClient(ModulosClient client) {
		this.client = client;
	}

	public ClientFactory getFactory() {
		return factory;
	}

	public void setFactory(ClientFactory factory) {
		this.factory = factory;
	}

	public CellTable<Modulo> getTabelaModulos() {
		return tabelaModulos;
	}

	public void setTabelaModulos(CellTable<Modulo> tabelaModulos) {
		this.tabelaModulos = tabelaModulos;
	}

	



//	@UiHandler("salvarBtn")
//	public void salvarClick(ClickEvent evt) {
//
//		ModulosEditado.setNome(nomeField.getValue());
//		ModulosEditado.setTipoAnimal(TipoAnimal.values()[tipoAnimalField.getSelectedIndex()]);
//		ModulosEditado.setAlojamentoMaximo(ClientUtil.parseInteger(alojamentoMaximoField.getValue()));
//
//		// ModulosEditado.setBarracao();
//		// TipoRacaoEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
//		client.update(ModulosEditado, new Callback<ModulosI>() {
//
//			@Override
//			public void ok(ModulosI to) {
//				loadTabela();
//				ModulosEditado = null;
//				formRow.setVisible(false);
//			}
//
//		});
//	}

	
}