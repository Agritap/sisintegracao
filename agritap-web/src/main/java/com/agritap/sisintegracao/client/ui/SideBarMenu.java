package com.agritap.sisintegracao.client.ui;

import com.agritap.sisintegracao.client.ViewEnum;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SideBarMenu extends Composite {

	private static SideBarUiBinder uiBinder = GWT.create(SideBarUiBinder.class);

	private ClientFactory factory;

	interface SideBarUiBinder extends UiBinder<Widget, SideBarMenu> {
	}

	public SideBarMenu(ClientFactory clientFactory) {
		this.factory=clientFactory;
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	@UiHandler("produtores")
	public void produtoresClick(ClickEvent evt){
		factory.getAppController().goTo(ViewEnum.PRODUTORES);
	}
	
	@UiHandler("tecnicos")
	public void tecnicosClick(ClickEvent evt){
		factory.getAppController().goTo(ViewEnum.TECNICOS);
	}
	
	@UiHandler("tabelaRacao")
	public void tabelaRacaoClick(ClickEvent evt){
		factory.getAppController().goTo(ViewEnum.TABELA_RACAO);
	}
	
	@UiHandler("configuracaoLote")
	public void configuracaoLoteClick(ClickEvent evt){
		factory.getAppController().goTo(ViewEnum.CONFIGURACAO_LOTE);
	}

	@UiHandler("controleMortalidade")
	public void controleMortalidadeClick(ClickEvent evt) {
		factory.getAppController().goTo(ViewEnum.CONTROLE_MORTALIDADE);
	}	
	@UiHandler("reposicaoLote")
	public void reposicaoLoteClick(ClickEvent evt){
		factory.getAppController().goTo(ViewEnum.REPOSICAO_LOTE);
	}
	@UiHandler("vacinacaoAnimais")
	public void vacinacaoAnimaisClick(ClickEvent evt) {
		factory.getAppController().goTo(ViewEnum.VACINACAO_ANIMAIS);
	}

}
