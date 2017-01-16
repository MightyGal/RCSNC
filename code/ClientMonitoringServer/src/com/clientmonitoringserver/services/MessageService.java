package com.clientmonitoringserver.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.clientmonitoringserver.model.Message;
import com.clientmonitoringserver.model.Message.MessageType;
import com.clientmonitoringserver.model.User;
import com.clientmonitoringserver.util.HibernateUtil;
import com.clientmonitoringserver.util.ListOperation;

public class MessageService {
	public List<Message> getMessageToSend(){
		  SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from Message m where m.messageType=:messagetype");
			query.setParameter("messagetype", MessageType.TOSEND);

			List<Message> messages = ListOperation.castList(Message.class, query.list());
			session.getTransaction().commit();
			return messages;
	}

	public Message  addMessage(Message message){
		SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		session.save(message);

		session.getTransaction().commit();
		return message;
	}

	public void deleteMessage(Message message) {
		SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();

		Session session =  sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.delete(message);
		session.getTransaction().commit();
	}

}
