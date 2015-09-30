package com.agritap.sisintegracao.client;

import com.agritap.sisintegracao.client.ui.ViewFactory;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;

public class MainApp implements EntryPoint {

	@Override
	public void onModuleLoad() {
//		<stylesheet src="css/sb-admin-2.css"/>
//		<stylesheet src="css/timeline.css"/>
//		<script src="js/sb-admin-2.js"/>
		ViewFactory vf = ViewFactory.getInstance();
		Composite c = vf.init();
//		if(autenticado){
			//abre a tela de login
//		}else{
			//abre a tela menu
			//pega o usuario.
			//descobre quais permissoes ele
//		}
		RootPanel.get().add(c);
	}

}
