package com.cg.healthcaresystem.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	private static EntityManagerFactory entityManagerFactory;

	
	public static void initialize() {
		entityManagerFactory = Persistence.createEntityManagerFactory("healthcaresystem");
	}

	public static EntityManager getEntityManager() {
		if(null==entityManagerFactory) {
			entityManagerFactory = Persistence.createEntityManagerFactory("healthcaresystem");
		}
		return entityManagerFactory.createEntityManager();
	}

	
	

}
