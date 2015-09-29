package com.agritap.sisintegracao.client;

import com.agritap.sisintegracao.client.ui.MainWindow;
import com.agritap.sisintegracao.client.ui.ViewFactory;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;

public class MainApp implements EntryPoint {

	@Override
	public void onModuleLoad() {
		
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
