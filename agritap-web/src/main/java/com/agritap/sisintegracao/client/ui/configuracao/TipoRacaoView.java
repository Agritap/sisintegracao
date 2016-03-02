package com.agritap.sisintegracao.client.ui.configuracao;

import java.util.List;

import org.gwtbootstrap3.client.ui.CheckBox;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.ListGroup;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;
import org.gwtbootstrap3.extras.datepicker.client.ui.DatePicker;

import com.agritap.sisintegracao.client.ClientUtil;
import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.client.request.clients.TipoRacaoClient;
import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.model.TipoAnimal;
import com.agritap.sisintegracao.model.TipoRacao;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class TipoRacaoView extends Composite {

	private static TipoRacaoUiBinder uiBinder = GWT.create(TipoRacaoUiBinder.class);

	TipoRacaoClient client = GWT.create(TipoRacaoClient.class);

	ClientFactory factory;

	@UiField
	CellTable<TipoRacao> tabelaTipoRacao;
	@UiField
	HTMLPanel addTipoRacao;

	@UiField
	Row formRow;

	@UiField
	TextBox carenciaField;

	@UiField
	DatePicker dataFimField;

	@UiField
	ListGroup errorBox;
	@UiField
	DatePicker dataInicioField;

	@UiField
	CheckBox medicadaField;

	@UiField
	TextBox nomeField;

	@UiField
	ListBox tipoAnimalField;

	TipoRacao TipoRacaoEditado;

	interface TipoRacaoUiBinder extends UiBinder<Widget, TipoRacaoView> {
	}

	public TipoRacaoView(ClientFactory factory) {
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
		bindAddEvent();
	}

	private void bindAddEvent() {

		addTipoRacao.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				TipoRacaoEditado = new TipoRacao();
				loadForm(TipoRacaoEditado);
				formRow.setVisible(true);
			}
		}, ClickEvent.getType());
	}

	private void loadTabela() {
		client.todos(new RestCallback<List<TipoRacao>>() {

			@Override
			public void success(List<TipoRacao> response) {
				tabelaTipoRacao.setRowData(response);
				tabelaTipoRacao.redraw();
			}
		});
	}

	private void preparaTabela() {
		TextColumn<TipoRacao> carenciaColumn = new TextColumn<TipoRacao>() {
			@Override
			public String getValue(TipoRacao tipoRacao) {
				if (tipoRacao.getCarencia() != null) {
					return tipoRacao.getCarencia().toString() + "  dias";
				}
				return "0 dias";
			}
		};
		TextColumn<TipoRacao> dataFimColumn = new TextColumn<TipoRacao>() {
			@Override
			public String getValue(TipoRacao tipoRacao) {
				return ClientUtil.formatDate(tipoRacao.getDataFim());
			}
		};

		TextColumn<TipoRacao> dataInicioColumn = new TextColumn<TipoRacao>() {
			@Override
			public String getValue(TipoRacao tipoRacao) {
				return ClientUtil.formatDate(tipoRacao.getDataInicio());
			}

		};

		TextColumn<TipoRacao> medicadaColumn = new TextColumn<TipoRacao>() {
			@Override
			public String getValue(TipoRacao tipoRacao) {
				return ClientUtil.formatSimNao(tipoRacao.getMedicada());
			}

		};

		TextColumn<TipoRacao> nomeColumn = new TextColumn<TipoRacao>() {
			@Override
			public String getValue(TipoRacao tipoRacao) {
				return tipoRacao.getNome();

			}

		};

		TextColumn<TipoRacao> tipoAnimalColumn = new TextColumn<TipoRacao>() {
			@Override
			public String getValue(TipoRacao tipoRacao) {
				if (tipoRacao.getTipoAnimal() == null) {
					return null;
				}
				return tipoRacao.getTipoAnimal().name();

			}

		};

		final Column<TipoRacao, String> click = new Column<TipoRacao, String>(
				new ButtonCell(ButtonType.PRIMARY, IconType.EDIT)) {
			@Override
			public String getValue(TipoRacao object) {
				return "";
			}
		};

		click.setFieldUpdater(new FieldUpdater<TipoRacao, String>() {
			@Override
			public void update(int index, TipoRacao tipoRacao, String value) {
				loadForm(tipoRacao);
				formRow.setVisible(true);
			}

		});

		tabelaTipoRacao.addColumn(nomeColumn, "Nome");
		tabelaTipoRacao.addColumn(dataInicioColumn, "Data Inicio");
		tabelaTipoRacao.addColumn(dataFimColumn, "Data Fim");
		tabelaTipoRacao.addColumn(carenciaColumn, "Carência");
		tabelaTipoRacao.addColumn(medicadaColumn, "Medicada");
		tabelaTipoRacao.addColumn(tipoAnimalColumn, "Tipo de Animal");
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

	public CellTable<TipoRacao> getTabelaTipoRacao() {
		return tabelaTipoRacao;
	}

	public void setTabelaTipoRacao(CellTable<TipoRacao> tabelaTipoRacao) {
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

	public TextBox getCarenciaField() {
		return carenciaField;
	}

	public void setCarenciaField(TextBox carenciaField) {
		this.carenciaField = carenciaField;
	}

	public TextBox getNomeField() {
		return nomeField;
	}

	public void setNomeField(TextBox nomeField) {
		this.nomeField = nomeField;
	}

	public TipoRacao getTipoRacaoEditado() {
		return TipoRacaoEditado;
	}

	public void setTipoRacaoEditado(TipoRacao tipoRacaoEditado) {
		TipoRacaoEditado = tipoRacaoEditado;
	}

	@UiHandler("salvarBtn")
	public void salvarClick(ClickEvent evt) {
		TipoRacaoEditado.setCarencia(ClientUtil.parseInteger(carenciaField.getValue()));
		TipoRacaoEditado.setDataFim(dataFimField.getValue());
		TipoRacaoEditado.setDataInicio(dataInicioField.getValue());

		// TROCAR O TIPO DE DADOS PARA CHECKBOX
		TipoRacaoEditado.setMedicada(medicadaField.getValue());

		TipoRacaoEditado.setNome(nomeField.getValue());

		TipoRacaoEditado.setTipoAnimal(TipoAnimal.values()[tipoAnimalField.getSelectedIndex()]);
		// TipoRacaoEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
		client.update(TipoRacaoEditado, new RestCallback<TipoRacao>() {

			@Override
			public void success(TipoRacao to) {
				loadTabela();
				TipoRacaoEditado = null;
				formRow.setVisible(false);
			}
//			@Override
//			public void onValidation(ErrosI erro) {
//				errorBox.setVisible(true);
//				ClientUtil.printValidation(erro, errorBox);
//			}
		});
	}

	@UiHandler("cancelarBtn")
	public void cancelarClick(ClickEvent evt) {
		formRow.setVisible(false);
		TipoRacaoEditado = null;
	}

	@UiHandler("excluirBtn")
	public void excluirClick(ClickEvent evt) {

		// TipoRacaoEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
		client.delete(TipoRacaoEditado.getId(), new RestCallback<Void>() {

			@Override
			public void success(Void to) {
				loadTabela();
				TipoRacaoEditado = null;
				formRow.setVisible(false);
			}
		});
	}
	//

	public void loadForm(TipoRacao tipoRacao) {
		this.TipoRacaoEditado = tipoRacao;

		carenciaField.setValue(ClientUtil.formatInteger(tipoRacao.getCarencia()));
		dataFimField.setValue(tipoRacao.getDataFim());
		dataInicioField.setValue(tipoRacao.getDataInicio());
		medicadaField.setValue(tipoRacao.getMedicada());
		nomeField.setValue(tipoRacao.getNome());

		if (tipoRacao.getTipoAnimal() != null) {

			tipoAnimalField.setSelectedIndex(tipoRacao.getTipoAnimal().ordinal());

		}

	}

	public DatePicker getDataFimField() {
		return dataFimField;
	}

	public void setDataFimField(DatePicker dataFimField) {
		this.dataFimField = dataFimField;
	}

	public DatePicker getDataInicioField() {
		return dataInicioField;
	}

	public void setDataInicioField(DatePicker dataInicioField) {
		this.dataInicioField = dataInicioField;
	}

	public CheckBox getMedicadaField() {
		return medicadaField;
	}

	public void setMedicadaField(CheckBox medicadaField) {
		this.medicadaField = medicadaField;
	}

	public ListBox getTipoAnimalField() {
		return tipoAnimalField;
	}

	public void setTipoAnimalField(ListBox tipoAnimalField) {
		this.tipoAnimalField = tipoAnimalField;
	}

}
