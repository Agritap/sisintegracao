package com.agritap.sisintegracao.client.ui;

import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.InlineCheckBox;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.gwt.HTMLPanel;

import com.agritap.sisintegracao.client.ClientUtil;
import com.agritap.sisintegracao.client.ViewEnum;
import com.agritap.sisintegracao.client.request.RestCallback;
import com.agritap.sisintegracao.client.request.clients.PessoaClient;
import com.agritap.sisintegracao.client.vo.ErrosValidacao;
import com.agritap.sisintegracao.client.vo.UsuarioTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Cookies;
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
	InlineCheckBox lembrarField;
	
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
	
	PessoaClient pessoaClient = GWT.create(PessoaClient.class);
	
	public LoginWindow(ClientFactory clientFactory, StateHistory st) {
		initWidget(uiBinder.createAndBindUi(this));
		emailField.addStyleName("email");
		passwordField.addStyleName("password");
		this.clientFactory = clientFactory;
		stateHistory = st;
		verifyToken();
	}


	private void verifyToken() {
		String cookie = Cookies.getCookie(ClientUtil.COOKIE_TOKEN_NAME);
		if(!ClientUtil.isEmpty(cookie)){
			pessoaClient.auth(cookie, new RestCallback<UsuarioTO>() {
				@Override
				public void success(UsuarioTO result) {
					login(result);
				}
			});
		}
	}
	

	private void geraToken(UsuarioTO to) {
		Cookies.setCookie(ClientUtil.COOKIE_TOKEN_NAME,to.getToken());
	}


	@UiHandler("passwordField")
	void onEnter(KeyDownEvent event) {
		if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            onClick(null);
      }
	}
	
	private void login(UsuarioTO to) {
		clientFactory.setAutenticado(to);
		String retorno = stateHistory.getParameter("st_retorno");
		if(lembrarField.getValue()!=null && lembrarField.getValue()){
			geraToken(to);
		}
		if(retorno==null){
			History.newItem(ViewEnum.INICIAL.getUrl());
		}else{
			History.newItem(retorno);
		}
	}
	
	@UiHandler("loginBtn")
	void onClick(ClickEvent e) {
		pessoaClient.auth(emailField.getText(),passwordField.getText(),new RestCallback<UsuarioTO>() {
			
			@Override
			public void success(UsuarioTO to) {
				login(to);
			}
			@Override
			public void onValidation(ErrosValidacao  erro) {
				mensagemErroBox.setVisible(true);
				mensagemErroBox.clear();
				for(String err:erro.getErrosGenericos()){
					Label l = new Label(err);
					mensagemErroBox.add(l);
				}
			}
		});
	}

}
