package com.agritap.sisintegracao.client.ui.configuracao;

import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.agritap.sisintegracao.client.request.Callback;
import com.agritap.sisintegracao.client.request.beans.ModulosI;
import com.agritap.sisintegracao.client.request.beans.ModulosIAdapter;
import com.agritap.sisintegracao.client.request.clients.ModulosClient;
import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.model.TipoAnimal;
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

public class ModulosView extends Composite {

	private static ModulosUiBinder uiBinder = GWT.create(ModulosUiBinder.class);

	ModulosClient client = new ModulosClient();

	ClientFactory factory;

	@UiField
	CellTable<ModulosI> tabelaModulos;

	@UiField
	Row formRow;

	@UiField
	TextBox nomeField;

	@UiField
	ListBox tipoAnimalField;

	ModulosI ModulosEditado;

	interface ModulosUiBinder extends UiBinder<Widget, ModulosView> {
	}

	public ModulosView(ClientFactory factory) {
		initWidget(uiBinder.createAndBindUi(this));
		init();
		this.factory = factory;
	}

	private void init() {
		for (TipoAnimal t : TipoAnimal.values()) {
			tipoAnimalField.addItem(t.name());
		}
		preparaTabela();
		loadTabela();

	}

	private void loadTabela() {
		client.todos(new Callback<ModulosIAdapter>() {

			@Override
			public void ok(ModulosIAdapter response) {
				tabelaModulos.setRowData(response.getResultado());
				tabelaModulos.redraw();
			}
		
		});
	}

	private void preparaTabela() {
		

		TextColumn<ModulosI> nomeColumn = new TextColumn<ModulosI>() {
			@Override
			public String getValue(ModulosI modulos) {
				return modulos.getNome();

			}

		};

		TextColumn<ModulosI> tipoAnimalColumn = new TextColumn<ModulosI>() {
			@Override
			public String getValue(ModulosI modulos) {
				if (modulos.getTipoAnimal() == null) {
					return null;
				}
				return modulos.getTipoAnimal().name();

			}

		};
		
		TextColumn<ModulosI> barracoesColumn = new TextColumn<ModulosI>() {
			@Override
			public String getValue(ModulosI barracao){
				return barracao.getBarracao() ;
			}
		};
		

		final Column<ModulosI, String> click = new Column<ModulosI, String>(
				new ButtonCell(ButtonType.PRIMARY, IconType.EDIT)) {
			@Override
			public String getValue(ModulosI object) {
				return "";
			}
		};

		click.setFieldUpdater(new FieldUpdater<ModulosI, String>() {
			@Override
			public void update(int index, ModulosI modulos, String value) {
				loadForm(modulos);
				formRow.setVisible(true);
			}

		});

		tabelaModulos.addColumn(nomeColumn, "Nome");
		tabelaModulos.addColumn(barracoesColumn, "Barracão");
		tabelaModulos.addColumn(tipoAnimalColumn, "Tipo de Animal");
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

	public CellTable<ModulosI> getTabelaModulos() {
		return tabelaModulos;
	}

	public void setTabelaModulos(CellTable<ModulosI> tabelaModulos) {
		this.tabelaModulos = tabelaModulos;
	}

	public Row getFormRow() {
		return formRow;
	}

	public void setFormRow(Row formRow) {
		this.formRow = formRow;
	}

	public TextBox getNomeField() {
		return nomeField;
	}

	public void setNomeField(TextBox nomeField) {
		this.nomeField = nomeField;
	}

	public ModulosI getModulosEditado() {
		return ModulosEditado;
	}

	public void setModulosEditado(ModulosI modulosEditado) {
		ModulosEditado = modulosEditado;
	}

	@UiHandler("salvarBtn")
	public void salvarClick(ClickEvent evt) {

		ModulosEditado.setNome(nomeField.getValue());
		ModulosEditado.setTipoAnimal(TipoAnimal.values()[tipoAnimalField.getSelectedIndex()]);
		// ModulosEditado.setBarracao();
		// TipoRacaoEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
		client.update(ModulosEditado, new Callback<ModulosI>() {

			@Override
			public void ok(ModulosI to) {
				loadTabela();
				ModulosEditado = null;
				formRow.setVisible(false);
			}

		});
	}

	@UiHandler("cancelarBtn")
	public void cancelarClick(ClickEvent evt) {
		formRow.setVisible(false);
		ModulosEditado = null;
	}

	@UiHandler("excluirBtn")
	public void excluirClick(ClickEvent evt) {

		// TipoRacaoEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
		client.delete(ModulosEditado.getId(), new Callback<Void>() {

			@Override
			public void ok(Void to) {
				loadTabela();
				ModulosEditado = null;
				formRow.setVisible(false);
			}
		});
	}
	//

	public void loadForm(ModulosI modulos) {
		this.ModulosEditado = modulos;

		nomeField.setValue(modulos.getNome());

		if (modulos.getTipoAnimal() != null) {

			tipoAnimalField.setSelectedIndex(modulos.getTipoAnimal().ordinal());

		}

	}

	public ListBox getTipoAnimalField() {
		return tipoAnimalField;
	}

	public void setTipoAnimalField(ListBox tipoAnimalField) {
		this.tipoAnimalField = tipoAnimalField;
	}

}
