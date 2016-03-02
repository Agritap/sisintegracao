package com.agritap.sisintegracao.client;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.ListGroup;
import org.gwtbootstrap3.client.ui.ListGroupItem;
import org.gwtbootstrap3.client.ui.constants.ListGroupItemType;

import com.agritap.sisintegracao.client.request.beans.ErrosI;
import com.agritap.sisintegracao.model.Rotulavel;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;

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
	
	public static void populateListBox(ListBox listBox,List<?> pessoas) {
		listBox.clear();
		for (Object obj:pessoas){
			if(obj instanceof Rotulavel){
				listBox.addItem(((Rotulavel)obj).getRotulo(),((Rotulavel)obj).getIdAsString());
			}else{
				listBox.addItem(obj.toString());
			}
		}
		if(pessoas.size()==1){
			listBox.setSelectedIndex(0);
		}
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

	public static void prepareIntegerBox(IntegerBox input){
		input.setValidateOnBlur(true);
		input.setAlignment(TextAlignment.RIGHT);
		input.addKeyPressHandler(new KeyPressHandler() {
			
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if(event.getNativeEvent().getKeyCode() == KeyCodes.KEY_TAB){
					return;	
				}
				if(!Character.isDigit(event.getCharCode()))
	                ((IntegerBox)event.getSource()).cancelKey();
			}
		});
		input.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				try{
					Integer.parseInt(((IntegerBox)event.getSource()).getText());
				}catch(NumberFormatException ex){
					((IntegerBox)event.getSource()).setText("");
				}
			}
		});
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
	
	public static void populaListBox(ListBox listBox, Enum<?>[] values,boolean emptyOption) {
		listBox.clear();
		if(emptyOption){
			listBox.addItem("","");
		}
		for(Enum<?> enu:values){
			if(enu instanceof Rotulavel){
				listBox.addItem(enu.name(),((Rotulavel)enu).getRotulo());
			}else{
				listBox.addItem(enu.name());
			}
		}
		if(values.length==1){
			if(emptyOption){
				listBox.setSelectedIndex(1);
			}else{
				listBox.setSelectedIndex(0);
			}
		}
		
	}
	public static void populaListBox(ListBox listBox, Enum<?>[] values) {
		populaListBox(listBox, values,false);
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
	
	public static BigDecimal parseBigDecimal(String value) {
		if(isEmpty(value)){
			return null;
		}
		value = value.replaceAll("\\.", "");
		value = value.replaceAll(",", ".");
		return new BigDecimal(value);
	}
	public static String formatBigDecimal(BigDecimal value) {
		if(isEmpty(value)){
			return null;
		}
		NumberFormat nf =NumberFormat.getDecimalFormat();
		return nf.format(value);
	}

}
