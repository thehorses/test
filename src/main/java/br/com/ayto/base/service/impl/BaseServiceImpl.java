package br.com.ayto.base.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.ayto.base.model.BaseModel;
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

	@Override
	public <T extends BaseModel> T findById(Class<T> clazz, Object id) {
		return getEM().find(clazz, id);
	}

	protected Session getSession() {
		return getEM().unwrap(Session.class);
	}

	protected SessionFactory getSessionFactory() {
		return getSession().getSessionFactory();
	}

}
