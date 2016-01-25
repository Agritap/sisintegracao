package com.agritap.sisintegracao.client.ui.configuracao;

import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.InlineCheckBox;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.ListGroup;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.form.validator.BlankValidator;
import org.gwtbootstrap3.client.ui.form.validator.FieldMatchValidator;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.agritap.sisintegracao.client.ClientUtil;
import com.agritap.sisintegracao.client.request.Callback;
import com.agritap.sisintegracao.client.request.beans.ErrosI;
import com.agritap.sisintegracao.client.request.beans.PessoaI;
import com.agritap.sisintegracao.client.request.beans.PessoaIAdapter;
import com.agritap.sisintegracao.client.request.clients.PessoaClient;
import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.model.Integradora;
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

public class PessoasView extends Composite {

	private static PessoasUiBinder uiBinder = GWT.create(PessoasUiBinder.class);
	Logger log = Logger.getLogger(PessoasView.class.getName());

	PessoaClient client = new PessoaClient();
	
	ClientFactory factory;
	
	@UiField
	CellTable<PessoaI> tabelaProdutores;
	
	@UiField
	HTMLPanel addProdutor;

	@UiField
	Row formRow;
	
	@UiField
	Form form;

	@UiField
	ListBox integradoraField;
	
	@UiField
	TextBox nomeField;
	
	@UiField
	ListGroup errorBox;

	@UiField
	TextBox emailField;

	@UiField
	TextBox codigoIntegradoraField;
	
	@UiField
	InlineCheckBox ativoField;

	@UiField
	TextBox telefoneField;
	@UiField
	TextBox apelidoField;
	@UiField
	TextBox cpfField;
	@UiField
	InlineCheckBox produtorField;
	@UiField
	InlineCheckBox tecnicoField;
	@UiField	
	InlineCheckBox granjeiroField;
	@UiField	
	Anchor definirSenhaAnchor;
	@UiField	
	Input senhaField;
	@UiField	
	Input confirmaSenhaField;
	
	@UiField	
	Row blocoSenhas;
	
	PessoaI produtorEditado;
	
	interface PessoasUiBinder extends UiBinder<Widget, PessoasView> {
	}

	public PessoasView(ClientFactory factory) {
		initWidget(uiBinder.createAndBindUi(this));
		init();
		this.factory=factory;
	}

	private void init() {
		preparaTabela();
		loadTabela();
		preparaFormulario();
		bindAddEvent();
		ClientUtil.populaListBox(integradoraField,Integradora.values());
	}

	private void preparaFormulario() {
		FieldMatchValidator<String> fm = new FieldMatchValidator<>(senhaField);
		confirmaSenhaField.addValidator(fm);
		confirmaSenhaField.setValidateOnBlur(true);
		nomeField.addValidator(new BlankValidator<String>());
		nomeField.setValidateOnBlur(true);
//		emailField.setValidators(new RegExValidator(""));
//		cpfField.setValidators(new RegExValidator(""));

	}

