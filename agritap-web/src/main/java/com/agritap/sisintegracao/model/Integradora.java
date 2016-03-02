package com.agritap.sisintegracao.model;

public enum Integradora implements Rotulavel{
	BRF;

	@Override
	public String getRotulo() {
		return name();
	}

	@Override
	public String getIdAsString() {
		return name();
	}
}
