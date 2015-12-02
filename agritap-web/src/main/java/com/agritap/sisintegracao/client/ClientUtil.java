package com.agritap.sisintegracao.client;

import java.util.Date;
import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.ListGroup;
import org.gwtbootstrap3.client.ui.ListGroupItem;
import org.gwtbootstrap3.client.ui.constants.ListGroupItemType;

import com.agritap.sisintegracao.client.request.beans.ErrosI;
import com.agritap.sisintegracao.model.Integradora;
import com.agritap.sisintegracao.model.Rotulavel;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Cookies;

public class ClientUtil {
	static Logger log = Logger.getLogger(ClientUtil.class.getName());
	static DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
	
	public static final String COOKIE_TOKEN_NAME = "agritapweb_token";
	
	
	
	public static void clearToken() {
		Cookies.removeCookie(COOKIE_TOKEN_NAME);
	}
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
		if(isEmpty(value)){
			return null;
		}
		try{
			return Integer.parseInt(value);
		}catch(NumberFormatException ex){
			return null;
		}
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

	public static boolean isEmpty(Object str) {
		if(str==null){
			return true;
		}
		return str.toString().trim().equals("");
	}
	
	public static void populaListBox(ListBox integradoraField, Enum<?>[] values,boolean emptyOption) {
		integradoraField.clear();
		if(emptyOption){
			integradoraField.addItem("","");
		}
		for(Enum<?> enu:values){
			if(enu instanceof Rotulavel){
				integradoraField.addItem(enu.name(),((Rotulavel)enu).getRotulo());
			}else{
				integradoraField.addItem(enu.name());
			}
		}
		if(values.length==1){
			if(emptyOption){
				integradoraField.setSelectedIndex(1);
			}else{
				integradoraField.setSelectedIndex(0);
			}
		}
		
	}
	public static void populaListBox(ListBox integradoraField, Integradora[] values) {
		populaListBox(integradoraField, values,false);
	}
	public static String formatDoc(String doc) {
		if (isEmpty(doc)) {
			return null;
		}
		return doc.length() <= 11 ? formatCPF(doc) : formatCNPJ(doc);
	}
	
	public static String formatCPF(String cpf) {
		if (isEmpty(cpf)) {
			return "";
		}
		StringBuilder fmt = new StringBuilder();
		try {
			fmt.append(cpf.substring(0, 3)).append('.').append(cpf.substring(3, 6)).append('.')
					.append(cpf.substring(6, 9)).append('-').append(cpf.substring(9));
		} catch (StringIndexOutOfBoundsException e) {// This is OK. It happens
		}
		return fmt.toString();
	}
	public static String numbers(String str) {
		if (str == null) {
			return null;
		}
		return isEmpty(str) ? str : str.replaceAll("[^\\d]", "");
	}
	public static String formatCNPJ(String cnpj) {
		if (isEmpty(cnpj)) {
			return "";
		}
		StringBuilder fmt = new StringBuilder();
		try {
			fmt.append(cnpj.substring(0, 2)).append('.').append(cnpj.substring(2, 5)).append('.')
					.append(cnpj.substring(5, 8)).append('/').append(cnpj.substring(8, 12)).append('-')
					.append(cnpj.substring(12));
		} catch (StringIndexOutOfBoundsException e) {// This is OK. It happens
		}
		return fmt.toString();
	}

}
