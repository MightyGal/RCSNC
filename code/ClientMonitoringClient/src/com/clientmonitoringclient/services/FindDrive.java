/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clientmonitoringclient.services;

import com.clientmonitoringclient.model.Message;
import com.clientmonitoringclient.util.HttpRequestClient;
import com.google.gson.Gson;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * 
 */
public class FindDrive extends  Thread{
    private boolean status=true;
    public void stopMonitor(){
        status=false;
    }
public  void startMonitor(){
String[] letters = new String[]{ "A", "B", "C", "D", "E", "F", "G", "H", "I"};
File[] drives = new File[letters.length];
System.out.println(letters.length);
boolean[] isDrive = new boolean[letters.length];
System.out.println(isDrive.toString());

// init the file objects and the initial drive state
for ( int i = 0; i < letters.length; ++i )
    {
    drives[i] = new File(letters[i]+":/");

    isDrive[i] = drives[i].canRead();
    }

 System.out.println("FindDrive: waiting for devices...");

 // loop indefinitely
 while(true)
    {
    // check each drive 
        if(!status){
            break;
        }
    for ( int i = 0; i < letters.length; ++i )
        {
        boolean pluggedIn = drives[i].canRead();

        // if the state has changed output a message
        if ( pluggedIn != isDrive[i] )
            {
            if ( pluggedIn ){
                System.out.println("Drive "+letters[i]+" has been plugged in");
            
            
                 Message message=new Message();
             
                    message.setMobile(new UserServices().getUser().getMobile());
                    message.setMessageType(Message.MessageType.TOSEND);
                    message.setMessage("New Device Connected.. "+new Date());
                    String jsonData=new Gson().toJson(message);
                    List<NameValuePair> postParametres=new ArrayList<NameValuePair>();
                    postParametres.add(new BasicNameValuePair("ACTION", "RECEIVED_MESSAGE"));
                    postParametres.add(new BasicNameValuePair("jsondata", jsonData));
                    
                    try {
                        //send message to server
                        HttpRequestClient.sendPost("MessageManageServlet", postParametres);
                    } catch (Exception ex) {
                        Logger.getLogger(Win32IdleTime.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            else
                System.out.println("Drive "+letters[i]+" has been unplugged");

            isDrive[i] = pluggedIn;
            }
        }

    // wait before looping
    try { Thread.sleep(100); }
    catch (InterruptedException e) { /* do nothing */ }

    }
}

    @Override
    public void run() {
        
        startMonitor();
    }

}
