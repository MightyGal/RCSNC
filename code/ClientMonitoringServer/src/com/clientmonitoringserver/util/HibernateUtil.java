package com.clientmonitoringserver.util;


import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

  private static SessionFactory sessionFactory;
 private static ServiceRegistry serviceRegistry;

  public static SessionFactory configureSessionFactory() throws HibernateException {
	  if(sessionFactory==null){
     Configuration configuration = new Configuration();
     configuration.configure();
     serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
     sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	  }
     return sessionFactory;
 }
}

