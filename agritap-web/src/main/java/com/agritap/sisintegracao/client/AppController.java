package com.agritap.sisintegracao.client;

import com.agritap.sisintegracao.client.ui.ClientFactory;
import com.agritap.sisintegracao.client.ui.MainWindow;
import com.agritap.sisintegracao.client.ui.StateHistory;
import com.agritap.sisintegracao.client.ui.configuracao.ConfiguracaoLote;
import com.agritap.sisintegracao.client.ui.configuracao.ControleMortalidade;
import com.agritap.sisintegracao.client.ui.configuracao.ProdutoresView;
import com.agritap.sisintegracao.client.ui.configuracao.ReposicaoLote;
import com.agritap.sisintegracao.client.ui.configuracao.TabelaRacaoView;
import com.agritap.sisintegracao.client.ui.configuracao.TecnicosView;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements ValueChangeHandler<String>{

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
	      History.newItem("inicial");
	    }
	    else {
	      History.fireCurrentHistoryState();
	    }
	  }
	
	 public void onValueChange(ValueChangeEvent<String> event) {
		    String token = event.getValue();

			if(token.equals("")){
				token=ViewEnum.INICIAL.getUrl();
			}
			ViewEnum view = ViewEnum.getView(token);
			if(view==null){
				view=ViewEnum.INICIAL;
			}
			StateHistory st = new StateHistory();
			st.setView(view);
			openView(st);
	 }
	 
	 public void goTo(StateHistory st){
		 History.newItem(st.getView().getUrl());
	 }
	 public void goTo(ViewEnum vw){
		 History.newItem(vw.getUrl());
	 }
	 public Composite openView(StateHistory st) {
			ViewEnum view = st.getView();
			History.newItem(view.getUrl());
			MainWindow mw = factory.getMainWindow(container);
			if(view.equals(ViewEnum.INICIAL)){
				mw.open();
			}
			if(view.equals(ViewEnum.CONFIGURACAO_LOTE)){
				mw.addConteudo(new ConfiguracaoLote());
				return mw;
			}
			if(view.equals(ViewEnum.CONTROLE_MORTALIDADE)){
				mw.addConteudo(new ControleMortalidade());
				return mw;
			}
			if(view.equals(ViewEnum.PRODUTORES)){
				mw.addConteudo(new ProdutoresView(factory));
				return mw;
			}
			if(view.equals(ViewEnum.TABELA_RACAO)){
				mw.addConteudo(new TabelaRacaoView(factory));
				return mw;
			}
			if(view.equals(ViewEnum.REPOSICAO_LOTE)){
				mw.addConteudo(new ReposicaoLote());
				return mw;
			}
			if(view.equals(ViewEnum.TECNICOS)){
				mw.addConteudo(new TecnicosView(factory));
				return mw;
			}
			
			return null;
		}
//		    if (token != null) {
//		      Presenter presenter = null;
//
//		      if (token.equals("list")) {
//		        presenter = new ContactsPresenter(rpcService, eventBus, new ContactView());
//		      }
//		      else if (token.equals("add")) {
//		        presenter = new EditContactPresenter(rpcService, eventBus, new EditContactView());
//		      }
//		      else if (token.equals("edit")) {
//		        presenter = new EditContactPresenter(rpcService, eventBus, new EditContactView());
//		      }
//
//		      if (presenter != null) {
//		        presenter.go(container);
//		      }
//		    }
}
