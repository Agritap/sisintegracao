package com.agritap.sisintegracao.server;

import java.util.Date;

import com.agritap.sisintegracao.client.ValidacaoException;

public class ServerUtil {

	public static String crypt(String pass) {
		return pass;
	}

	public static String decrypt(String authToken) {
		return authToken;
	}
	
	public static boolean isPeriodosIntercecao(Date inicio1,Date fim1,Date inicio2,Date fim2){
		if(
				inicio1==null ||
				(inicio1!=null && 
				(
				( inicio1!=null && inicio2.compareTo(inicio1)>=0 )  
				|| 
				inicio1==null )) ){
			if(fim1 == null
					|| inicio2==null
					|| fim1.compareTo(inicio2) >0 
					){
				//nomes duplicados??0
				return true;
				
			}
			return false;
		}
		return false;
	}
	
	public static boolean isEmpty(Object str) {
		if(str==null){
			return true;
		}
		return str.toString().trim().equals("");
	}

}
