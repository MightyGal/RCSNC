package com.clientmonitoringclient.util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * 
 */
	
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
 
public class HttpRequestClient {
	static final String BASE_URL = "http://192.168.43.61:8080/ClientMonitoringServer/";

	private static final String USER_AGENT = "Mozilla/5.0";
 
	
	// HTTP GET request
	public static  String sendGet(String url) throws Exception {
 
 
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(BASE_URL+url);
                System.out.print(BASE_URL+url);
 
		// add request header
		request.addHeader("User-Agent", USER_AGENT);
 
		HttpResponse response = client.execute(request);
 
		
 
		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		return result.toString();
 
	}
 
	// HTTP POST request
	public static String sendPost(String url, List<NameValuePair> urlParameters) throws Exception {
 
 
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(BASE_URL+url);
                
 
		// add header
		post.setHeader("User-Agent", USER_AGENT);
 
		
 
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
 
		HttpResponse response = client.execute(post);
		
		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		return result.toString();
 
	}
 
}