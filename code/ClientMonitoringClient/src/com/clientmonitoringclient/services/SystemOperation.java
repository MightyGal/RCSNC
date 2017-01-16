/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clientmonitoringclient.services;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class SystemOperation {
    public static void shutDown(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    String shutdownCmd = "shutdown -s";
    Process child = Runtime.getRuntime().exec(shutdownCmd);
                } catch (IOException ex) {
                    Logger.getLogger(SystemOperation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
        
    }
    public static void restart(){
        try {
            Runtime r=Runtime.getRuntime();
             r.exec("shutdown -r");
        } catch (IOException ex) {
            Logger.getLogger(SystemOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void lockScreen(){
        
    }
    public static void main(String args[]){
        shutDown();
        
    }
}
