package com.agritap.sisintegracao.client;

public enum ViewEnum {

	LOGIN("/login"),INICIAL("/inicial"),ERRO("/erro"),CONFIGURACAO_LOTE("/configuracaoLote");

	public String url;
	
	private  ViewEnum (String url){
		this.url=url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static ViewEnum getView(String token) {
		for(ViewEnum v:values()){
			if(token.startsWith(v.getUrl())){
				return v;
			}
		}
		return null;
	}
	
}
