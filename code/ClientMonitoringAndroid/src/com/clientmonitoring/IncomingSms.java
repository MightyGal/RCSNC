package com.clientmonitoring;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.clientmonitoring.model.Message;
import com.clientmonitoring.model.Message.MessageType;
import com.clientmonitoring.util.CustomHttpClient;
import com.google.gson.Gson;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class IncomingSms extends BroadcastReceiver {
	  // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
     
    public void onReceive(Context context, Intent intent) {
     
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
 
        try {
             
            if (bundle != null) {
                 
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                 
                for (int i = 0; i < pdusObj.length; i++) {
                     
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                     
                    String senderNum = phoneNumber;
                    String messageContent = currentMessage.getDisplayMessageBody();
 
                    Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + messageContent);
                    
                     Message message=new Message();
                     message.setMessage(messageContent);
                     message.setMobile(senderNum);
                     message.setMessageType(MessageType.RECIEVED);
                   final  String messageJsonData=new Gson().toJson(message);
                     new Thread(new Runnable() {
						
						@Override
						public void run() {
							ArrayList<NameValuePair> postParameters=new ArrayList<NameValuePair>();
							postParameters.add(new BasicNameValuePair("ACTION","RECEIVED_MESSAGE"));

							postParameters.add(new BasicNameValuePair("jsondata", messageJsonData));
							// TODO Auto-generated method stub
							try {
								CustomHttpClient.executeHttpPost("MessageManageServlet", postParameters);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}).start();
                  
 
                   // Show Alert
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context,
                                 "senderNum: "+ senderNum + ", message: " + messageContent, duration);
                    toast.show();
                     
                } // end for loop
              } // bundle is null
 
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
             
        }
    }    
}
