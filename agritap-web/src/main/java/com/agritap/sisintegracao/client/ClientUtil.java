package com.agritap.sisintegracao.client;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class ClientUtil {

	static DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
	public static String formatDate(Date data){
		if(data==null){
			return null;
		}
		return dateFormat.format(data);
	}
	
	public static String formatSimNao(Boolean b){
		if(b!=null && b){
			return "Sim";
		}else{
			return "Não";
		}
	}

	public static String formatInteger(Integer carencia) {
		if(carencia==null){
			return null;
		}
		return carencia.toString();
	}

	public static Integer parseInteger(String value) {
		if(value==null){
			return null;
		}
		//TODO gtratar problema de numberformatexception
		// "asdsa"
		return Integer.parseInt(value);
	}
}
