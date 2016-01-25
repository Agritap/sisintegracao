package com.agritap.sisintegracao.client.ui.component;

import java.math.BigDecimal;
import java.text.ParseException;

import com.agritap.sisintegracao.client.ClientUtil;
import com.google.gwt.text.shared.Parser;

public class BigDecimalParser implements Parser<BigDecimal> {

	 private static BigDecimalParser INSTANCE;

	  /**
	   * Returns the instance of the no-op renderer.
	   */
	  public static Parser<BigDecimal> instance() {
	    if (INSTANCE == null) {
	      INSTANCE = new BigDecimalParser();
	    }
	    return INSTANCE;
	  }

	  protected BigDecimalParser() {
	  }

	  public BigDecimal parse(CharSequence object) throws ParseException {
	    if (ClientUtil.isEmpty(object)) {
	      return null;
	    }

	    try {
	    	String obj = object.toString().replaceAll("\\.", "");
	    	obj = obj.replaceAll(",", ".");
	    	if(obj.charAt(obj.length()-1)=='.'){
	    		obj = obj.substring(0, obj.length()-1);
	    	}
	    	return new BigDecimal(obj);
	    } catch (NumberFormatException e) {
	      throw new ParseException(e.getMessage(), 0);
	    }
	  }
}
