package com.agritap.sisintegracao.server;

import static org.junit.Assert.assertTrue;

import java.util.Date;

public class ServerUtil {

	public static String crypt(String pass) {
		return pass;
	}

	public static String decrypt(String authToken) {
		return authToken;
	}
	
	public static boolean isPeriodosIntercecao(Date inicioA,Date fimA,Date inicioB,Date fimB){
//		ServerUtil.isPeriodosIntercecao(fevereiro.getTime(), agosto.getTime(), janeiro.getTime(),
//				junho.getTime());

		if(inicioB!=null){
			if(inicioA!=null){
				if(inicioA.compareTo(inicioB)<=0){
					if(fimA==null || fimA.compareTo(inicioB)>=0){
						return true;
					}
				}else if(fimB==null || fimB.compareTo(inicioA)>=0){
					return true;
				}
			}else{
				if(fimA==null || fimA.compareTo(inicioB)>=0){
					return true;
				}
			}
		}else{
			if(fimB==null || inicioA==null || fimB.compareTo(inicioA)>=0){
				return true;
			}
		}
		return false;
//		if(
//				inicio1==null ||
//				(inicio1!=null && 
//				(
//				( inicio1!=null && inicio2.compareTo(inicio1)>=0 )  
//				|| 
//				inicio1==null )) ){
//			if(fim1 == null
//					|| inicio2==null
//					|| fim1.compareTo(inicio2) >0 
//					){
//				//nomes duplicados??0
//				return true;
//				
//			}
//			return false;
//		}
	}
	
	public static boolean isEmpty(Object str) {
		if(str==null){
			return true;
		}
		return str.toString().trim().equals("");
	}

}
