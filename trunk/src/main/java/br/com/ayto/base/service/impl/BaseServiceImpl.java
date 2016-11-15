package br.com.ayto.base.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.ws.BindingProvider;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import br.com.ayto.base.model.BaseModel;
import br.com.ayto.base.service.BaseService;

public abstract class BaseServiceImpl implements BaseService {
	private static final long serialVersionUID = 8513632354316595381L;
	private static final Log LOG = LogFactory.getLog(BaseServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired(required = false)
	private ConnectionFactory jmsConnectionFactory;

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

	protected void sendMessageToQueue(String queueName, Map<String, String> mapValues) throws Exception {
		Validate.notNull("JMS Connection Factory não configurado");
		Connection connection = null;
		javax.jms.Session session = null;
		try {
			// Create a Connection
			connection = jmsConnectionFactory.createConnection();
			connection.start();

			// Create a Session
			session = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue(queueName);

			// Create a MessageProducer from the Session to the Topic or Queue
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			// Create a messages
			MapMessage message = session.createMapMessage();
			for (Entry<String, String> entry : mapValues.entrySet()) {
				message.setString(entry.getKey(), entry.getValue());
			}

			// Tell the producer to send the message
			producer.send(message);
		} catch (Exception e) {
			LOG.error(ExceptionUtils.getStackTrace(e));
			throw e;
		} finally {
			try {
				session.close();
			} catch (Exception e) {
			}
			try {
				connection.close();
			} catch (Exception e) {
			}
		}
	}

	protected Query setQueryCache(Query query) {
		return query.setHint("org.hibernate.cacheable", true);
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public ConnectionFactory getJmsConnectionFactory() {
		return jmsConnectionFactory;
	}

	public void setJmsConnectionFactory(ConnectionFactory jmsConnectionFactory) {
		this.jmsConnectionFactory = jmsConnectionFactory;
	}

}
