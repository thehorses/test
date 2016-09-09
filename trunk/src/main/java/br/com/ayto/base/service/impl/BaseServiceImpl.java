package br.com.ayto.base.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ayto.base.service.BaseService;

public abstract class BaseServiceImpl implements BaseService {
	private static final long serialVersionUID = 8513632354316595381L;

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public EntityManager getEM() {
		return getEntityManager();
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
