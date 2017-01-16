/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clientmonitoringclient.services;

import com.clientmonitoringclient.model.Command;
import com.clientmonitoringclient.model.Leader;
import com.clientmonitoringclient.model.Message;
import com.clientmonitoringclient.model.User;
import com.clientmonitoringclient.ui.FileServices;
import com.clientmonitoringclient.util.HttpRequestClient;
import com.clientmonitoringclient.util.NotificationPopUpManager;
import com.clientmonitoringserver.util.HibernateUtil;
import com.google.gson.Gson;
import com.nex.locationbasedchat.util.ListOperation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 *
 */
public class UserServices {
    public void addUser(User user){
//                 SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
//		session.beginTransaction();
//		session.save(user);
//
//		session.getTransaction().commit();
                new FileServices().writeToFile(user.getMobile());
    }
    public User getUser(){
        User user=new User();
//        SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
//		session.beginTransaction();
//		Query query = session.createQuery("from User");
//
//		List<User> user = ListOperation.castList(User.class, query.list());
//		session.getTransaction().commit();
//		return user.get(0);
        try {
            user.setMobile(new FileServices().readFromFile());
        } catch (IOException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       
      return  user;
	
        
    }
    public void sendMessage(final String messageContent){
        new Thread(new Runnable() {

            @Override
            public void run() {
          
         List<NameValuePair> postParametres=new ArrayList<NameValuePair>();
                    Message message=new Message();
                    message.setMobile(new UserServices().getUser().getMobile());
                    message.setMessageType(Message.MessageType.TOSEND);
                    message.setMessage(messageContent);
                    String jsonData=new Gson().toJson(message);
                    postParametres.add(new BasicNameValuePair("ACTION", "RECEIVED_MESSAGE"));
                    postParametres.add(new BasicNameValuePair("jsondata", jsonData));
                    try {
                        //send message to server
                        HttpRequestClient.sendPost("MessageManageServlet", postParametres);
                    } catch (Exception ex) {
                        Logger.getLogger(Win32IdleTime.class.getName()).log(Level.SEVERE, null, ex);
                    }
                      }
        }).start();
    }
    public boolean checkUserAlreadyExist(){
        try {
            //         SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
            //		Session session = sessionFactory.getCurrentSession();
            //		session.beginTransaction();
            //		Query query = session.createQuery("from User");
            //
            //		List<User> users = ListOperation.castList(User.class, query.list());
            //		session.getTransaction().commit();
            //		return (users.size()>0);

                    new FileServices().readFromFile();
        } catch (IOException ex) {
return  false;       }
        return true;
    }
    public static void main(String args[]){
       new UserServices().getUser();
    }
    public void processMessage(Command command){
        String commandType=command.getContent().split(" ")[1];
        System.out.println(commandType);
        if(commandType.equalsIgnoreCase("SHOW")){
            //show message
            String message=command.getContent().substring(7);
            NotificationPopUpManager.createNotification(message, "Message From Leader");
            
        }
        else if(commandType.equalsIgnoreCase("SHUTDOWN")){
              sendMessage("Success ");
           SystemOperation.shutDown();
         
            
        }
        if(commandType.equalsIgnoreCase("RESTART")){
            SystemOperation.restart();
        }
        
       
       
    }

    public void saveLeader(Leader leader) {
 SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(leader);

		session.getTransaction().commit();    }
    
}
