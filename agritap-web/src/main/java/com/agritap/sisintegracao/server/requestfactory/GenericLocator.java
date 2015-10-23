package com.agritap.sisintegracao.server.requestfactory;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.agritap.sisintegracao.server.GenericDAO;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.shared.Locator;

public abstract class GenericLocator<T,I> extends Locator<T, I>{
	
	@Inject
	protected Injector injector;
	
	@Override
	public T create(Class<? extends T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public T find(Class<? extends T> clazz, I id) {
		GenericDAO dao = injector.getInstance(GenericDAO.class);
		return dao.find(clazz,(Serializable)id);
	}

	@Override
	public Class<T> getDomainType() {
		throw new UnsupportedOperationException();//unused
	}

	@Override
	public I getId(T domainObject) {
		Method m;
		try {
			m = domainObject.getClass().getMethod("getId");
			return (I) m.invoke(domainObject, null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			//Erro de reflection
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Class<I> getIdType() {
		return (Class<I>) Integer.class;
	}

	@Override
	public Object getVersion(T domainObject) {
		Method m;
		try {
			m = domainObject.getClass().getMethod("getVersion");
			return m.invoke(domainObject, null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			//Erro de reflection
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Injector getInjector() {
		return injector;
	}

	public void setInjector(Injector injector) {
		this.injector = injector;
	}

}
