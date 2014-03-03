package br.com.sapientia.vegetabilia.utils;

import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static final SessionFactory factory;
	private static final ServiceRegistry registry;

	static {
		Configuration cf = new Configuration();
		cf.configure("hibernate.cfg.xml");

		registry = new ServiceRegistryBuilder().applySettings(
				cf.getProperties()).buildServiceRegistry();

		cf.setSessionFactoryObserver(new SessionFactoryObserver() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void sessionFactoryCreated(SessionFactory arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void sessionFactoryClosed(SessionFactory arg0) {
				// TODO Auto-generated method stub

			}
		});

		factory = cf.buildSessionFactory(registry);
	}

	public static SessionFactory getSessionFactory() {
		return factory;
	}
}
