package com.agritap.sisintegracao.client;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.FieldSet;
import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.FormGroup;
import org.gwtbootstrap3.client.ui.FormLabel;
import org.gwtbootstrap3.client.ui.ListGroup;
import org.gwtbootstrap3.client.ui.ListGroupItem;
import org.gwtbootstrap3.client.ui.constants.ListGroupItemType;
import org.gwtbootstrap3.client.ui.constants.ValidationState;

import com.agritap.sisintegracao.client.request.beans.ErrosI;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Widget;

public class ClientUtil {
	static Logger log = Logger.getLogger(ClientUtil.class.getName());
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
	
//	public static void showError (ErrosI erros,Form form){
//		Set<String> fields=erros.getErrosFields().keySet();
//		for(String key:fields){
//			FormGroup formGroup = findFormGroup(form,key);
//			if(formGroup!=null){
//				formGroup.setValidationState(ValidationState.ERROR);
//				formGroup.setTitle(erros.getErrosFields().get(key));
//			}
//		}
////		onValidation(ErrosI erro) {
//	}


	public static Integer parseInteger(String value) {
		if(value==null){
			return null;
		}
		//TODO gtratar problema de numberformatexception
		// "asdsa"
		return Integer.parseInt(value);
	}

	public static void printValidation(ErrosI erro, ListGroup errorBox) {
		errorBox.clear();
		for(String err: erro.getErrosFields().values()){
			ListGroupItem it =new ListGroupItem();
			it.setType(ListGroupItemType.DANGER);
			it.setText(err);
			errorBox.add(it);
		}
		for(String err: erro.getErrosGenericos()){
			ListGroupItem it =new ListGroupItem();
			it.setType(ListGroupItemType.DANGER);
			it.setText(err);
			errorBox.add(it);
		}
		errorBox.setVisible(true);
		
	}
}
