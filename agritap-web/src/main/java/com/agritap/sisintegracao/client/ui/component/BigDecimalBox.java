package com.agritap.sisintegracao.client.ui.component;

import java.math.BigDecimal;
import java.text.ParseException;

import org.gwtbootstrap3.client.ui.base.ValueBoxBase;
import org.gwtbootstrap3.client.ui.constants.Styles;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;

public class BigDecimalBox extends ValueBoxBase<BigDecimal>{

	public BigDecimalBox() {
        super(Document.get().createTextInputElement(), BigDecimalRenderer.instance(), BigDecimalParser.instance());
        addStyleName(Styles.FORM_CONTROL);
        addDefaultHandlers();
	}

	private void addDefaultHandlers() {
		setValidateOnBlur(true);
		setAlignment(TextAlignment.RIGHT);
		addKeyPressHandler(new KeyPressHandler() {
			
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if(event.getCharCode()=='.' ){
	                ((BigDecimalBox)event.getSource()).cancelKey();
	                ((BigDecimalBox)event.getSource()).setText(((BigDecimalBox)event.getSource()).getText()+",");
	                return;
				}
				if(event.getCharCode()==','){
					return;
				}
				
				if(event.getNativeEvent().getKeyCode() == KeyCodes.KEY_TAB){
					return;	
				}
				if(!Character.isDigit(event.getCharCode())){
					((BigDecimalBox)event.getSource()).cancelKey();
				}
			}
		});
		
		addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				try{
					BigDecimalParser.instance().parse(((BigDecimalBox)event.getSource()).getText());
				}catch(ParseException ex){
					((BigDecimalBox)event.getSource()).setText("");
				}
			}
		});
	}
	

	
}
