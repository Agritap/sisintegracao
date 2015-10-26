package com.agritap.sisintegracao.client;

import com.agritap.sisintegracao.client.request.EntityFactory;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class ClientResource {

	public static final EntityFactory entityFactory = GWT.create(EntityFactory.class);

	public static final EventBus EVENT_BUS = GWT.create(SimpleEventBus.class);

}
