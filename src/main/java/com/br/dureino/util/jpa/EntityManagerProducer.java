package com.br.dureino.util.jpa;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory factory;

	public EntityManagerProducer(EntityManagerFactory entityManagerFactory) {
	
		this.factory = Persistence.createEntityManagerFactory("locadora");
	}
	
	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	
	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}
}
