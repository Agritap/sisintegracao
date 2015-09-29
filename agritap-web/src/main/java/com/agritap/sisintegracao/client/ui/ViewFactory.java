package com.agritap.sisintegracao.client.ui;

import com.agritap.sisintegracao.client.Sample;
import com.agritap.sisintegracao.client.ViewEnum;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;

public class ViewFactory {

	private ViewFactory(){}
	
	//Use gin to make singleton?
	private static ViewFactory instance;
	
	private MainWindow mainWindow;
	
	private LoginWindow loginWindow;

	public static ViewFactory getInstance(){
		if(instance==null){
			instance=new ViewFactory();
		}
		return instance;
	}
	
	public Composite init(){
		String token = History.getToken();
		if(token.equals("")){
			token=ViewEnum.INICIAL.getUrl();
		}
		ViewEnum view = ViewEnum.getView(token);
		if(view==null){
			view=ViewEnum.INICIAL;
		}
		return openView(view);
	}

	private Composite openView(ViewEnum view) {
		if(view.equals(ViewEnum.INICIAL)){
			MainWindow mw = new MainWindow();
			return mw;
		}
		if(view.equals(ViewEnum.INICIAL)){
			Sample sample = new Sample();
			return sample;
		}
		return openView(ViewEnum.ERRO);
	}

}
