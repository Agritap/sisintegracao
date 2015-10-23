package com.agritap.sisintegracao.server;

import java.io.Serializable;

import javax.persistence.EntityManager;

import com.google.inject.Inject;

public class GenericDAO {

	@Inject
	EntityManager em;

	public <T> T find(Class<T> clazz, Serializable id) {
		return em.find(clazz, id);
	}
}
