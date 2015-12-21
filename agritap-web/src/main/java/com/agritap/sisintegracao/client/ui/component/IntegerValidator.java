package com.agritap.sisintegracao.client.ui.component;

import org.gwtbootstrap3.client.ui.form.validator.AbstractValidator;

import com.agritap.sisintegracao.client.ClientUtil;

public class IntegerValidator extends AbstractValidator<Integer>{

    public IntegerValidator() {
		super("Informe um valor numérico não decimal");
	}


	@Override
    public int getPriority() {
        return Priority.MEDIUM;
    }


	@Override
	public boolean isValid(Integer value) {
		if(ClientUtil.isEmpty(value)){
			return true;
		}
		try{
//			Integer.parseInt(value);
			return true;
		}catch(Exception e){
			return false;
		}
	}



}
