package com.agritap.sisintegracao.client;

public enum ViewEnum {

	LOGIN("/login",false),INICIAL("/inicial"),
	ERRO("/erro"),
	LOTES("/lotes"), 
	LOTE("/lote"), 
	CONTROLE_MORTALIDADE("/controleMortalidade"),
	REPOSICAO_LOTE("/reposicaoLote"),
	PRODUTORES("/produtores"),
	TABELAS_RACAO("/tabelasRacao"),
	TABELA_RACAO("/tabelaRacao"),
	PESSOAS("/pessoas"),
	TIPORACAO("/tipoRacao"),
	RECEBIMENTOS("/recebimentos"),
	MODULOS("/modulos"),
	VACINACAO_ANIMAIS("/vacinacaoAnimais"),
	MODULO("/modulo");
	

	public String url;

	public boolean requerAutenticacao;

	private ViewEnum(String url) {
		this(url, true);
	}

	private ViewEnum(String url, boolean requerAutenticacao) {
		this.url = url;
		this.requerAutenticacao = requerAutenticacao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static ViewEnum getView(String token) {
		for (ViewEnum v : values()) {
			if (token.startsWith(v.getUrl())) {
				return v;
			}
		}
		return null;
	}

	public boolean isRequerAutenticacao() {
		return requerAutenticacao;
	}

	public void setRequerAutenticacao(boolean requerAutenticacao) {
		this.requerAutenticacao = requerAutenticacao;
	}

}
