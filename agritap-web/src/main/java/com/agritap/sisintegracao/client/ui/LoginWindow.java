package com.agritap.sisintegracao.client.ui;

import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.gwt.HTMLPanel;

import com.agritap.sisintegracao.client.ViewEnum;
import com.agritap.sisintegracao.client.request.Callback;
import com.agritap.sisintegracao.client.request.beans.ErrosI;
import com.agritap.sisintegracao.client.request.beans.UsuarioI;
import com.agritap.sisintegracao.client.request.clients.PessoaClient;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LoginWindow extends Composite {

	Logger log = Logger.getLogger(LoginWindow.class.getName());

	private static LoginWindowUiBinder uiBinder = GWT.create(LoginWindowUiBinder.class);

	interface LoginWindowUiBinder extends UiBinder<Widget, LoginWindow> {
	}

	@UiField
	TextBox emailField;
	
	@UiField
	Input passwordField;

	@UiField
	HTMLPanel mensagemErroBox;
	
	@UiField
	Label mensagemErro;
	@UiField
	Form formSign;
	
	ClientFactory clientFactory;
	
	StateHistory stateHistory;
	
	PessoaClient pessoaClient = new PessoaClient();
	
	public LoginWindow(ClientFactory clientFactory, StateHistory st) {
		log.warning("passou aqui kct");
		initWidget(uiBinder.createAndBindUi(this));
		emailField.addStyleName("email");
		passwordField.addStyleName("password");
		this.clientFactory = clientFactory;
		stateHistory = st;
	}


	@UiHandler("loginBtn")
	void onClick(ClickEvent e) {
		pessoaClient.auth(emailField.getText(),passwordField.getText(),new Callback<UsuarioI>() {
			
			@Override
			public void ok(UsuarioI to) {
				clientFactory.setAutenticado(to);
				String retorno = stateHistory.getParameter("st_retorno");
				if(retorno==null){
					History.newItem(ViewEnum.INICIAL.getUrl());
				}else{
					History.newItem(retorno);
				}
			}
			@Override
			public void onValidation(ErrosI erro) {
				mensagemErroBox.setVisible(true);
				for(String err:erro.getErrosGenericos()){
					Label l = new Label(err);
					mensagemErroBox.add(l);
				}
			}
		});
	}

}
