package com.example.account;

import java.lang.*;
import android.util.Log;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.*;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.example.account.CreateAccount;
import com.example.account.Sql_db;
import com.example.account.UserFunctions;

public class sync_DB extends Activity 
{
    private final String NAMESPACE = "http://tempuri.org/";
    private final String URL = "http://haingbs.com/Service1.asmx";
    private final String SOAP_ACTION = "http://tempuri.org/SyncUserInfo";
    private final String METHOD_NAME = "SyncUserInfo";
    
    //User Information
    
    String first_name;
    String last_name;
    String user_name;
    int user_type;
    String password;
    String output;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.sync_account);
    	
    	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME); 
    	
    	//Getting the user information values from database
    	
    	final Sql_db_startup info = new Sql_db_startup(this);
    	
    	info.open();
		info.getData();
		
		first_name = info.getFirst_Name();
    	last_name = info.getLast_Name();
    	user_name = info.getUser_Name();
    	user_type = info.getUser_Type();
    	password = info.getPassword();
    	
    	String[] user_info = new String[] {"first_name","last_name","user_name","user_type","password"};
    	String[] user_values = new String[] {first_name,last_name,user_name,Integer.toString(user_type),password};

    	//Adding user information
    	
    	for (int i=0;i<user_info.length;i++)
    	{
    		request.addProperty(user_info[i], user_values[i]);
    	}
       
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
    	envelope.dotNet = true;
    	
    	envelope.setOutputSoapObject(request);
    	
    	 HttpTransportSE aht = new HttpTransportSE(URL);
    	 try 
    	 {
    		 aht.call(SOAP_ACTION, envelope);
    		 SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
    		 
    		 Context context = getApplicationContext();
    		 
    		 CharSequence text = "Hello toast!: "+ response;
    		 int duration = Toast.LENGTH_SHORT;

    		 Toast toast = Toast.makeText(context, text, duration);
    		 toast.show();
    		 
    	 }catch (Exception e) {
             e.printStackTrace();
         }	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_account, menu);
        return true;
    }
}
