/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clientmonitoringclient.services;

import com.clientmonitoringclient.model.Command;
import com.clientmonitoringclient.model.Message;
import com.clientmonitoringclient.model.User;
import com.clientmonitoringclient.util.HttpRequestClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * 
 */
public class MessageCheckService extends Thread{
    private boolean status=true;
    public void stopMonitor(){
        status=false;
    }

    @Override
    public void run() {
        while(true){
            if(!status)
                break;
        User user=new UserServices().getUser();
             List<NameValuePair> postParameter=new ArrayList<NameValuePair>();
             postParameter.add(new BasicNameValuePair("ACTION", "GET_COMMANDS"));
             postParameter.add(new BasicNameValuePair("mobile", user.getMobile()));

     String response=null;
        try {
            response = HttpRequestClient.sendPost("UserServlet", postParameter);
        } catch (Exception ex) {
            Logger.getLogger(MessageCheckService.class.getName()).log(Level.SEVERE, null, ex);
        }
     Type type = new TypeToken<List<Command>>() {}.getType();
     List<Command> commands=new Gson().fromJson(response, type);
     for(Command command:commands){
         new UserServices().processMessage(command);
     }
     
        }
    }
    
    
}