	private void bindAddEvent() {
		
		addProdutor.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				produtorEditado = factory.getEntityfactory().newProdutor().as();
				loadForm(produtorEditado);
				 formRow.setVisible(true);
			}
		}, ClickEvent.getType());
	}

	private void loadTabela() {
		client.todos(new Callback<PessoaIAdapter>() {
			
			@Override
			public void ok(PessoaIAdapter response) {
				tabelaProdutores.setRowData(response.getResultado());
				tabelaProdutores.redraw();
			}
		});
	}

	private void preparaTabela() {
		TextColumn<PessoaI> nomeColumn = new TextColumn<PessoaI>() {
			@Override
			public String getValue(PessoaI produtor) {
				if(ClientUtil.isEmpty(produtor.getApelido())){
					return produtor.getNome();
				}else{
					return produtor.getNome()+" ("+produtor.getApelido()+")";
				}
			}
		};
		TextColumn<PessoaI> emailColumn = new TextColumn<PessoaI>() {
			@Override
			public String getValue(PessoaI produtor) {
				return produtor.getEmail();
			}
		};
		
		TextColumn<PessoaI> telefoneColumn = new TextColumn<PessoaI> (){
			@Override
			public String getValue(PessoaI produtor){
				return produtor.getTelefone();
			}
			
		};
		
		TextColumn<PessoaI> ativo2Column = new TextColumn<PessoaI>() {
			@Override
			public String getValue(PessoaI produtor) {
				if(produtor.getAtivo() == null || produtor.getAtivo()){
					return "true";
				}else{
					return "false";
				}
			}
			@Override
			public void render(Context context, PessoaI produtor, SafeHtmlBuilder sb) {
				if(produtor.getAtivo() == null || produtor.getAtivo()){
					sb.appendHtmlConstant("<i title=\"Ativo\" class=\"fa fa-check-square-o\"></i>");
				}else{
					sb.appendHtmlConstant("<i title=\"Inativo\" class=\"fa fa-square-o\"></i>");
				}
				if(produtor.getProdutor()!=null && produtor.getProdutor() )	{
					sb.appendHtmlConstant(" <i title=\"Produtor\" class=\"fa fa-star\"></i>");
				}
				if(produtor.getTecnico()!=null && produtor.getTecnico() )	{
					sb.appendHtmlConstant(" <i title=\"Técnico\" class=\"fa fa-suitcase\"></i>");
					
				}
				if(produtor.getGranjeiro()!=null && produtor.getGranjeiro() )	{
					sb.appendHtmlConstant(" <i title=\"Granjeiro\" class=\"fa fa-spinner\"></i>");
				}
			}
		};
		 final Column<PessoaI, String> click = new Column<PessoaI, String>(new ButtonCell(ButtonType.PRIMARY, IconType.EDIT)) {
	            @Override
	            public String getValue(PessoaI object) {
	                return "";
	            }
	        };
	        
	        click.setFieldUpdater(new FieldUpdater<PessoaI, String>() {
            @Override
            public void update(int index, PessoaI produtor, String value) {
            	loadForm(produtor);
                formRow.setVisible(true);
            }

        });
		ativo2Column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		tabelaProdutores.addColumn(nomeColumn, "Nome");
		tabelaProdutores.addColumn(emailColumn, "Email");
		tabelaProdutores.addColumn(telefoneColumn, "Telefone");	
		tabelaProdutores.addColumn(ativo2Column, "Ativo");
		tabelaProdutores.addColumn(click, "Ações");
	}
	
	@UiHandler("salvarBtn")
	public void salvarClick(ClickEvent evt){
		errorBox.setVisible(false);
		errorBox.clear();
		if(form.validate()){
			
			produtorEditado.setNome(nomeField.getValue());
			produtorEditado.setEmail(emailField.getValue());
			produtorEditado.setTelefone(telefoneField.getValue());
			produtorEditado.setAtivo(ativoField.getValue());
			produtorEditado.setCodigoIntegradora(codigoIntegradoraField.getValue());
			produtorEditado.setCpf(ClientUtil.numbers(cpfField.getValue()));
			produtorEditado.setProdutor(produtorField.getValue());
			produtorEditado.setTecnico(tecnicoField.getValue());
			produtorEditado.setGranjeiro(granjeiroField.getValue());
			produtorEditado.setApelido(apelidoField.getValue());
			client.update(produtorEditado, new Callback<PessoaI>() {
				
				@Override
				public void ok(PessoaI to) {
					if(!ClientUtil.isEmpty(senhaField.getValue())){
						client.updatePassword(to.getId(),senhaField.getValue(), new Callback<Boolean>() {
							@Override
							public void ok(Boolean to) {
								loadTabela();
								produtorEditado=null;
								formRow.setVisible(false);
							}
							public void onValidation(ErrosI erro) {
								ClientUtil.printValidation(erro,errorBox);
							};
						});
						
					}else{
						loadTabela();
						produtorEditado=null;
						formRow.setVisible(false);
					}
				}
				@Override
				public void onValidation(ErrosI erro) {
					ClientUtil.printValidation(erro,errorBox);
				}
			});
		}
	}
	@UiHandler("cancelarBtn")
	public void cancelarClick(ClickEvent evt){
		formRow.setVisible(false);
		errorBox.setVisible(false);
		errorBox.clear();
		produtorEditado=null;
	}

	@UiHandler("excluirBtn")
	public void excluirClick(ClickEvent evt){
		
		client.delete(produtorEditado.getId(), new Callback<Void>() {

			@Override
			public void ok(Void to) {
				loadTabela();
				produtorEditado=null;
				formRow.setVisible(false);
			}
		});
	}
//	
	@UiHandler("removerAcessoBtn")
	public void clickRemoverAcessoBtn(ClickEvent evt){
		client.removeSenhas(produtorEditado.getId(), new Callback<Boolean>() {

			@Override
			public void ok(Boolean sucesso) {
				blocoSenhas.setVisible(false);
				definirSenhaAnchor.setVisible(true);
				definirSenhaAnchor.setText("Este usuário NÃO possui acesso ao sistema. Clique aqui para definir uma senha");
				
			}
		});

	}
	@UiHandler("definirSenhaAnchor")
	public void clickDefinirSenhas(ClickEvent evt){
		blocoSenhas.setVisible(true);
		definirSenhaAnchor.setVisible(false);
	}

	public void loadForm(PessoaI produtor) {
		errorBox.setVisible(false);
		errorBox.clear();
		this.produtorEditado=produtor;
		nomeField.setValue(produtor.getNome());
		emailField.setValue(produtor.getEmail());
		codigoIntegradoraField.setValue(produtor.getCodigoIntegradora());
		ativoField.setValue(produtor.getAtivo());;
		telefoneField.setValue(produtor.getTelefone());
		cpfField.setValue(ClientUtil.formatDoc(produtor.getCpf()));
		produtorField.setValue(produtorEditado.getProdutor());
		tecnicoField.setValue(produtorEditado.getTecnico());
		granjeiroField.setValue(produtorEditado.getGranjeiro());
		apelidoField.setValue(produtorEditado.getApelido());
		blocoSenhas.setVisible(false);
		definirSenhaAnchor.setVisible(true);

		client.possuiSenha(produtor.getId(),new Callback<Boolean>() {

			@Override
			public void ok(Boolean possuiSenha) {
				if(possuiSenha){
					definirSenhaAnchor.setText("Este usuário ja possui acesso ao sistema. Clique aqui para redefinir Senha");
				}else{
					definirSenhaAnchor.setText("Este usuário NÃO possui acesso ao sistema. Clique aqui para definir uma senha");
				}
				
			}
		});
		
	}

}
