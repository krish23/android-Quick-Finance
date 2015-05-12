package com.example.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class EditAccount extends Activity {

	
	EditText edit_accName;
	EditText edit_accNumber;
	EditText edit_accBalance;
	EditText edit_accOName;
	
	EditText save_accName;
	EditText save_accNumber;
	EditText save_accBalance;
	EditText save_accOName;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editaccounts);
		
		
		Intent intent = getIntent();
		edit_accName=(EditText)findViewById(R.id.txt_inp_aName);
		edit_accNumber=(EditText)findViewById(R.id.txt_inp_aNumber);
		edit_accBalance=(EditText)findViewById(R.id.txt_inp_aBalance);
		edit_accOName=(EditText)findViewById(R.id.txt_inp_aOName);
		
		//Set the texts for edit
		
		
		String getaccountBalance = intent.getExtras().getString("acc_number");
		
		
		edit_accName.setText(intent.getExtras().getString("acc_name"));
		edit_accNumber.setText(intent.getExtras().getString("acc_number"));
		edit_accBalance.setText(intent.getExtras().getString("acc_balance"));
		edit_accOName.setText(intent.getExtras().getString("acc_orgName"));
	}
	
	public void btnClick_edit(View view)
	{
		save_accName=(EditText)findViewById(R.id.txt_inp_aName);
		save_accNumber=(EditText)findViewById(R.id.txt_inp_aNumber);
		save_accBalance=(EditText)findViewById(R.id.txt_inp_aBalance);
		save_accOName=(EditText)findViewById(R.id.txt_inp_aOName);
		
		Intent answer = new Intent();
		
		answer.putExtra("save_acName", save_accName.getText().toString());
		answer.putExtra("save_acNumber", save_accNumber.getText().toString());
		answer.putExtra("save_acBalance", save_accBalance.getText().toString());
		answer.putExtra("save_acOName", save_accOName.getText().toString());
		
		setResult(RESULT_OK,answer);
		finish();
	}
	
	public void btnClick_cancel(View view)
	{
		Intent answer = new Intent();
		setResult(RESULT_OK,answer);
		finish();
	}
	
	
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_account, menu);
        return true;
    }
}
