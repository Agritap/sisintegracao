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

}
