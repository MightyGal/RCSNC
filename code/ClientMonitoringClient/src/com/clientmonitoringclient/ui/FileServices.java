/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clientmonitoringclient.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author DELL
 */
public class FileServices {
    public void writeToFile(String content){
        try {
 
 String filepath=System.getProperty("user.home")+"/user";
            System.out.println(filepath);
			File file = new File(filepath);
 
			// if file doesnt exists, then create it
			
				file.createNewFile();
			
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public String readFromFile() throws IOException{
        
	BufferedReader br = null;
         String filepath=System.getProperty("user.home")+"/user";
			String content="";

 
		try {
 
 
			br = new BufferedReader(new FileReader(filepath));
 String sCurrentLine="";
			while ((sCurrentLine = br.readLine()) != null) {
                           content=content+sCurrentLine;
                            
			}
 
		} catch (IOException e) {
		  throw  e;
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				throw  ex;
			}
		}
                return content;
    }
    public static void main(String args[]){
      //  new FileServices().writeToFile("+9999999999999999999");
        try{
                    System.out.println( new FileServices().readFromFile());

        }
        catch(Exception e){
            
        }
        
    }
    
}
