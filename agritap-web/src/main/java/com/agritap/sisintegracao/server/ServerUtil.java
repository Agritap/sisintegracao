package com.agritap.sisintegracao.server;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class ServerUtil {

	public static final String TOKEN_SEP = "-";

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

	public static String generateToken(Integer id) {
		Calendar agora= Calendar.getInstance();
		agora.add(Calendar.DAY_OF_MONTH, 30);
		String tok = id+TOKEN_SEP+agora.getTimeInMillis();
		return crypt(tok);
	}

	public static Date getTokenExpiracao(String authToken) {
		String token = ServerUtil.decrypt(authToken);
		StringTokenizer buf = new StringTokenizer(token,TOKEN_SEP);
		buf.nextToken(); //joga o id fora
//		Integer id = Integer.parseInt(buf.nextToken());
		long expires = Long.parseLong(buf.nextToken());
		Date exp = new Date(expires);

		return exp;
	}

	public static Integer getTokenId(String authToken) {
		String token = ServerUtil.decrypt(authToken);
		StringTokenizer buf = new StringTokenizer(token,TOKEN_SEP);
		Integer id = Integer.parseInt(buf.nextToken());
		return id;
	}

}
