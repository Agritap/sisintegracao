package com.agritap.sisintegracao.client.ui;

import com.agritap.sisintegracao.client.ViewEnum;
import com.agritap.sisintegracao.client.ui.configuracao.ConfiguracaoLote;
import com.agritap.sisintegracao.client.ui.configuracao.ControleMortalidade;
import com.agritap.sisintegracao.client.ui.configuracao.ReposicaoLote;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;

public class ViewFactory {

	private ViewFactory(){}
	
	//Use gin to make singleton?
	private static ViewFactory instance;
	
	private MainWindow mainWindow;
	
	private LoginWindow loginWindow;

	public static ViewFactory getInstance(){
		if(instance==null){
			instance=new ViewFactory();
		}
		return instance;
	}
	
	public Composite init(){
		String token = History.getToken();
		if(token.equals("")){
			token=ViewEnum.INICIAL.getUrl();
		}
		ViewEnum view = ViewEnum.getView(token);
		if(view==null){
			view=ViewEnum.INICIAL;
		}
		StateHistory st = new StateHistory();
		st.setView(view);
		return openView(st);
	}

	public MainWindow getMainWindow(){
		if(mainWindow==null){
			mainWindow=new MainWindow();
		}
		return mainWindow;
	}

	public Composite openView(StateHistory st) {
		ViewEnum view = st.getView();
		History.newItem(view.getUrl());
		if(view.equals(ViewEnum.INICIAL)){
			return getMainWindow();
		}
		MainWindow mw = getMainWindow();
		if(view.equals(ViewEnum.CONFIGURACAO_LOTE)){
			mw.addConteudo(new ConfiguracaoLote());
			return mw;
		}
		MainWindow mwcm = getMainWindow ();
		if(view.equals(ViewEnum.CONTROLE_MORTALIDADE)){
			mwcm.addConteudo(new ControleMortalidade());
			return mwcm;
		}
		
		MainWindow mwc = getMainWindow ();
		if(view.equals(ViewEnum.REPOSICAO_LOTE)){
			mwcm.addConteudo(new ReposicaoLote());
			return mwc;
		}
		
		return null;
	}

}
