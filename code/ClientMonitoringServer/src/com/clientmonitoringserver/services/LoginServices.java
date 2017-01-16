package com.clientmonitoringserver.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.clientmonitoringserver.model.Leader;
import com.clientmonitoringserver.model.User;
import com.clientmonitoringserver.util.HibernateUtil;
import com.clientmonitoringserver.util.ListOperation;

public class LoginServices {
	public User checkLogin(Leader admin) {
		List<User> users = null;
		SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		String querrystString = "FROM Admin a where a.userName= :userName and a.password= :password";
		Query query = session.createQuery(querrystString);
		//query.setParameter("userName", admin.getUserName());
	//	query.setParameter("password", admin.getPassword());
		try {
			users = ListOperation.castList(User.class, query.list());
			session.getTransaction().commit();

		} catch (RuntimeException re) {
			session.getTransaction().rollback();
		}

		if (users.size() < 1) {
			return null;
		}
		return users.get(0);

	}

	public Leader addLogin(Leader admin) {
		SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		session.save(admin);

		session.getTransaction().commit();
		return admin;

	}
	

}
