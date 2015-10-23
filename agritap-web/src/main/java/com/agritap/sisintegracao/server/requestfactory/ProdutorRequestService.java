package com.agritap.sisintegracao.server.requestfactory;

import java.util.List;

import javax.persistence.EntityManager;

import com.agritap.sisintegracao.client.model.Produtor;
import com.google.inject.persist.Transactional;

public class ProdutorRequestService extends GenericLocator<Produtor, Integer>{

	
	public List<Produtor> produtores(){
		EntityManager em = injector.getInstance(EntityManager.class);
		List<Produtor> l = em.createQuery("select p from Produtor p",Produtor.class).getResultList();
//		em.close();
		return l;
	}

	@Transactional
	public void persist(Produtor produtor){
		System.out.println("asdasdas");
		EntityManager em = injector.getInstance(EntityManager.class);
		em.merge(produtor);
//		em.close();
	}
	

	
}
