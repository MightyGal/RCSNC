/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clientmonitoringclient.services;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class SystemInfoService {
    public String getHardwareInfo(){
        System.out.println("Available processors (cores): " + 
        Runtime.getRuntime().availableProcessors());

    /* Total amount of free memory available to the JVM */
    System.out.println("Free memory (bytes): " + 
        Runtime.getRuntime().freeMemory());

    /* This will return Long.MAX_VALUE if there is no preset limit */
    long maxMemory = Runtime.getRuntime().maxMemory();
    /* Maximum amount of memory the JVM will attempt to use */
    System.out.println("Maximum memory (bytes): " + 
        (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

    /* Total memory currently available to the JVM */
    System.out.println("Total memory available to JVM (bytes): " + 
        Runtime.getRuntime().totalMemory());
    return null;

    }
    public static String getOsInfo(){


   return System.getProperty("os.name");

    }
    public static  String  getHostName(){
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            Logger.getLogger(SystemInfoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static void main(String args[]){
        System.out.println(getOsInfo());
                System.out.println(getHostName());

        
    }
    
}
