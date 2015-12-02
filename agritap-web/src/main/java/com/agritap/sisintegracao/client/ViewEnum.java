package com.agritap.sisintegracao.client;

public enum ViewEnum {

	LOGIN("/login",false),INICIAL("/inicial"),
	ERRO("/erro"),
	CONFIGURACAO_LOTE("/configuracaoLote"), 
	CONTROLE_MORTALIDADE("/controleMortalidade"),
	REPOSICAO_LOTE("/reposicaoLote"),
<<<<<<< HEAD
	PRODUTORES("/produtores"),
	VACINACAO_ANIMAIS("/vacinacaoAnimais"),
	TABELA_RACAO("/tabelaRacao"),
	TECNICOS("/tecnicos");
=======
	PESSOAS("/pessoas"),
	TIPORACAO("/tipoRacao"),
	RECEBIMENTOS("/recebimentos"),
	VACINACAO_ANIMAIS("/vacinacaoAnimais");
>>>>>>> a5369d748f4a44296e001de459c4d495c8e9922b

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
