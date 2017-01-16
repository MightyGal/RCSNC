/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clientmonitoringclient.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 *
 */
public class ProcessService {
    public void process(){
         try {
        String line;
Process p = Runtime.getRuntime().exec
        (System.getenv("windir") +"\\system32\\"+"tasklist.exe");        BufferedReader input =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((line = input.readLine()) != null) {
            System.out.println(line); //<-- Parse data here.
        }
        input.close();
    } catch (Exception err) {
        err.printStackTrace();
    }
    }
    public static void main(String args[]){
        new ProcessService().process();
    }
}
