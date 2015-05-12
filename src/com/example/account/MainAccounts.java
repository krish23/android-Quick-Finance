package com.example.account;

import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.account.MainAccounts;
import com.example.account.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class MainAccounts extends Activity {
	 String[] Accounts = new String[] {};
	 long lposition;

	private ListView viewAccountsList;
	ArrayList<String> AccountsList = new ArrayList<String>();
	private ArrayAdapter<String> listAdapter;
	private final Context context = this;
	private String selectedItem;
	static final private int CHOOSE_COLOR = 0;
	static final private int CHOOSE_EDIT = 1;
	String db_account_name;
	String db_account_number;
	String db_account_balance;
	String db_account_orgname;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainaccounts);
        lposition=1;
        
        viewAccountsList = (ListView) findViewById( R.id.lst_accounts ); 
          
        //ArrayList<String> planetList = new ArrayList<String>();  
        AccountsList.addAll( Arrays.asList(Accounts) );  
         // Create ArrayAdapter using the planet list.  
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow_accounts, AccountsList); 
        
        
      //get the size
        Sql_db db_count = new Sql_db(MainAccounts.this);
        db_count.open();
        int i = db_count.getsize();
        db_count.close();
        
        //add name of bugets to the list
        for(int j = 1; j<=i;j++)
        {
        	Sql_db db_count1 = new Sql_db(MainAccounts.this);
            db_count1.open();
            listAdapter.add(db_count1.getAccname(j));
            db_count1.close();
        }
         
        listAdapter.notifyDataSetChanged();
        
        // Set the ArrayAdapter as the ListView's adapter.  
        OnItemClickListener itemListener = new OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View arg1, int position,long arg3)
			{
				lposition=position+1;
				Sql_db db_entry = new Sql_db(MainAccounts.this);
		        db_entry.open();
				Toast.makeText(getApplicationContext(),
						db_entry.getAccname(lposition)+ "\n" + db_entry.Accnumber(lposition) + "\n" + db_entry.AccOrgname(lposition) + "\n" + db_entry.getAccbalance(lposition),
						Toast.LENGTH_SHORT).show();
				db_entry.close();
				Toast.makeText(getApplicationContext(),
						"what " + lposition,
						Toast.LENGTH_SHORT).show();
				Button editt = (Button)findViewById(R.id.btn_editaccounts);
				editt.setText("edit " + parent.getItemAtPosition(position).toString()+ " " + (position+1));

			}

        };
        
        OnItemLongClickListener itemLongListener = new OnItemLongClickListener(){

		 public boolean onItemLongClick(AdapterView<?> parent, View arg1,int position, long arg3) {
				
				// TODO Auto-generated method stub
				selectedItem = parent.getItemAtPosition(position).toString();
				
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("DO you really want to remove " + selectedItem + "?");
				builder.setCancelable(false);
				builder.setPositiveButton("yes", new DialogInterface.OnClickListener(){

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

						listAdapter.remove(selectedItem);
						listAdapter.notifyDataSetChanged();
						long lRow1 = lposition;
						Sql_db ex1 = new Sql_db(MainAccounts.this);
						ex1.open();
						//ex1.deleteEntry(lRow1);
						ex1.close();
						
						Toast.makeText(getApplicationContext(),
								selectedItem + "has been removed.",
								Toast.LENGTH_SHORT).show();
					}
					
				});
				builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				});
				
				builder.show();
				
				return false;
			}
        };
        
        viewAccountsList.setAdapter( listAdapter );
        viewAccountsList.setOnItemClickListener(itemListener);
        viewAccountsList.setOnItemLongClickListener(itemLongListener);
        
	}
	public void btnClick_addAccounts(View target)
    {
    	Intent question = new Intent(this,com.example.account.CreateAccount.class) {
    		
		};
		startActivityForResult(question, CHOOSE_COLOR);
    }
	
	public void btnClick_editAccounts(View target)
    {

    	Intent question = new Intent(this,com.example.account.EditAccount.class) {
    		
		};
    	Sql_db db_count1 = new Sql_db(MainAccounts.this);
        db_count1.open();
        
        question.putExtra("acc_name", db_count1.getAccname(lposition));
        question.putExtra("acc_number", db_count1.Accnumber(lposition));
        question.putExtra("acc_balance", db_count1.getAccbalance(lposition));
        question.putExtra("acc_orgName", db_count1.AccOrgname(lposition));
        
        db_count1.close();
		startActivityForResult(question, CHOOSE_EDIT);
		
    }
	
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	final Dialog d = new Dialog(this);
	        final TextView tv = new TextView(this);
	    	switch(requestCode)
	    	{
	    	case CHOOSE_COLOR:
	    		if(resultCode == RESULT_OK)
	    		{
	    			boolean dbStored = false;
	        		
	        		try
	        		{
	        			String name1 = data.getExtras().getString("account_name");
	        			listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow_accounts, AccountsList);   
	        			listAdapter.add(name1 );
	        			listAdapter.notifyDataSetChanged();
	        			viewAccountsList.setAdapter( listAdapter );
//	        			
//	        			db_account_name = name1;
//	        			db_account_number = data.getExtras().getString("account_number");
//	        			db_account_balance = data.getExtras().getString("account_balance");
//	        			db_account_orgname = data.getExtras().getString("account_orgname");
//	    	        
//	        			Sql_db db_entry = new Sql_db(MainAccounts.this);
//	        		
//	        			//Open the Database
//	        			db_entry.open();
//	        			
//	        			//Implementation
//	        			db_entry.createEntry(db_account_name,db_account_number,db_account_balance,db_account_orgname);
//	        			
	        			//Close the database
	        			//db_entry.close();
	        			Toast.makeText(getApplicationContext(),
	    						"you have created",
	    						Toast.LENGTH_SHORT).show();
	        		
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
	        				d.setTitle("Create Account - DB Connection");
	        				tv.setText("DB connection success!");
	        				d.setContentView(tv);
	        				d.show();
	        			}
	        		}
	    		}
	    		break;
	    	case CHOOSE_EDIT:
	    		if(resultCode == RESULT_OK)
	    		{

	    			boolean dbStored = false;
	        		
	        		try
	        		{
	        			String name2 = data.getExtras().getString("save_acName");
	        			if(name2.equals("false"))
	        			{
	            			Toast.makeText(getApplicationContext(),
	        						"what the hell",
	        						Toast.LENGTH_SHORT).show();
	        			}
	        			else
	        			{
	            			//db_buget_name = name1;
	        				//String bname =  name1;
	            			listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow_accounts, AccountsList);
	            			//counts the number of enterys
	            			Sql_db db_count = new Sql_db(this);
	            	        db_count.open();
	            	        int i = db_count.getsize();
	            	        db_count.close();
	            			Toast.makeText(getApplicationContext(),
	        						"the size of the database is "+i,
	        						Toast.LENGTH_SHORT).show();
	            			//listAdapter.remove(listAdapter.getItem(lposition-1));
	            	        for(long j = i; j>=1;j--)
	            	        {
	            	        	Sql_db db_count1 = new Sql_db(this);
	            	            db_count1.open();
	            	            listAdapter.remove(db_count1.getAccname(j));
	            	            db_count1.close();
	            	        }
	            			listAdapter.notifyDataSetChanged();
	            			/*Toast.makeText(getApplicationContext(),
	        						name2,
	        						Toast.LENGTH_SHORT).show();*/
	            			Button editt = (Button)findViewById(R.id.btn_editaccounts);
	        				editt.setText("edit " + name2+ " " + (lposition));
	            			db_account_number = data.getExtras().getString("save_acNumber");
	            			db_account_balance = data.getExtras().getString("save_acBalance");
	            			db_account_orgname = data.getExtras().getString("save_acOName");
	            			
	            			Sql_db db_entry = new Sql_db(this);
	            		
	            			//Open the Database
	            			db_entry.open();
	            			
	            			//Implementation
	            			db_entry.updateEntry(lposition,name2,db_account_number,db_account_balance,db_account_orgname);
	            			
	            			//Close the database
	            			db_entry.close();

	            			viewAccountsList.setAdapter( listAdapter );
	        			
	        			listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow_accounts, AccountsList);
	        			/*Sql_db db_count = new Sql_db(this);
	        	        db_count.open();
	        	        int i = db_count.getsize();
	        	        db_count.close();*/
	        	        
	        	        //add name of bugets to the list
	        	        for(long j = 1; j<=i;j++)
	        	        {
	        	        	Sql_db db_count1 = new Sql_db(this);
	        	            db_count1.open();
	        	            listAdapter.add(db_count1.getAccname(j));
	        	            db_count1.close();
	        	        }
	        	         
	        	        listAdapter.notifyDataSetChanged();
	        			//mainListView.setAdapter( listAdapter );
	        			/*Toast.makeText(getApplicationContext(),
	    						"you have updated",
	    						Toast.LENGTH_SHORT).show();*/

	        			dbStored = true;
	        			}
	        		
	        		}
	        		catch(Exception e) 
	        		{
	        			dbStored = false;
	        		}
	        		finally
	        		{
	        			if(dbStored)
	        			{
	        				d.setTitle("Create Account - DB Connection");
	        				tv.setText("DB connection success!");
	        				d.setContentView(tv);
	        				d.show();
	        			}
	        		}
	    		}
	    		break;
	    		
	    	default:
	    		break;
	    			
	    	}
	    }
	
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.create_account, menu);
	        return true;
	    }
	
}
