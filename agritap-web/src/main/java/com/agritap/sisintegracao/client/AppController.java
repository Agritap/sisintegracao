package com.agritap.sisintegracao.client;

import java.util.logging.Logger;

import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.client.ui.MainWindow;
import com.agritap.sisintegracao.client.ui.StateHistory;
import com.agritap.sisintegracao.client.ui.configuracao.ControleMortalidade;
import com.agritap.sisintegracao.client.ui.configuracao.LoteView;
import com.agritap.sisintegracao.client.ui.configuracao.LotesView;
import com.agritap.sisintegracao.client.ui.configuracao.ModuloView;
import com.agritap.sisintegracao.client.ui.configuracao.ModulosView;
import com.agritap.sisintegracao.client.ui.configuracao.PessoasView;
import com.agritap.sisintegracao.client.ui.configuracao.ReposicaoLote;
import com.agritap.sisintegracao.client.ui.configuracao.TabelasRacaoView;
import com.agritap.sisintegracao.client.ui.configuracao.TipoRacaoView;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements ValueChangeHandler<String>{
	
	Logger log=Logger.getLogger(AppController.class.getName());
	
	ClientFactory factory;
	
	HasWidgets container;
	
	public AppController(ClientFactory fac){
		factory=fac;
		fac.setAppController(this);
		bind();
	}
	
	public void bind(){
		History.addValueChangeHandler(this);
	}
	
	public void go(final HasWidgets container) {
	    this.container = container;

	    if ("".equals(History.getToken())) {
	      History.newItem(ViewEnum.INICIAL.getUrl());
	    }
	    else {
	      History.fireCurrentHistoryState();
	    }
	  }
	
	 public void onValueChange(ValueChangeEvent<String> event) {
		    String token = event.getValue();

			if(token.equals("")){
				StateHistory st = new StateHistory();
				st.setView(ViewEnum.INICIAL);
				openView(st);
				return;
			}
			StateHistory st = StateHistory.fromString(token);
			openView(st);
	 }
	 
	 public void goTo(StateHistory st){
		 History.newItem(st.toString());
	 }
	 public void goTo(ViewEnum vw){
		 History.newItem(vw.getUrl());
	 }
	 public Composite openView(StateHistory st) {
			ViewEnum view = st.getView();
			if(st.isRequerAutenticacao()){
				if(!factory.isAutenticado()){
					//redireciona para tela de login
					StateHistory s = new StateHistory(ViewEnum.LOGIN);
					s.addParam("st_retorno", st.toString());		
					History.newItem(s.toString());
					return null;
				}
			}
			if(view.equals(ViewEnum.LOGIN)){
				return factory.openLoginWindow(container,st);
			}
//			History.newItem(view.getUrl());
			MainWindow mw = factory.getMainWindow(container);
			if(view.equals(ViewEnum.INICIAL)){
				mw.open();
				return mw;
			}
			if(view.equals(ViewEnum.LOTES)){
				mw.addConteudo(new LotesView(factory));
				return mw;
			}
			if(view.equals(ViewEnum.LOTE)){
				mw.addConteudo(new LoteView(factory,st));
				return mw;
			}
			if(view.equals(ViewEnum.CONTROLE_MORTALIDADE)){
				mw.addConteudo(new ControleMortalidade());
				return mw;
			}
			if(view.equals(ViewEnum.PESSOAS)){
				mw.addConteudo(new PessoasView(factory));
				return mw;
			}
			if(view.equals(ViewEnum.TIPORACAO)){
				mw.addConteudo(new TipoRacaoView(factory));
				return mw;
			}
			if(view.equals(ViewEnum.TABELA_RACAO)){
				mw.addConteudo(new TabelasRacaoView(factory));
				return mw;
			}
			if(view.equals(ViewEnum.REPOSICAO_LOTE)){
				mw.addConteudo(new ReposicaoLote());
				return mw;
			}
			if(view.equals(ViewEnum.MODULOS)){
				mw.addConteudo(new ModulosView(factory));
				return mw;
			}
			if(view.equals(ViewEnum.MODULO)){
				mw.addConteudo(new ModuloView(factory));
				return mw;
			}
			
			
			return null;
		}
//		    
}
