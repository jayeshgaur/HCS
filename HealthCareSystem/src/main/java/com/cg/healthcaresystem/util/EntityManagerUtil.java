package com.cg.healthcaresystem.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	
	public static void initialize() {
		entityManagerFactory = Persistence.createEntityManagerFactory("healthcaresystem");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}

	public static void setEntityManager(EntityManager entityManager) {
		EntityManagerUtil.entityManager = entityManager;
	}
	
	

}
