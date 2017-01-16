package com.clientmonitoring.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.clientmonitoring.model.Message;
import com.clientmonitoring.util.CustomHttpClient;
import com.clientmonitoring.util.HttpRequestClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;

public class MessageSendHelperService  extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		 final Handler handler=new Handler() {
			@Override
			public void handleMessage(android.os.Message msg) {
				switch (msg.what) {
				case 1:
					Toast.makeText(getApplicationContext(), msg.arg1+"hhhh", Toast.LENGTH_LONG).show();
					break;

				default:
					break;
				}
				// TODO Auto-generated method stub
				super.handleMessage(msg);
			}
		};
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				ArrayList<NameValuePair> postParameters=null;
								
				while(true){
					postParameters=new ArrayList<NameValuePair>();
				try {
					postParameters.add(new BasicNameValuePair("ACTION","GET_MESSAGE_TOSEND"));
					String response= CustomHttpClient.executeHttpPost("MessageManageServlet", postParameters);
					Type type = new TypeToken<List<Message>>() {
					}.getType();
					List<Message> messagesToSend=new Gson().fromJson(response, type);
					for(Message message:messagesToSend){
						SmsManager smsManager = SmsManager.getDefault();
						smsManager.sendTextMessage(message.getMobile(), null, message.getMessage(), null, null);
						android.os.Message msg=android.os.Message.obtain(null,1);
						
						handler.sendMessage(msg);
						
					}
					Thread.sleep(5000);
					
					postParameters.remove(0);
					if(messagesToSend.size()>0) {
					postParameters.add(new BasicNameValuePair("ACTION","MESSAGE_SEND_ACK"));
					postParameters.add(new BasicNameValuePair("messages", response.trim()));
					CustomHttpClient.executeHttpPost("MessageManageServlet", postParameters);
					}
					
					
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		}).start();
		
		
		return super.onStartCommand(intent, flags, startId);
	}

}
