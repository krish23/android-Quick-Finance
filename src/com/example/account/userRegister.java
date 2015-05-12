package com.example.account;

import java.lang.*;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class userRegister extends Activity 
{
	
	EditText txt_first_name;
	EditText txt_last_name;
	EditText txt_user_name;
	EditText txt_password;

	String db_first_name;
	String db_last_name;
	String db_user_name;
	String db_password;
	

	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);
        
        final int user_type = getIntent().getExtras().getInt("type");
         
        final Dialog d = new Dialog(this);
        final TextView tv = new TextView(this);
        
        //Defining all inputs and button
        
        txt_first_name = (EditText)findViewById(R.id.txt_firstname);
        txt_last_name = (EditText)findViewById(R.id.txt_lastname);
        txt_user_name = (EditText)findViewById(R.id.txt_username);
        txt_password = (EditText)findViewById(R.id.txt_pwd);
        
          Button btn_submit;
          btn_submit = (Button)findViewById(R.id.btn_submit);
          
      //Add account button implementation
        btn_submit.setOnClickListener(new OnClickListener(){
        	public void onClick(View v) 
        	{
        		boolean dbStored = false;
        		
        		try
        		{
	        		db_first_name = txt_first_name.getText().toString();
	        		db_last_name = txt_last_name.getText().toString();
	        		db_user_name = txt_user_name.getText().toString();
	        		db_password = txt_password.getText().toString();
	        		
	        		//Introducing the Database class
	        		
	        		Sql_db_startup db_entry = new Sql_db_startup(userRegister.this);
	        		
	        		//Open the Database
	        		db_entry.open();
	        		
	        		//Implementation
	        		db_entry.createEntry(db_first_name,db_last_name,db_user_name,user_type,db_password);
	
	        		//Close the database
	        		db_entry.close();
	        		
	        		dbStored = true;
	        		
        		}
        		catch(Exception e) 
        		{
        			dbStored = false;
        		}
        		finally
        		{
        			if(dbStored)
        			{
        				d.setTitle("Create User Account - DB Connection");
        				tv.setText("DB connection success!");
        				d.setContentView(tv);
        				d.show();
        			}
        		}
        	}	
        });

    }
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.create_account, menu);
	        return true;
	    }
	
	
}
