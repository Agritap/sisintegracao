package com.agritap.sisintegracao.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface SisIntegracaoClientBundle extends ClientBundle {

    static final SisIntegracaoClientBundle INSTANCE = GWT.create(SisIntegracaoClientBundle.class);

	  @Source("resource/js/sb-admin-2.js")
    TextResource sbAdmin2();
}
