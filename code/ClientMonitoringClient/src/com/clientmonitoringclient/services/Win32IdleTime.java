/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clientmonitoringclient.services;

/**
 *
 * 
 */
import com.clientmonitoringclient.model.Message;
import com.clientmonitoringclient.util.HttpRequestClient;
import com.google.gson.Gson;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.jna.*;
import com.sun.jna.win32.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

/**
 * Utility method to retrieve the idle time on Windows and sample code to test it.
 * JNA shall be present in your classpath for this to work (and compile).
 * @author ochafik
 */
public class Win32IdleTime extends  Thread{
private boolean status=true;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public void stopMonitor(){
        setStatus(false);
    }
    public interface Kernel32 extends StdCallLibrary {
        Kernel32 INSTANCE = (Kernel32)Native.loadLibrary("kernel32", Kernel32.class);

        /**
         * Retrieves the number of milliseconds that have elapsed since the system was started.
         * @see http://msdn2.microsoft.com/en-us/library/ms724408.aspx
         * @return number of milliseconds that have elapsed since the system was started.
         */
        public int GetTickCount();
    };

    public interface User32 extends StdCallLibrary {
        User32 INSTANCE = (User32)Native.loadLibrary("user32", User32.class);

        /**
         * Contains the time of the last input.
         */
        public static class LASTINPUTINFO extends Structure {
            public int cbSize = 8;

            /// Tick count of when the last input event was received.
            public int dwTime;
        }

        /**
         * Retrieves the time of the last input event.
         * @see http://msdn.microsoft.com/library/default.asp?url=/library/en-us/winui/winui/windowsuserinterface/userinput/keyboardinput/keyboardinputreference/keyboardinputfunctions/getlastinputinfo.asp
         * @return time of the last input event, in milliseconds
         */
        public boolean GetLastInputInfo(LASTINPUTINFO result);
    };

    /**
     * Get the amount of milliseconds that have elapsed since the last input event
     * (mouse or keyboard)
     * @return idle time in milliseconds
     */
    public static int getIdleTimeMillisWin32() {
        User32.LASTINPUTINFO lastInputInfo = new User32.LASTINPUTINFO();
        User32.INSTANCE.GetLastInputInfo(lastInputInfo);
        return Kernel32.INSTANCE.GetTickCount() - lastInputInfo.dwTime;
    }

    enum State {
        UNKNOWN, ONLINE, IDLE, AWAY
    };

    public  void startMonitor() {
        if (!System.getProperty("os.name").contains("Windows")) {
            System.err.println("ERROR: Only implemented on Windows");
            System.exit(1);
        }
        State state = State.UNKNOWN;
        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");

        for (;;) {
            int idleSec = getIdleTimeMillisWin32() / 5000;
            if(!isStatus()){
                System.out.println("Stoping activity monitoring....");
                break;
            }

            State newState =
                idleSec < 30 ? State.ONLINE :
                idleSec > 5 * 60 ? State.AWAY : State.IDLE;

            if (newState != state) {
                state = newState;
                if(state==State.ONLINE){
                    List<NameValuePair> postParametres=new ArrayList<NameValuePair>();
                    Message message=new Message();
                    message.setMobile(new UserServices().getUser().getMobile());
                    message.setMessageType(Message.MessageType.TOSEND);
                    message.setMessage("New activity detected at "+dateFormat.format(new Date()));
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
                System.out.println(dateFormat.format(new Date()) + " # " + state);
            }
            try { Thread.sleep(1000); } catch (Exception ex) {}
        }
    }

    @Override
    public void run() {
        startMonitor();
    }
   


}
