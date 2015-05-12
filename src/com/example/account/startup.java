package com.example.account;

import java.lang.*;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class startup extends Activity 
{
	final Context context = this;

	String first_name;
	String last_name;
	String user_name;
	String password;
	
	
	public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_start);
        
        final Dialog dialog = new Dialog(context);
        
        
        //Check the app status. If user is not created an account. Display the registration information. 
        //Otherwise go to the main menu page
        
        //Check the database status for user information
        
        //Introducing the Database class
        
        Sql_db_startup db_entry = new Sql_db_startup(this);
        
        db_entry.open();
        db_entry.getData();
        
        first_name = db_entry.getFirst_Name();
        last_name = db_entry.getLast_Name();
        user_name = db_entry.getUser_Name();
        password = db_entry.getPassword();
        
        //Check the username
        final Dialog d = new Dialog(this);
        final TextView tv = new TextView(this);
        
        if(user_name==null)
        {
        	//User is not found, so redirect to registration page
        	//Intent goToNextActivity = new Intent(getApplicationContext(), userRegister.class);
    		//startActivity(goToNextActivity);
        	
        	Intent intent = new Intent(startup.this,UserSelector.class);
			startActivity(intent);
        }
        else
        {
        	//User is found, so redirect to the mainmenu page
        	Intent goToNextActivity = new Intent(getApplicationContext(), MainMenu.class);
    		startActivity(goToNextActivity);
        }
        
        db_entry.close();        
    }    

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_account, menu);
        return true;
    }
}
