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

public class CreateAccount extends Activity {

	EditText txt_account_name;
	EditText txt_account_number;
	EditText txt_orgName;
	EditText txt_balance;
	
	Button btn_add_account;
	Button sync_page;
	String db_account_name;
	String db_account_number;
	String db_account_balance;
	String db_orgName;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        txt_account_name = (EditText)findViewById(R.id.txt_accountName);
        txt_account_number = (EditText)findViewById(R.id.txt_account_number);
        txt_balance = (EditText)findViewById(R.id.txt_balance);
        txt_orgName = (EditText)findViewById(R.id.txt_orgName);
        sync_page = (Button)findViewById(R.id.sync_page);
        btn_add_account = (Button)findViewById(R.id.btn_add_acount);
        final Dialog d = new Dialog(this);
        final TextView tv = new TextView(this);
        
        
        //Add account button implementation
        btn_add_account.setOnClickListener(new OnClickListener(){
        	public void onClick(View v) 
        	{
        		boolean dbStored = false;
        		
        		try
        		{
	        		db_account_name = txt_account_name.getText().toString();
	        		db_account_number = txt_account_number.getText().toString();
	        		db_account_balance = txt_balance.getText().toString();
	        		db_orgName = txt_orgName.getText().toString();
	        		
	        		//Introducing the Database class
	        		
	        		Sql_db db_entry = new Sql_db(CreateAccount.this);
	        		
	        		//Open the Database
	        		db_entry.open();
	        		
	        		//Implementation
	        		db_entry.createEntry(db_account_name,db_account_number,db_account_balance,db_orgName);
	
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
//        				d.setTitle("Create Account - DB Connection");
//        				tv.setText("DB connection success!");
//        				d.setContentView(tv);
//        				d.show();
        				
        				//Now redirect
        				
        				Intent answer = new Intent();
        				answer.putExtra("account_name",  txt_account_name.getText().toString());
        				setResult(RESULT_OK,answer);
        				finish();
        			}
        			else
        			{
        				d.setTitle("ERRRRROOOOORRR");
        				tv.setText("DB connection success!");
        				d.setContentView(tv);
        				d.show();
        			}
        		}
        	}	
        });
        
        
        sync_page.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
				Intent goToNextActivity = new Intent(getApplicationContext(), sync_DB.class);
        		startActivity(goToNextActivity);
			}
        });
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_account, menu);
        return true;
    }
}
