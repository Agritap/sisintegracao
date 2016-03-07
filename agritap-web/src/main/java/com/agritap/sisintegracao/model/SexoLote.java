package com.agritap.sisintegracao.model;

public enum SexoLote implements Rotulavel{

	MACHO("Macho"),FEMEA("Fêmea"),MISTO("Misto");

	private String rotulo;
	
	private SexoLote(String descricao){
		this.rotulo=descricao;
	}
	@Override
	public String getRotulo() {
		return rotulo;
	}
	@Override
	public String getIdAsString() {
		return name();
	}
}
