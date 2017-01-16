package com.clientmonitoringserver.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.clientmonitoringserver.model.Command;
import com.clientmonitoringserver.model.User;
import com.clientmonitoringserver.util.HibernateUtil;
import com.clientmonitoringserver.util.ListOperation;

public class CommandService {
	public Command addCommand(Command command){
		SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		

		session.save(command);

		session.getTransaction().commit();
		return command;
	}
	public List<Command> getCommands(String mobile) {
		 SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from Command c where c.mobile=:mobile");
			query.setParameter("mobile", mobile);

			List<Command> commands = ListOperation.castList(Command.class, query.list());
			session.getTransaction().commit();
			return commands;
		
		
	}
	public void deleteCommands(String mobile) {
		 SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("delete from Command c where c.mobile=:mobile");
			query.setParameter("mobile", mobile);
			query.executeUpdate();

			session.getTransaction().commit();
		
	}

}
