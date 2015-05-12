package com.example.account;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Button;

public class MainMenu extends Activity 
{
	
	Button sync_page;
	Button account;

	public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        
        final Dialog d = new Dialog(this);
        final TextView tv = new TextView(this);
        
        sync_page = (Button)findViewById(R.id.btn_m_sync);
        account = (Button)findViewById(R.id.btn_m_account);
        
        sync_page.setOnClickListener(new OnClickListener(){
        	public void onClick(View v) 
        	{
        		Intent goToNextActivity = new Intent(getApplicationContext(), sync_DB.class);
        		startActivity(goToNextActivity);
        	}
        });
        
        account.setOnClickListener(new OnClickListener(){
        	public void onClick(View v) 
        	{
        		Intent goToNextActivity = new Intent(getApplicationContext(), MainAccounts.class);
        		startActivity(goToNextActivity);
        	}
        });
        
        
        //If wifi or 4G, 3G available then, execute the sync function
        
        if(checkInternetConnection())
        {
        	d.setTitle("Internet Connection OK");
			tv.setText("OK");
			d.setContentView(tv);
			d.show();
        }
        else
        {
        	d.setTitle("NO Internet :(");
			tv.setText("OK");
			d.setContentView(tv);
			d.show();
        }
    }
	
	public boolean checkInternetConnection()
	{
		final ConnectivityManager connMgr = (ConnectivityManager)  
		this.getSystemService(Context.CONNECTIVITY_SERVICE);   
		
		final android.net.NetworkInfo wifi =  connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		final android.net.NetworkInfo mobile =  connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
				   
		if( wifi.isAvailable() )
		{
		   return true;
		}  
		else if( mobile.isAvailable() )
		{ 
		   return true;	  
		}
		else 
		{
		   return false;	
		} 
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_account, menu);
        return true;
    }
}
