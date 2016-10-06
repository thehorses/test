package br.com.ayto.base.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.ws.BindingProvider;

import org.apache.commons.lang.StringUtils;
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

	protected void prepareBindingProviderWsPort(Object port, String endpoint) {
		prepareBindingProviderWsPort(port, endpoint, null, null);
	}

	protected void prepareBindingProviderWsPort(Object port, String endpoint, String user, String password) {
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
		if (!StringUtils.isBlank(user)) {
			bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
		}
		if (!StringUtils.isBlank(password)) {
			bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
		}
	}

	protected Query setQueryCache(Query query) {
		return query.setHint("org.hibernate.cacheable", true);
	}

}
