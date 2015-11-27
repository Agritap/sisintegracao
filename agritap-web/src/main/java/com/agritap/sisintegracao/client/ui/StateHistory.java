package com.agritap.sisintegracao.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.agritap.sisintegracao.client.ViewEnum;

public class StateHistory {

	ViewEnum view;
	
	Map<String,String> parameters;

	public StateHistory(){}
	
	public StateHistory(ViewEnum view){
		this.view=view;
	}
	
	public StateHistory(ViewEnum view,Map<String,String> par){
		this.view=view;
		this.parameters=par;
	}
	
	public void addParam(String key,String value){
		if(parameters==null){
			parameters=new HashMap<String,String>();
		}
		parameters.put(key,value);
	}

	public ViewEnum getView() {
		return view;
	}

	public void setView(ViewEnum view) {
		this.view = view;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public boolean isRequerAutenticacao() {
		return view.isRequerAutenticacao();
	}
	
	@Override
	public String toString() {
		String retorno = view.getUrl();
		if(parameters!=null && parameters.size()>0){
			retorno+="?";
			int param=0;
			for(String chave:parameters.keySet()){
				param++;
				if(param>1){
					retorno+="&";
				}
				retorno+=chave+"="+parameters.get(chave);
			}
		}
		return retorno;
	}
//	@Override
//	public StateHistory fromString(String state) {
//		
//		String retorno = view.name();
//		if(parameters!=null && parameters.size()>0){
//			retorno+="|";
//			for(String chave:parameters.keySet()){
//				retorno+=chave+"="+parameters.get(chave);
//			}
//		}
//		return retorno;
//	}

	public String getParameter(String key) {
		if(parameters==null){
			return null;
		}
		return parameters.get(key);
	}

	public static StateHistory fromString(String token) {
		if(token==null){
			return null;
		}
		int posicaoInterrog = token.indexOf("?");
		if(posicaoInterrog>0){
			//tem param;
			String url = token.substring(0,posicaoInterrog);
			ViewEnum en = ViewEnum.getView(url);
			StateHistory st = new StateHistory(en);
			String parameterUrl = token.substring(posicaoInterrog+1);
			String[] params= parameterUrl.split("&");
			for(String par:params){
				st.addParam(par.substring(0,par.indexOf("=")), par.substring(par.indexOf("=")+1)) ;
			}
			return st;
		}else{
			ViewEnum en = ViewEnum.getView(token);
			return new StateHistory(en);
		}
	}

}
