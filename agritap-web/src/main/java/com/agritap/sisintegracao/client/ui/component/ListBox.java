package com.agritap.sisintegracao.client.ui.component;

import java.util.LinkedList;
import java.util.List;

import com.agritap.sisintegracao.client.ClientUtil;
import com.agritap.sisintegracao.model.Rotulavel;

public class ListBox extends org.gwtbootstrap3.client.ui.ListBox{

	@SuppressWarnings("rawtypes")
	List itens;
	public void setItems(List itens) {
		setItems(itens,false);
	}
	public void setItems(Enum<?>[] itens) {
		setItems(itens,false);
	}
	
	public void setSelectedValue(Object val) {
		if(val==null){
			setSelectedIndex(0);
		}
		String key=null;
		if(val instanceof Rotulavel){
			key=((Rotulavel)val).getIdAsString();
		}else{
			key = val.toString();
		}
		for (int i = 0; i < getItemCount(); i++) {
			String testKey = getValue(i);
			if(testKey.equals(key)){
				setSelectedIndex(i);
				return;
			}
		}
		itens.add(val);
		if(val instanceof Rotulavel){
			addItem(((Rotulavel)val).getRotulo(),((Rotulavel)val).getIdAsString());
		}else{
			addItem(val.toString());
		}
		setSelectedIndex(getItemCount()-1);
	}

	public void setItems(Enum<?>[] itens,boolean emptyOption) {
		List aux = new LinkedList<>();
		for(Enum<?> e:itens){
			aux.add(e);
		}
		setItems(aux,emptyOption);
	}

	public void setItems(List<?> itens,boolean emptyOption) {
		this.clear();
		this.itens=itens;
		if(itens==null){
			return;
		}
		if(emptyOption){
			addItem("","");
		}
		for (Object obj:itens){
			if(obj instanceof Rotulavel){
				addItem(((Rotulavel)obj).getRotulo(),((Rotulavel)obj).getIdAsString());
			}else{
				addItem(obj.toString());
			}
		}
		if(itens.size()==1 && !emptyOption){
			setSelectedIndex(0);
		}
	}
	public Object getSelectedObject(){
		String val = getSelectedValue();
		if(ClientUtil.isEmpty(val)){
			return null;
		}
		if(itens==null){
			return null;
		}
		for (Object obj:itens){
			if(obj instanceof Rotulavel){
				if(((Rotulavel)obj).getIdAsString().equals(val)){
					return obj;
				}
			}else{
				if(obj.toString().equals(val)){
					return obj;
				}				
			}			
		}
		return null;
	}
	
}
