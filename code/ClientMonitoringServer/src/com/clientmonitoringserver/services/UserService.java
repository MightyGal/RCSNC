package com.clientmonitoringserver.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.clientmonitoringserver.model.Command;
import com.clientmonitoringserver.model.Host;
import com.clientmonitoringserver.model.Leader;
import com.clientmonitoringserver.model.Message;
import com.clientmonitoringserver.model.User;
import com.clientmonitoringserver.util.HibernateUtil;
import com.clientmonitoringserver.util.ListOperation;

public class UserService {
	private static final String SPACE = " ";
	public User addUser(User user){
		SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		

		session.save(user);

		session.getTransaction().commit();
		return user;
		
	}
public Host addHost(Host host){
	SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
	Session session = sessionFactory.getCurrentSession();
	session.beginTransaction();
	session.save(host);

	session.getTransaction().commit();
	return host;
}
public User getUser(){
    SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
	Session session = sessionFactory.getCurrentSession();
	session.beginTransaction();
	Query query = session.createQuery("from User");

	List<User> user = ListOperation.castList(User.class, query.list());
	session.getTransaction().commit();
	return user.get(0);

    
}
public Host getHost(String mobile){
	SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
	Session session = sessionFactory.getCurrentSession();
	
	Query query = session.createQuery("From Host where user.mobile=:mobile");
	query.setParameter("mobile", mobile);
	Host host = ListOperation.castList(Host.class, query.list()).get(0);
	session.getTransaction().commit();
	
	return host;

	

	
}
public User getUser(String mobile){
	SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
	Session session = sessionFactory.getCurrentSession();
	
	Query query = session.createQuery("From User u where u.mobile=:mobile");
	query.setParameter("mobile", mobile);
	User user = ListOperation.castList(User.class, query.list()).get(0);
	session.getTransaction().commit();
	
	return user;

	

	
}
public List<Leader> getLeaders(){
	SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
	Session session = sessionFactory.getCurrentSession();
	session.beginTransaction();
	Query query = session.createQuery("From Leader");
   List<Leader> leaders = ListOperation.castList(Leader.class, query.list());
	session.getTransaction().commit();
	
	return leaders;

}
	public static void main(String[] args) {
	List<User> users=	new UserService().getUsers("555");
	System.out.println(users.get(0).getEmail()+"kk");
	//System.out.println(new UserService().addHost(host).getUser().getEmail());
	}
	public List<Message> getMessages(String mobile) {
		SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("From Message m where m.mobile=:mobile");
		query.setParameter("mobile", mobile);
	   List<Message> messages = ListOperation.castList(Message.class, query.list());
		session.getTransaction().commit();
		
		return messages;
	}
	public void generateCommand(Message message) {
		String messageString=message.getMessage();
		if(messageString.split(SPACE)[0].equalsIgnoreCase("CL")){
			User user=new UserService().getUser();
         Command command=new Command();
         command.setContent(message.getMessage());
         command.setMobile(user.getMobile());
         new CommandService().addCommand(command);
         

		}
		if(messageString.split(SPACE)[0].equalsIgnoreCase("LD")){
			List<User> users=new UserService().getUsers(message.getMobile());
			for(User user:users){
				 Command command=new Command();
		         command.setContent(message.getMessage());
		         command.setMobile(user.getMobile());
		         new CommandService().addCommand(command);
				
			}
		
		}

		
	}
	private List<User> getUsers(String mobile) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("From User u where u.leader.mobile=:mobile");
		query.setParameter("mobile", mobile);
		List<User> users = ListOperation.castList(User.class, query.list());
		session.getTransaction().commit();
		
		return users;
	}

	
}
